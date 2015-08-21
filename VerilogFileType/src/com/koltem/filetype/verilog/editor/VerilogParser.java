package com.koltem.filetype.verilog.editor;

import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ChangeListener;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.CommonTokenStream;
import org.netbeans.modules.csl.api.Error;
import org.netbeans.modules.parsing.api.*;
import org.netbeans.modules.parsing.spi.*;
import org.netbeans.modules.parsing.spi.Parser;

import com.koltem.filetype.verilog.antlr.*;

/**
 *
 * @author ranga
 */
public class VerilogParser extends Parser
{

    private Snapshot snapshot;
    private Verilog2001Parser verilogParser;
    
    private List<SyntaxError> syntaxErrors;

    @Override
    public void parse(Snapshot snapshot, Task task, SourceModificationEvent event)
    {
        this.snapshot = snapshot;
        //ANTLRStringStream input = new ANTLRStringStream(snapshot.getText().toString());
        ANTLRInputStream input = new ANTLRInputStream(snapshot.getText().toString());
        Lexer lexer = new Verilog2001Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        verilogParser = new Verilog2001Parser(tokens);
        syntaxErrors = new ArrayList<>();
        verilogParser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                                    int line, int charPositionInLine,
                                    String msg, RecognitionException e) {
                syntaxErrors.add(new SyntaxError(line, charPositionInLine, msg, e));
            }
        });
        try {
            verilogParser.description();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Result getResult(Task task)
    {
        return new VerilogEditorParserResult(snapshot, verilogParser);
    }

    public void cancel()
    {
    }

    public void addChangeListener(ChangeListener changeListener)
    {
    }

    public void removeChangeListener(ChangeListener changeListener)
    {
    }

    public List<SyntaxError> getSyntaxErrors()
    {
        return syntaxErrors;
    }
    
    public class VerilogEditorParserResult extends org.netbeans.modules.csl.spi.ParserResult {

        private Verilog2001Parser verilogParser;
        private boolean valid = true;

        VerilogEditorParserResult(Snapshot snapshot, Verilog2001Parser verilogParser)
        {
            super(snapshot);
            this.verilogParser = verilogParser;
        }
        
        public VerilogParser getMyParser()
        {
            return VerilogParser.this;
        }

        public Verilog2001Parser getVerilogParser()
                throws ParseException
        {
            if (!valid) {
                throw new ParseException();
            }
            return verilogParser;
        }

        protected void invalidate()
        {
            valid = false;
        }

        @Override
        public List<? extends Error> getDiagnostics()
        {
            return new ArrayList();
        }
    }
}

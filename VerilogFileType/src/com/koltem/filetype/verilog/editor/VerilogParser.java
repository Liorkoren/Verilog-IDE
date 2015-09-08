package com.koltem.filetype.verilog.editor;

import com.koltem.filetype.verilog.antlr.Verilog2001Lexer;
import com.koltem.filetype.verilog.antlr.Verilog2001Parser;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ChangeListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;
import org.netbeans.modules.csl.api.Error;
import org.netbeans.modules.parsing.api.Snapshot;
import org.netbeans.modules.parsing.api.Task;
import org.netbeans.modules.parsing.spi.ParseException;
import org.netbeans.modules.parsing.spi.Parser;
import org.netbeans.modules.parsing.spi.SourceModificationEvent;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;
import org.openide.windows.OutputWriter;

/**
 *
 * @author ranga
 */
public class VerilogParser extends Parser {

    private Snapshot snapshot;

    private Verilog2001Parser verilogParser;

    private List<SyntaxError> syntaxErrors;

    static private OutputWriter writer;

    VerilogParser() {
        InputOutput io = IOProvider.getDefault().getIO("Errors", false);
        writer = io.getOut();
    }

    @Override
    public void parse(Snapshot snapshot, Task task, SourceModificationEvent event) {
        this.snapshot = snapshot;

        //ANTLRStringStream input = new ANTLRStringStream(snapshot.getText().toString());
        ANTLRInputStream input = new ANTLRInputStream(snapshot.getText().toString());
        Lexer lexer = new Verilog2001Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        verilogParser = new Verilog2001Parser(tokens);
        verilogParser.setBuildParseTree(false);//Don't create the tree
        syntaxErrors = new ArrayList<>();
//        verilogParser.addErrorListener(new VerilogErrorListener());
        VerilogContextTreeListener contextTreeListener = new VerilogContextTreeListener(verilogParser);
        verilogParser.removeErrorListeners();
        verilogParser.addParseListener(contextTreeListener);
        //IOProvider.getDefault().getIO("Errors", false).setErrVisible(true);
        verilogParser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                    int line, int charPositionInLine,
                    String msg, RecognitionException e) {
                underlineError(recognizer, (Token) offendingSymbol, line, charPositionInLine);
                writer.println("line " + line + ":" + charPositionInLine + " " + msg);
                writer.println("");
                syntaxErrors.add(new SyntaxError(line, charPositionInLine, msg, e));
            }

            protected void underlineError(Recognizer recognizer,
                    Token offendingToken, int line,
                    int charPositionInLine) {
                CommonTokenStream tokens
                        = (CommonTokenStream) recognizer.getInputStream();
                String input = tokens.getTokenSource().getInputStream().toString();
                String[] lines = input.split("\n");
                String errorLine = lines[line - 1];
                writer.println(errorLine);
                for (int i = 0; i < charPositionInLine; i++) {
                    writer.print(" ");
                }
                int start = offendingToken.getStartIndex();
                int stop = offendingToken.getStopIndex();
                if (start >= 0 && stop >= 0) {
                    for (int i = start; i <= stop; i++) {
                        writer.print("^");
                    }
                }
                writer.println("");
            }

        });
        try {
            verilogParser.description();
            writer.println(syntaxErrors.size() + " Errors Detected");
            writer.println("------------------------");
            writer.flush();
            System.out.println("Done Parsing");
            syntaxErrors.addAll(contextTreeListener.getSyntaxErrors());
            System.out.println("Done Adding Context Tree Syntax Errors");

        } catch (UnsupportedOperationException ex) {
            System.out.println("UnsupportedOperationException : " + ex.getMessage());
        }
    }

    @Override
    public Result getResult(Task task) {
        return new VerilogEditorParserResult(snapshot, verilogParser);
    }

    public void cancel() {
    }

    public void addChangeListener(ChangeListener changeListener) {
    }

    public void removeChangeListener(ChangeListener changeListener) {
    }

    public List<SyntaxError> getSyntaxErrors() {
        return syntaxErrors;
    }

    public class VerilogEditorParserResult extends org.netbeans.modules.csl.spi.ParserResult {

        private Verilog2001Parser verilogParser;
        private boolean valid = true;

        VerilogEditorParserResult(Snapshot snapshot, Verilog2001Parser verilogParser) {
            super(snapshot);
            this.verilogParser = verilogParser;
        }

        public VerilogParser getMyParser() {
            return VerilogParser.this;
        }

        public Verilog2001Parser getVerilogParser()
                throws ParseException {
            if (!valid) {
                throw new ParseException();
            }
            return verilogParser;
        }

        protected void invalidate() {
            valid = false;
        }

        @Override
        public List<? extends Error> getDiagnostics() {
            return new ArrayList();
        }
    }
}

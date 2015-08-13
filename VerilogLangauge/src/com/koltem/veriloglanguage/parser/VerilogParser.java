/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koltem.veriloglanguage.parser;

import com.koltem.jccverilogparser.JccVerilogParser;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ChangeListener;
import org.netbeans.modules.csl.api.Error;
import org.netbeans.modules.csl.spi.ParserResult;
import org.netbeans.modules.parsing.api.Snapshot;
import org.netbeans.modules.parsing.api.Task;
import org.netbeans.modules.parsing.spi.Parser;
import org.netbeans.modules.parsing.spi.SourceModificationEvent;
/**
 *
 * @author Koren
 */
public class VerilogParser extends Parser {

    private Snapshot snapshot;
    private JccVerilogParser jccVerilogParser;

    @Override
    public void parse(Snapshot snapshot, Task task, SourceModificationEvent event) {
        this.snapshot = snapshot;
        Reader reader = new StringReader(snapshot.getText().toString());
        jccVerilogParser = new JccVerilogParser(reader);
        try {
            jccVerilogParser.SourceText();
        } catch (com.koltem.jccverilogparser.ParseException ex) {
            Logger.getLogger(VerilogParser.class.getName()).log(Level.WARNING, null, ex);
        }
    }

    @Override
    public Parser.Result getResult(Task task) {
        return new VerilogParserResult(snapshot, jccVerilogParser);
    }

    @Override
    public void cancel() {
    }

    @Override
    public void addChangeListener(ChangeListener changeListener) {
    }

    @Override
    public void removeChangeListener(ChangeListener changeListener) {
    }

    public static class VerilogParserResult extends ParserResult {

        private JccVerilogParser jccVerilogParser;
        private boolean valid = true;

        VerilogParserResult(Snapshot snapshot, JccVerilogParser verilogParser) {
            super(snapshot);
            this.jccVerilogParser = verilogParser;
        }

        public JccVerilogParser getJccVerilogParser() throws org.netbeans.modules.parsing.spi.ParseException {
            if (!valid) {
                throw new org.netbeans.modules.parsing.spi.ParseException();
            }
            return jccVerilogParser;
        }

        @Override
        protected void invalidate() {
            valid = false;
        }

        @Override
        public List<? extends Error> getDiagnostics() {
            List<Error> listErrors = new ArrayList<Error>();
            return listErrors;
        }
   }

}

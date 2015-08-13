/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koltem.veriloglanguage.parser;

import com.koltem.jccverilogparser.ParseException;
import com.koltem.jccverilogparser.Token;
import com.koltem.veriloglanguage.parser.VerilogParser.VerilogParserResult;
import java.util.ArrayList;
import java.util.List;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.StyledDocument;
import org.netbeans.modules.parsing.spi.Parser.Result;
import org.netbeans.modules.parsing.spi.ParserResultTask;
import org.netbeans.modules.parsing.spi.Scheduler;
import org.netbeans.modules.parsing.spi.SchedulerEvent;
import org.netbeans.spi.editor.hints.ErrorDescription;
import org.netbeans.spi.editor.hints.ErrorDescriptionFactory;
import org.netbeans.spi.editor.hints.HintsController;
import org.netbeans.spi.editor.hints.Severity;
import org.openide.text.NbDocument;
import org.openide.util.Exceptions;

/**
 *
 * @author Koren
 */
public class VerilogSyntaxErrorHighlightingTask extends ParserResultTask {

    @Override
    public void run(Result result, SchedulerEvent event) {
        try {
            VerilogParserResult verilogResult = (VerilogParserResult) result;
            List<ParseException> syntaxErrors = verilogResult.getJccVerilogParser().syntaxErrors;
            Document document = result.getSnapshot().getSource().getDocument(false);
            List<ErrorDescription> errors = new ArrayList<ErrorDescription>();
            for (ParseException syntaxError : syntaxErrors) {
                Token token = syntaxError.currentToken;
                token.printInfo();
                System.out.println(document.getText(0, 1));
                int start = NbDocument.findLineOffset((StyledDocument) document, token.beginLine - 1) + token.beginColumn -1;
                int end = NbDocument.findLineOffset((StyledDocument) document, token.endLine -1 ) + token.endColumn;
                ErrorDescription errorDescription = ErrorDescriptionFactory.createErrorDescription(
                        Severity.ERROR,
                        syntaxError.getMessage(),
                        document, document.createPosition(start),
                        document.createPosition(end)
                );
                errors.add(errorDescription);
            }
            HintsController.setErrors(document, "system-verilog", errors);
        } catch (BadLocationException ex1) {
            Exceptions.printStackTrace(ex1);
        } catch (org.netbeans.modules.parsing.spi.ParseException ex1) {
            Exceptions.printStackTrace(ex1);
        }
    }

    @Override
    public int getPriority() {
        return 100;
    }

    @Override
    public Class getSchedulerClass() {
        return Scheduler.EDITOR_SENSITIVE_TASK_SCHEDULER;
    }

    @Override
    public void cancel() {
    }
}

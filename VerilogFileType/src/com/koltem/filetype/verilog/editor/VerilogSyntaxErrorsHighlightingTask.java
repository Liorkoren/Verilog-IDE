package com.koltem.filetype.verilog.editor;

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
 * @author ranga
 */
public class VerilogSyntaxErrorsHighlightingTask extends ParserResultTask 
{

    public VerilogSyntaxErrorsHighlightingTask() {
    }

    public void run(Result result, SchedulerEvent event) {
        try {
            VerilogParser.VerilogEditorParserResult verilogResult = (VerilogParser.VerilogEditorParserResult) result;
            List<SyntaxError> syntaxErrors = verilogResult.getMyParser().getSyntaxErrors();
            Document document = result.getSnapshot().getSource().getDocument(false);
            List<ErrorDescription> errors = new ArrayList<ErrorDescription>();
            for (SyntaxError syntaxError : syntaxErrors) {
                String message = syntaxError.getMessage();

                int line = syntaxError.getLine();
                if (line <= 0) {
                    continue;
                }
                ErrorDescription errorDescription = ErrorDescriptionFactory.createErrorDescription(
                        Severity.ERROR,
                        message,
                        document,
                        line);
                errors.add(errorDescription);
            }
            HintsController.setErrors(document, "v", errors);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int getPriority() {
        return 100;
    }

    public Class<? extends Scheduler> getSchedulerClass() {
        return Scheduler.EDITOR_SENSITIVE_TASK_SCHEDULER;
    }

    public void cancel() {
    }
}


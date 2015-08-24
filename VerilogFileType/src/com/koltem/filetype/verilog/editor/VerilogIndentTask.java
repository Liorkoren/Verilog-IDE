package com.koltem.filetype.verilog.editor;

import javax.swing.text.BadLocationException;
import org.netbeans.modules.editor.indent.spi.Context;
import org.netbeans.modules.editor.indent.spi.ExtraLock;
import org.netbeans.modules.editor.indent.spi.IndentTask;
import org.openide.awt.StatusDisplayer;

/**
 *
 * @author ranga
 */
public class VerilogIndentTask implements IndentTask
{
    private static final int INDENT_SIZE = 2;
    
    private Context context;

    public VerilogIndentTask(Context context)
    {
        this.context = context;
    }

    @Override
    public void reindent()
            throws BadLocationException
    {
        try {
            //this provides the character position (and not line number) of current line (new line after enter is pressed)
            int curLineStart = context.lineStartOffset(context.caretOffset());
            //this provides the indent used in the new line
            int curIndent = context.lineIndent(curLineStart);
            //provides the character number start of previous line
            int prevLineStart = context.lineStartOffset(curLineStart - curIndent - 1);
            //gets the previous line indent
            int prevLineIndent = context.lineIndent(context.lineStartOffset(prevLineStart));
            
            //gets the previous line's content. Use -1 to remove the newline added
            String prevLineContent = context.document().getText(prevLineStart , curLineStart - prevLineStart - 1);
            
//            System.out.printf("curlineStart(%d), curIndent(%d)\n", curLineStart, curIndent);
//            System.out.printf("prev(%d), endOffset(%d)\n" , prevLineStart, context.endOffset());
//            System.out.println("prev line content:" + prevLineContent);
            
            prevLineContent = prevLineContent.trim();
            //if any of these condition satisfy, increase the indent
            if(prevLineContent.startsWith("module")
                    || prevLineContent.startsWith("generate")
                    || prevLineContent.startsWith("for")
                    || prevLineContent.startsWith("initial")
                    || prevLineContent.endsWith("begin")) {
                context.modifyIndent(curLineStart , prevLineIndent + INDENT_SIZE);
            }
            //otherwise, maintain the previous line's indent
            else {
                context.modifyIndent(curLineStart , prevLineIndent);
            }
        }
        catch(BadLocationException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public ExtraLock indentLock()
    {
        return null;
    }
}

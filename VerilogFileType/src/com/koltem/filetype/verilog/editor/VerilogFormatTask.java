package com.koltem.filetype.verilog.editor;

import java.io.*;
import java.util.List;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import org.netbeans.modules.editor.indent.api.IndentUtils;
import org.netbeans.modules.editor.indent.spi.*;
import org.openide.util.Exceptions;

/**
 *
 * @author ranga
 */
class VerilogFormatTask implements ReformatTask
{
    private static final String NEWLINE = "\n";
    private static final String SPACE = " ";
    
    private final Context context;

    public VerilogFormatTask(Context context)
    {
        this.context = context;
    }

    @Override
    public void reformat()
            throws BadLocationException
    {
        Context.Region region = context.indentRegions().get(0);
        
        int startOffset = region.getStartOffset();
        int endOffset = region.getEndOffset();
        
        Document document = context.document();
        String textToFormat = document.getText(startOffset, endOffset - startOffset);
        
        int indentLevel = 0;
        /* If the format is for a portion of the document, then need to calculdate the indent counts accordingly.
         * Get the beginning line indent and divide by indent size which will give us the indent counts
         *
         */
        indentLevel = IndentUtils.lineIndent(document, startOffset) / Util.getIndentSize(document);

        /*
         * Get the text and then read line by line.
         * trim the text first and see if they indicate beginnings. If so, increase the indentLevel
         * Then, do the actual indendation. Remember that we removed the spaces earlier with trim.
         * Just loop through and add spaces for the number of calculated indents 
         * Then, check for start values and if needed decrease the indent level
         * 
         * IMPORTANT:
         * The order is important here. if ending is found, indent level should be decreased before doing the indendation 
         * as it is needed to indent the current line itself.
         * for start points, only the next line is to be intended. So, do the indendation first and then increase 
         * the indent level
         */
        StringBuilder sb = new StringBuilder();
        String line = null;
        LineNumberReader br = new LineNumberReader(new StringReader(textToFormat));
        try {
            while((line=br.readLine()) != null) {
                line = line.trim();
                
                //check for endings before doing indendation
                if(line.equals("end")
                        || line.equals("endgenerate")
                        || line.equals("endmodule")) {
                    indentLevel--;
                }                
                
                //now, do the indendation
                //indendation is simply the indent level * indent size
                //for example, if indent size is 4, then a for loop (which is 2 levels)
                //should start at 2 * 4 = 8
                int indent = indentLevel * Util.getIndentSize(document);
                for (int i = 0; i < indent; i++) {
                    sb.append(SPACE);
                }
                sb.append(line);
                sb.append(NEWLINE);
                
                //after indendation is done, check for start values and adjust the indent
                if(line.startsWith("module")
                        || line.startsWith("generate")
                        || line.startsWith("for")
                        || line.startsWith("initial")
                        || line.endsWith("begin")) {
                    indentLevel++;
                }
            }
        }
        catch (IOException ex) {
            Exceptions.printStackTrace(ex);
            //rethrow?
            //throw new BadLocationException(line, br.getLineNumber());
        }
        
        document.remove(startOffset, endOffset - startOffset);
        document.insertString(startOffset, sb.toString(), null);

    }

    @Override
    public ExtraLock reformatLock()
    {
        return null;
    }
    
}

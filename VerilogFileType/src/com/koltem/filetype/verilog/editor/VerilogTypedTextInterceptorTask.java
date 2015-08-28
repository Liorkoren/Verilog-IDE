package com.koltem.filetype.verilog.editor;

import javax.swing.text.*;
import org.netbeans.editor.Utilities;
import org.netbeans.spi.editor.typinghooks.TypedTextInterceptor;

/**
 *
 * @author ranga
 */
class VerilogTypedTextInterceptorTask implements TypedTextInterceptor
{

    public VerilogTypedTextInterceptorTask()
    {
    }

    @Override
    public boolean beforeInsert(Context cntxt)
            throws BadLocationException
    {
        return false;
    }

    @Override
    public void insert(MutableContext mc)
            throws BadLocationException
    {
    }

    @Override
    public void afterInsert(Context context)
            throws BadLocationException
    {
        //get the row start and row end with help of utilities
        int rowStart = Utilities.getRowStart(context.getComponent(), context.getOffset());
        int rowEnd = Utilities.getRowEnd(context.getComponent(), context.getOffset());
        
        final Document document = context.getDocument();
        final int strLen = rowEnd-rowStart;
        //now, get the word from the document and trim it
        String text = document.getText(rowStart, strLen).trim();
        
        if(text.equals("end")
                || text.equals("endgenerate")
                || text.equals("endmodule")) {

            //doc.insertString(start, textWriter.toString(), null);
            //just remove the extra indendation
            //CAUTION: Do check if the characters to be removed are spaces!
            String toRemove = document.getText(rowStart, Util.getIndentSize(document));
            if(toRemove.trim().isEmpty()) {
                document.remove(rowStart, Util.getIndentSize(document));
            }
        }        
    }

    @Override
    public void cancelled(Context cntxt)
    {
    }
    
}

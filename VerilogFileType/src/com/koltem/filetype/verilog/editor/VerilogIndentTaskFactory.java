package com.koltem.filetype.verilog.editor;

import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.modules.editor.indent.spi.Context;
import org.netbeans.modules.editor.indent.spi.IndentTask;

/**
 *
 * @author ranga
 */
@MimeRegistration(mimeType = "text/x-v", service = IndentTask.Factory.class)
public class VerilogIndentTaskFactory implements IndentTask.Factory
{

    @Override
    public IndentTask createTask(Context context)
    {
        return new VerilogIndentTask(context);
    }
}

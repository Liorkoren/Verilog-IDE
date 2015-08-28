package com.koltem.filetype.verilog.editor;

import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.modules.editor.indent.spi.Context;
import org.netbeans.modules.editor.indent.spi.ReformatTask;
import org.netbeans.spi.editor.typinghooks.TypedTextInterceptor;

/**
 *
 * @author ranga
 */
@MimeRegistration(mimeType = "text/x-v", service = ReformatTask.Factory.class)
public class VerilogFormatTaskFactory implements ReformatTask.Factory
{

    @Override
    public ReformatTask createTask(Context context)
    {
        return new VerilogFormatTask(context);
    }
    
}

package com.koltem.filetype.verilog.editor;

import org.netbeans.api.editor.mimelookup.*;
import org.netbeans.modules.editor.indent.spi.IndentTask;
import org.netbeans.spi.editor.typinghooks.TypedTextInterceptor;
import org.netbeans.modules.editor.indent.spi.Context;

/**
 *
 * @author ranga
 */
@MimeRegistration(mimeType = "text/x-v", service = TypedTextInterceptor.Factory.class)
public class VerilogTypedTextInterceptorFactory implements TypedTextInterceptor.Factory
{

    @Override
    public TypedTextInterceptor createTypedTextInterceptor(MimePath mp)
    {
        return new VerilogTypedTextInterceptorTask();
    }
    
}

package com.koltem.filetype.verilog.editor;

import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.spi.editor.bracesmatching.BracesMatcher;
import org.netbeans.spi.editor.bracesmatching.BracesMatcherFactory;
import org.netbeans.spi.editor.bracesmatching.MatcherContext;
import org.netbeans.spi.editor.bracesmatching.support.BracesMatcherSupport;

/**
 *
 * @author ranga
 */
@MimeRegistration(mimeType = "text/x-v", service = BracesMatcherFactory.class)
public class VerilogBracesMatcherFactory implements BracesMatcherFactory
{

    @Override
    public BracesMatcher createMatcher(MatcherContext context)
    {
        return BracesMatcherSupport.defaultMatcher(context, -1, -1);
    }
}

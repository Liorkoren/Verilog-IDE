package com.koltem.filetype.verilog.editor;

import java.util.Collection;
import java.util.Collections;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.modules.parsing.api.Snapshot;
import org.netbeans.modules.parsing.spi.TaskFactory;

/**
 *
 * @author ranga
 */
@MimeRegistration(mimeType = "text/x-v", service = TaskFactory.class)
public class VerilogSyntaxErrorHighlightingTaskFactory extends TaskFactory
{

    @Override
    public Collection create(Snapshot snapshot)
    {
        return Collections.singleton(new VerilogSyntaxErrorsHighlightingTask());
    }
}

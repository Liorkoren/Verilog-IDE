/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koltem.veriloglanguage.parser;

import java.util.Collection;
import java.util.Collections;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.modules.parsing.api.Snapshot;
import org.netbeans.modules.parsing.spi.TaskFactory;

/**
 *
 * @author Koren
 */
@MimeRegistration(mimeType = "text/x-v", service = TaskFactory.class)
public class VerilogSyntaxErrorHighlightingTaskFactory extends TaskFactory {

    @Override
    public Collection create(Snapshot snapshot) {
        return Collections.singleton(new VerilogSyntaxErrorHighlightingTask());
    }
}

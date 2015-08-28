package com.koltem.filetype.verilog.editor;

import javax.swing.text.Document;
import org.netbeans.api.editor.settings.SimpleValueNames;
import org.netbeans.modules.editor.indent.spi.CodeStylePreferences;

/**
 *
 * @author ranga
 */
public class Util
{
    private static final int DEFAULT_INDENT_SIZE = 2;    
    static int getIndentSize(Document document) {
        //get indent size from user preferences - Tools-->Options-->Editor-->Formatting
        return CodeStylePreferences.get(document).getPreferences().getInt(
                SimpleValueNames.INDENT_SHIFT_WIDTH, DEFAULT_INDENT_SIZE);
    }
}

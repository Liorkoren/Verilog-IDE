package com.koltem.filetype.verilog.editor;

import org.netbeans.api.lexer.Language;
import org.netbeans.modules.csl.spi.DefaultLanguageConfig;
import org.netbeans.modules.csl.spi.LanguageRegistration;
import org.netbeans.modules.parsing.spi.Parser;

/**
 *
 * @author Koren
 */
@LanguageRegistration(mimeType = "text/x-v")
public class VerilogLanguage extends DefaultLanguageConfig {

    @Override
    public Language getLexerLanguage() {
        return VerilogTokenId.getLanguage();
    }

    @Override
    public String getDisplayName() {
        return "Verilog";
    }

    @Override
    public Parser getParser() {
        return new VerilogParser();
    }
}

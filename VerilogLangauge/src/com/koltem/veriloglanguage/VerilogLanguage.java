/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koltem.veriloglanguage;

import com.koltem.veriloglanguage.lexer.VerilogTokenId;
import com.koltem.veriloglanguage.parser.VerilogParser;
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

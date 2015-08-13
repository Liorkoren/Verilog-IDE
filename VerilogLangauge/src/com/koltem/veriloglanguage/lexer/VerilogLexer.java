/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koltem.veriloglanguage.lexer;

import com.koltem.jccveriloglexer.JccVerilogLexerCharStream;
import com.koltem.jccveriloglexer.JccVerilogLexerTokenManager;
import com.koltem.jccveriloglexer.Token;
import org.netbeans.spi.lexer.Lexer;
import org.netbeans.spi.lexer.LexerRestartInfo;

/**
 *
 * @author Koren
 */
public class VerilogLexer implements Lexer<VerilogTokenId> {

    private LexerRestartInfo<VerilogTokenId> info;
    private JccVerilogLexerTokenManager javaParserTokenManager;

    VerilogLexer(LexerRestartInfo<VerilogTokenId> info) {
        this.info = info;
        JccVerilogLexerCharStream stream = new JccVerilogLexerCharStream(info.input());
        javaParserTokenManager = new JccVerilogLexerTokenManager(stream);
    }

    @Override
    public org.netbeans.api.lexer.Token<VerilogTokenId> nextToken() {
        Token token = javaParserTokenManager.getNextToken();
        if (info.input().readLength() < 1) {
            return null;
        }
        return info.tokenFactory().createToken(VerilogLanguageHierarchy.getToken(token.kind));
    }

    @Override
    public Object state() {
        return null;
    }

    @Override
    public void release() {
    }
}

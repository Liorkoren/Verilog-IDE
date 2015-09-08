package com.koltem.filetype.verilog.editor;

import com.koltem.filetype.verilog.antlr.Verilog2001Lexer;
import org.antlr.v4.runtime.Token;
import org.netbeans.spi.lexer.Lexer;
import org.netbeans.spi.lexer.LexerRestartInfo;

/**
 *
 * @author ranga
 */
public class VerilogLexer implements Lexer<VerilogTokenId>
{
    private LexerRestartInfo<VerilogTokenId> info;
    
    //this is the antlr generated lexer
    private Verilog2001Lexer verilogLexer;
    
    public VerilogLexer(LexerRestartInfo<VerilogTokenId> info) {
        this.info = info;

        AntlrCharStream charStream = new AntlrCharStream(info.input(), "VerilogEditor");
        verilogLexer = new Verilog2001Lexer(charStream);
    }
    
    @Override
    public org.netbeans.api.lexer.Token<VerilogTokenId> nextToken()
    {
        Token token = verilogLexer.nextToken();
        if (token.getType() != Verilog2001Lexer.EOF) {
            VerilogTokenId tokenId = VerilogLanguageHierarchy.getToken(token.getType());
            return info.tokenFactory().createToken(tokenId);
        }
        return null;        
    }

    @Override
    public Object state()
    {
        return null;
    }

    @Override
    public void release()
    {
    }
    
}
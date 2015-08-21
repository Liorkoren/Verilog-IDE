package com.koltem.filetype.verilog;

import com.koltem.filetype.verilog.antlr.*;
import java.io.*;
import java.util.ArrayList;
import org.antlr.v4.runtime.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author ranga
 */
public class VerilogParserTest
{

    public VerilogParserTest()
    {
    }

    /**
     * Test of parse method, of class VerilogParser.
     */
    @Test
    public void testParse() throws IOException
    {
        CharStream input = new ANTLRInputStream(getClass().getResourceAsStream("/com/koltem/filetype/verilog/no_errorScript.v"));
        Lexer lexer = new Verilog2001Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Verilog2001Parser verilogParser = new Verilog2001Parser(tokens);
        verilogParser.description();
        
        //no errors
        assertEquals(0, verilogParser.getNumberOfSyntaxErrors());
    }

    /**
     * Test of getResult method, of class VerilogParser.
     */
    @Test
    public void testParseError() throws IOException
    {
        CharStream input = new ANTLRInputStream(getClass().getResourceAsStream("/com/koltem/filetype/verilog/errorScript.v"));
        Lexer lexer = new Verilog2001Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Verilog2001Parser verilogParser = new Verilog2001Parser(tokens);
//        verilogParser.addErrorListener(new BaseErrorListener() {
//            @Override
//            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
//                                    int line, int charPositionInLine,
//                                    String msg, RecognitionException e)
//            {
//
//                String sourceName = recognizer.getInputStream().getSourceName();
//                if (!sourceName.isEmpty()) {
//                    sourceName = String.format("%s:%d:%d: ", sourceName, line, charPositionInLine);
//                }
//                System.err.println(sourceName + "line " + line + ":" + charPositionInLine + " " + msg);
//                //System.err.println("line " + line + ":" + charPositionInLine + " " + msg);
//            }
//        });
        
        verilogParser.description();
        
        //gives only one syntax error?
        assertEquals(1, verilogParser.getNumberOfSyntaxErrors());
    }

}

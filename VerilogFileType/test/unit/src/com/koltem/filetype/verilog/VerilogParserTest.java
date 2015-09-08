package com.koltem.filetype.verilog;

import com.koltem.filetype.verilog.antlr.Verilog2001Lexer;
import com.koltem.filetype.verilog.antlr.Verilog2001Parser;
import com.koltem.filetype.verilog.editor.VerilogContextTreeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author ranga
 */
public class VerilogParserTest {

    public VerilogParserTest() {
    }

    public void TestSyntaxErrors(String fileName, List<String> expectedMessages) throws IOException {
        CharStream input = new ANTLRInputStream(getClass().getResourceAsStream("/com/koltem/filetype/verilog/" + fileName));
        Lexer lexer = new Verilog2001Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Verilog2001Parser verilogParser = new Verilog2001Parser(tokens);
        // Connect the Listener to the output String
        VerilogContextTreeListener contextTreeListener = new VerilogContextTreeListener(verilogParser);
        verilogParser.addParseListener(contextTreeListener);
        // Parse the file
        verilogParser.description();

        // Expect same amount of syntax errors
        assertEquals(expectedMessages.size(), contextTreeListener.getSyntaxErrors().size());
        int min = Math.min(expectedMessages.size(), contextTreeListener.getSyntaxErrors().size());
        for (int i = 0; i < min; i++) {
            String output = contextTreeListener.getSyntaxErrors().get(i).getMessage();
            String expected = expectedMessages.get(i);
            assertEquals(expected, output);
        }
    }

    /**
     * Test parameter with "<IDENTIFIER> = ".
     */
    @Test
    public void testParameterEqualNoValue() throws IOException {
        String msg = "";
        ArrayList<String> expected = new ArrayList<String>();
        msg += "Parameter decleration is missing a constant expression.\n";
        msg += "parameter <IDENTIFIER> = <CONSTANT EXPRESSION>.\n";
        msg += "IEEE Std 1364-2005 [4.10.1]\n";
        expected.add(msg);
        TestSyntaxErrors("TestParameterEqualNoValue.v", expected);
    }

    /**
     * Test of parse method, of class VerilogParser.
     */
    @Test
    public void testParameterNoDefault() throws IOException {
        String output;

        String msg = "Parameter decleration is missing a mandatory default value.\n";
        msg += "parameter <IDENTIFIER> = <CONSTANT EXPRESSION>.\n";
        msg += "IEEE Std 1364-2005 [4.10.1]\n";
        String expected = msg;

        CharStream input = new ANTLRInputStream(getClass().getResourceAsStream("/com/koltem/filetype/verilog/TestParameterNoDefault.v"));
        Lexer lexer = new Verilog2001Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Verilog2001Parser verilogParser = new Verilog2001Parser(tokens);
        // Connect the Listener to the output String
        VerilogContextTreeListener contextTreeListener = new VerilogContextTreeListener(verilogParser);
        verilogParser.addParseListener(contextTreeListener);
        verilogParser.description();
        //One error
        assertEquals(1, contextTreeListener.getSyntaxErrors().size());
        output = contextTreeListener.getSyntaxErrors().get(0).getMessage();
        assertEquals(output, expected);
    }

    /**
     * Test of getResult method, of class VerilogParser.
     */
    @Test
    public void testParseError() throws IOException {
        CharStream input = new ANTLRInputStream(getClass().getResourceAsStream("/com/koltem/filetype/verilog/errorScript.v"));
        Lexer lexer = new Verilog2001Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Verilog2001Parser verilogParser = new Verilog2001Parser(tokens);
        verilogParser.description();

        //gives only one syntax error?
        assertEquals(1, verilogParser.getNumberOfSyntaxErrors());
    }

}

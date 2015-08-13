/*
 * This file and the related grammars are essentially the java/polydiff
 * found within examples-v3.zip downloadable from antlr.org. The only
 * change I have made is to add Java package declarations, and imports,
 * and to call org.antlr.runtime.ParserRuleReturnScope.getTree()
 * in place of direct member access to the 'tree' member of the
 * <rule>_return objects.
 */
package com.koltem.antlr;

import com.koltem.antlr.grammer.verilog.Verilog2001Lexer;
import com.koltem.antlr.grammer.verilog.Verilog2001Parser;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import static org.stringtemplate.v4.misc.Misc.newline;

public class Control {

    public static Verilog2001Parser.DescriptionContext r ;

    public static void CreateAST(String[] args) throws Exception {

        CharStream input = null;
        if (args.length > 0) {
            //This is where a real application would open the file.
            System.out.println("Opening: " + args[0] + "." + newline);
            input = new ANTLRFileStream(args[0]);
        } else {
            input = new ANTLRInputStream(System.in);
        }

        // BUILD AST
        Verilog2001Lexer lex = new Verilog2001Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lex);
        Verilog2001Parser parser = new Verilog2001Parser(tokens);
        r = parser.description();
        System.out.println("tree=" + r.getText());
        

    }

}

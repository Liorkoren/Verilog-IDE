package com.koltem.filetype.verilog.editor;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.netbeans.spi.lexer.LanguageHierarchy;
import org.netbeans.spi.lexer.Lexer;
import org.netbeans.spi.lexer.LexerRestartInfo;

/**
 *
 * @author Koren
 */
public class VerilogLanguageHierarchy extends LanguageHierarchy<VerilogTokenId>
{

    private static List<VerilogTokenId> tokens;
    private static Map<Integer, VerilogTokenId> idToToken;

    private static void init()
    {
        tokens = Arrays.<VerilogTokenId>asList(new VerilogTokenId[]{
            new VerilogTokenId("EOF", "whitespace", 0),
            new VerilogTokenId("CONFIG", "keyword", 1),
            new VerilogTokenId("SEMICOLON", "separator", 2),
            new VerilogTokenId("END_CONFIG", "keyword", 3),
            new VerilogTokenId("DESIGN", "keyword", 4),
            new VerilogTokenId("DOT", "separator", 5),
            new VerilogTokenId("DEFAULT", "keyword", 6),
            new VerilogTokenId("INSTANCE", "keyword", 7),
            new VerilogTokenId("LIBLIST", "keyword", 8),
            new VerilogTokenId("CELL", "keyword", 9),
            new VerilogTokenId("USE", "keyword", 10),
            new VerilogTokenId("COLON_CONFIG", "keyword", 11),
            new VerilogTokenId("END_MODULE", "keyword", 12),
            new VerilogTokenId("MODULE", "keyword", 13),
            new VerilogTokenId("MACRO_MODULE", "keyword", 14),
            new VerilogTokenId("HASH", "operator", 15),
            new VerilogTokenId("OPEN_BRACE", "separator", 16),
            new VerilogTokenId("COMMA", "separator", 17),
            new VerilogTokenId("CLOSE_BRACE", "separator", 18),
            new VerilogTokenId("CURLY_OPEN_BRACE", "separator", 19),
            new VerilogTokenId("CURLY_CLOSE_BRACE", "separator", 20),
            new VerilogTokenId("SQUARE_OPEN_BRACE", "separator", 21),
            new VerilogTokenId("SQUARE_CLOSE_BRACE", "separator", 22),
            new VerilogTokenId("DEFPARAM", "keyword", 23),
            new VerilogTokenId("LOCALPARAM", "keyword", 24),
            new VerilogTokenId("SIGNED", "keyword", 25),
            new VerilogTokenId("INTEGER", "keyword", 26),
            new VerilogTokenId("REAL", "keyword", 27),
            new VerilogTokenId("REALTIME", "keyword", 28),
            new VerilogTokenId("TIME", "keyword", 29),
            new VerilogTokenId("PARAMETER", "keyword", 30),
            new VerilogTokenId("SPECPARAM", "keyword", 31),
            new VerilogTokenId("INOUT", "keyword", 32),
            new VerilogTokenId("INPUT", "keyword", 33),
            new VerilogTokenId("OUTPUT", "keyword", 34),
            new VerilogTokenId("REG", "keyword", 35),
            new VerilogTokenId("EVENT", "keyword", 36),
            new VerilogTokenId("GENVAR", "keyword", 37),
            new VerilogTokenId("TRIREG", "keyword", 38),
            new VerilogTokenId("VECTORED", "keyword", 39),
            new VerilogTokenId("SCALARED", "keyword", 40),
            new VerilogTokenId("SUPPLY0", "keyword", 41),
            new VerilogTokenId("SUPPLY1", "keyword", 42),
            new VerilogTokenId("TRI", "keyword", 43),
            new VerilogTokenId("TRIAND", "keyword", 44),
            new VerilogTokenId("TRIOR", "keyword", 45),
            new VerilogTokenId("TRI0", "keyword", 46),
            new VerilogTokenId("TRI1", "keyword", 47),
            new VerilogTokenId("WIRE", "keyword", 48),
            new VerilogTokenId("WAND", "keyword", 49),
            new VerilogTokenId("WOR", "keyword", 50),
            new VerilogTokenId("EQUALS", "operator", 51),
            new VerilogTokenId("HIGHZ1", "keyword", 52),
            new VerilogTokenId("HIGHZ0", "keyword", 53),
            new VerilogTokenId("STRONG0", "keyword", 54),
            new VerilogTokenId("PULL0", "keyword", 55),
            new VerilogTokenId("WEAK0", "keyword", 56),
            new VerilogTokenId("STRONG1", "keyword", 57),
            new VerilogTokenId("PULL1", "keyword", 58),
            new VerilogTokenId("WEAK1", "keyword", 59),
            new VerilogTokenId("SMALL", "keyword", 60),
            new VerilogTokenId("MEDIUM", "keyword", 61),
            new VerilogTokenId("LARGE", "keyword", 62),
            new VerilogTokenId("PATHPULSE", "keyword", 63),
            new VerilogTokenId("DOLLAR", "operator", 64),
            new VerilogTokenId("COLON", "separator", 65),
            new VerilogTokenId("FUNCTION", "keyword", 66),
            new VerilogTokenId("AUTOMATIC", "keyword", 67),
            new VerilogTokenId("ENDFUNCTION", "keyword", 68),
            new VerilogTokenId("TASK", "keyword", 69),
            new VerilogTokenId("ENDTASK", "keyword", 70),
            new VerilogTokenId("PULLDOWN", "keyword", 71),
            new VerilogTokenId("PULLUP", "keyword", 72),
            new VerilogTokenId("CMOS", "keyword", 73),
            new VerilogTokenId("RCMOS", "keyword", 74),
            new VerilogTokenId("BUFIF0", "keyword", 75),
            new VerilogTokenId("BUFIF1", "keyword", 76),
            new VerilogTokenId("NOTIF0", "keyword", 77),
            new VerilogTokenId("NOTIF1", "keyword", 78),
            new VerilogTokenId("NMOS", "keyword", 79),
            new VerilogTokenId("PMOS", "keyword", 80),
            new VerilogTokenId("RNMOS", "keyword", 81),
            new VerilogTokenId("RPMOS", "keyword", 82),
            new VerilogTokenId("AND", "keyword", 83),
            new VerilogTokenId("NAND", "keyword", 84),
            new VerilogTokenId("OR", "keyword", 85),
            new VerilogTokenId("NOR", "keyword", 86),
            new VerilogTokenId("XOR", "keyword", 87),
            new VerilogTokenId("XNOR", "keyword", 88),
            new VerilogTokenId("BUF", "keyword", 89),
            new VerilogTokenId("NOT", "keyword", 90),
            new VerilogTokenId("TRANIF0", "keyword", 91),
            new VerilogTokenId("TRANIF1", "keyword", 92),
            new VerilogTokenId("RTRANIF1", "keyword", 93),
            new VerilogTokenId("RTRANIF0", "keyword", 94),
            new VerilogTokenId("TRAN", "keyword", 95),
            new VerilogTokenId("RTRAN", "keyword", 96),
            new VerilogTokenId("GENERATE", "keyword", 97),
            new VerilogTokenId("END_GENERATE", "keyword", 98),
            new VerilogTokenId("IF", "keyword", 99),
            new VerilogTokenId("ELSE", "keyword", 100),
            new VerilogTokenId("CASE", "keyword", 101),
            new VerilogTokenId("ENDCASE", "keyword", 102),
            new VerilogTokenId("FOR", "keyword", 103),
            new VerilogTokenId("BEGIN", "keyword", 104),
            new VerilogTokenId("END", "keyword", 105),
            new VerilogTokenId("ASSIGN", "keyword", 106),
            new VerilogTokenId("INITIAL", "keyword", 107),
            new VerilogTokenId("ALWAYS", "keyword", 108),
            new VerilogTokenId("LT_EQUALS", "operator", 109),
            new VerilogTokenId("DEASSIGN", "keyword", 110),
            new VerilogTokenId("FORCE", "keyword", 111),
            new VerilogTokenId("RELEASE", "keyword", 112),
            new VerilogTokenId("FORK", "keyword", 113),
            new VerilogTokenId("JOIN", "keyword", 114),
            new VerilogTokenId("REPEAT", "keyword", 115),
            new VerilogTokenId("DISABLE", "keyword", 116),
            new VerilogTokenId("AT_THE_RATE", "operator", 117),
            new VerilogTokenId("ASTERIK", "operator", 118),
            new VerilogTokenId("POINTER", "operator", 119),
            new VerilogTokenId("POSEDGE", "keyword", 120),
            new VerilogTokenId("NEGEDGE", "keyword", 121),
            new VerilogTokenId("WAIT", "keyword", 122),
            new VerilogTokenId("CASEZ", "keyword", 123),
            new VerilogTokenId("CASEX", "keyword", 124),
            new VerilogTokenId("FOREVER", "keyword", 125),
            new VerilogTokenId("WHILE", "keyword", 126),
            new VerilogTokenId("SPECIFY", "keyword", 127),
            new VerilogTokenId("END_SPECIFY", "keyword", 128),
            new VerilogTokenId("PULSESTYLE_ONEVENT", "keyword", 129),
            new VerilogTokenId("PULSESTYLE_ONDETECT", "keyword", 130),
            new VerilogTokenId("SHOW_CANCELLED", "keyword", 131),
            new VerilogTokenId("NO_SHOW_CANCELLED", "keyword", 132),
            new VerilogTokenId("EQUALS_GT", "operator", 133),
            new VerilogTokenId("GT_STAR", "operator", 134),
            new VerilogTokenId("IFNONE", "keyword", 135),
            new VerilogTokenId("PLUS", "operator", 136),
            new VerilogTokenId("MINUS", "operator", 137),
            new VerilogTokenId("PLUS_COLON", "operator", 138),
            new VerilogTokenId("MINUS_COLON", "operator", 139),
            new VerilogTokenId("QUESTION_MARK", "operator", 140),
            new VerilogTokenId("EXCLAMATION", "operator", 141),
            new VerilogTokenId("TILDA", "operator", 142),
            new VerilogTokenId("AND_SYMBOL", "operator", 143),
            new VerilogTokenId("TILDA_AND", "operator", 144),
            new VerilogTokenId("PIPE", "operator", 145),
            new VerilogTokenId("TILDA_PIPE", "operator", 146),
            new VerilogTokenId("CIRCUMFLEX", "operator", 147),
            new VerilogTokenId("TILDA_CIRCUMFLEX", "operator", 148),
            new VerilogTokenId("CIRCUMFLEX_TILDA", "operator", 149),
            new VerilogTokenId("SLASH", "operator", 150),
            new VerilogTokenId("PERCENTAGE", "operator", 151),
            new VerilogTokenId("DOUBLE_EQUALS", "operator", 152),
            new VerilogTokenId("EXCLAMATION_EQUALS", "operator", 153),
            new VerilogTokenId("TRIPLE_EQUALS", "operator", 154),
            new VerilogTokenId("EXCLAMATION_DOUBLE_EQUALS", "operator", 155),
            new VerilogTokenId("DOUBLE_AMPERSAND", "operator", 156),
            new VerilogTokenId("DOUBLE_PIPE", "operator", 157),
            new VerilogTokenId("DOUBLE_ASTERIK", "operator", 158),
            new VerilogTokenId("LT", "operator", 159),
            new VerilogTokenId("GT", "operator", 160),
            new VerilogTokenId("GT_EQUALS", "operator", 161),
            new VerilogTokenId("DOUBLE_GT", "operator", 162),
            new VerilogTokenId("DOUBLE_LT", "operator", 163),
            new VerilogTokenId("TRIPLE_GT", "operator", 164),
            new VerilogTokenId("TRIPLE_LT", "operator", 165),
            new VerilogTokenId("REAL_NUMBER","number",166),
            new VerilogTokenId("DECIMAL_NUMBER","number",167),
            new VerilogTokenId("BINARY_NUMBER","number",168),
            new VerilogTokenId("OCTAL_NUMBER","number",169),
            new VerilogTokenId("HEX_NUMBER","number",170),
            new VerilogTokenId("STRING","string",171),
            new VerilogTokenId("ONE_LINE_COMMENT","comment",172),
            new VerilogTokenId("BLOCK_COMMENT","comment",173),
            new VerilogTokenId("ESCAPED_IDENTIFIER","whitespace",174),
            new VerilogTokenId("SIMPLE_IDENTIFIER","identifier",175),
            new VerilogTokenId("DOLLAR_IDENTIFIER","identifier",176),
            new VerilogTokenId("WHITE_SPACE","whitespace",177),
            
        });
        idToToken = new HashMap<Integer, VerilogTokenId>();
        for (VerilogTokenId token : tokens) {
            idToToken.put(token.ordinal(), token);
        }
    }

    static synchronized VerilogTokenId getToken(int id)
    {
        if (idToToken == null) {
            init();
        }
        return idToToken.get(id);
    }

    @Override
    protected synchronized Collection<VerilogTokenId> createTokenIds()
    {
        if (tokens == null) {
            init();
        }
        return tokens;
    }

    @Override
    protected synchronized Lexer<VerilogTokenId> createLexer(LexerRestartInfo<VerilogTokenId> info)
    {
        return new VerilogLexer(info);
    }

    @Override
    protected String mimeType()
    {
        return "text/x-v";
    }

}

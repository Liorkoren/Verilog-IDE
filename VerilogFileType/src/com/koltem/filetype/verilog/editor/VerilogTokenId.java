package com.koltem.filetype.verilog.editor;

import org.netbeans.api.lexer.Language;
import org.netbeans.api.lexer.TokenId;

/**
 *
 * @author Koren
 */
public class VerilogTokenId implements TokenId {

    public VerilogTokenId(String name, String primaryCategory, int id) {
        this.name = name;
        this.primaryCategory = primaryCategory;
        this.id = id;
    }
    private final String name;
    private final String primaryCategory;
    private final int id;

    @Override
    public String name() {
        return name;
    }

    @Override
    public int ordinal() {
        return id;
    }

    @Override
    public String primaryCategory() {
        return primaryCategory;
    }

    public static Language<VerilogTokenId> getLanguage() {
        return new VerilogLanguageHierarchy().language();
    }
}
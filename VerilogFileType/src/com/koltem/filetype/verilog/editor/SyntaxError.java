package com.koltem.filetype.verilog.editor;

import org.antlr.v4.runtime.RecognitionException;

/**
 *
 * @author ranga
 */
public class SyntaxError
{
    private int line;
    private int column;
    private String message;
    private RecognitionException exception;    

    public SyntaxError(int line, int column, String message, RecognitionException exception)
    {
        this.line = line;
        this.column = column;
        this.message = message;
        this.exception = exception;
    }

    public int getLine()
    {
        return line;
    }

    public void setLine(int line)
    {
        this.line = line;
    }

    public int getColumn()
    {
        return column;
    }

    public void setColumn(int column)
    {
        this.column = column;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public RecognitionException getException()
    {
        return exception;
    }

    public void setException(RecognitionException exception)
    {
        this.exception = exception;
    }

}

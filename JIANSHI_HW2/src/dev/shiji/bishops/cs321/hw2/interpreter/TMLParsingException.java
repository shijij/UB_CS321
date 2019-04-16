package dev.shiji.bishops.cs321.hw2.interpreter;

/**
 * Unchecked Runtime Exception, thrown when the TML program cannot be parsed.
 *
 * @author Shiji Jiang
 */
public class TMLParsingException extends RuntimeException{
    public TMLParsingException(String msg){
        super(msg);
    }
}
package dev.shiji.bishops.cs321.hw2.interpreter;

import java.util.Arrays;

/**
 * Represent the registers for the TML Interpreter
 *
 * @author Shiji Jiang
 */
public class Register {
    private int[] regs;
    private int size;
    public static final int MAX = 32;

    /**
     * Initialize a new Register List
     */
    public Register(){
        this(MAX);
    }

    /**
     * Initialize a new Register List with custom size
     */
    public Register(int size){
        this.size = size;
        regs = new int[size];
    }

    /**
     * Get value by index
     * @param index index
     * @throws TMLParsingException when it's invalid
     * @return value of the register
     */
    public int get(int index){
        checkIndex(index);
        return regs[index];
    }

    /**
     * Set value for index
     * @param index index
     * @param value value
     * @throws TMLParsingException when it's invalid
     */
    public void set(int index, int value){
        checkIndex(index);
        regs[index] = value;
    }

    /**
     * Check if the input index is valid
     * @throws TMLParsingException when it's invalid
     * @param index input index
     */
    private void checkIndex(int index){
        if(index < 0 || index >= size){
            throw new TMLParsingException("Invalid Register Index: " + index);
        }
    }

    @Override
    public String toString(){
        return Arrays.toString(regs);
    }
}

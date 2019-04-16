package dev.shiji.bishops.cs321.hw2.instructions;

import dev.shiji.bishops.cs321.hw2.interpreter.Register;

/**
 * Instruction for Printing Out
 */
public class Out extends Instruction {

    /**
     * Initialize a new Out instruction
     * @param param parameter list
     */
    public Out(int[] param){
        super("out", param);
    }

    /**
     * Helper for further child class
     * @param name name
     * @param param parameter list
     */
    public Out(String name, int[] param){
        super(name, param);
    }

    @Override
    public int execute(Register regs) {
        System.out.println(regs.get(parameters[0]));
        return -1;
    }
}

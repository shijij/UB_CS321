package dev.shiji.bishops.cs321.hw2.instructions;

import dev.shiji.bishops.cs321.hw2.interpreter.Register;

/**
 * Instruction for Subtract
 */
public class Sub extends Instruction {

    /**
     * Initialize a new Sub instruction
     * @param param parameter list
     */
    public Sub(int[] param){
        super("sub", param);
    }

    /**
     * Helper for further child class
     * @param name name
     * @param param parameter list
     */
    public Sub(String name, int[] param){
        super(name, param);
    }

    @Override
    public int execute(Register regs) {
        int result = regs.get(parameters[1]) - regs.get(parameters[2]);
        regs.set(parameters[0], result);
        return -1;
    }
}

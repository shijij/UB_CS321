package dev.shiji.bishops.cs321.hw2.instructions;

import dev.shiji.bishops.cs321.hw2.interpreter.Register;

/**
 * Instruction for Multiple
 */
public class Mul extends Instruction {

    /**
     * Initialize a new Mul instruction
     * @param param parameter list
     */
    public Mul(int[] param){
        super("mul", param);
    }

    /**
     * Helper for further child class
     * @param name name
     * @param param parameter list
     */
    public Mul(String name, int[] param){
        super(name, param);
    }

    @Override
    public int execute(Register regs) {
        int result = regs.get(parameters[1]) * regs.get(parameters[2]);
        regs.set(parameters[0], result);
        return -1;
    }
}

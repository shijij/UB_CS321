package dev.shiji.bishops.cs321.hw2.instructions;

import dev.shiji.bishops.cs321.hw2.interpreter.Register;

/**
 * Instruction for Division
 */
public class Div extends Instruction {

    /**
     * Initialize a new Div instruction
     * @param param parameter list
     */
    public Div(int[] param){
        super("div", param);
    }

    /**
     * Helper for further child class
     * @param name name
     * @param param parameter list
     */
    public Div(String name, int[] param){
        super(name, param);
    }

    @Override
    public int execute(Register regs) {

        int result = regs.get(parameters[1]) / regs.get(parameters[2]);
        regs.set(parameters[0], result);
        return -1;
    }
}

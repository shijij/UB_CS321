package dev.shiji.bishops.cs321.hw2.instructions;

import dev.shiji.bishops.cs321.hw2.interpreter.Register;

/**
 * Instruction for Bnz
 */
public class Bnz extends Instruction {

    /**
     * Initialize a new Bnz instruction
     * @param param parameter list
     */
    public Bnz(int[] param){
        super("bnz", param);
    }

    /**
     * Helper for further child class
     * @param name name
     * @param param parameter list
     */
    public Bnz(String name, int[] param){
        super(name, param);
    }

    @Override
    public int execute(Register regs) {
        if(regs.get(parameters[0]) != 0){
            return parameters[1];
        }
        return -1;
    }

}

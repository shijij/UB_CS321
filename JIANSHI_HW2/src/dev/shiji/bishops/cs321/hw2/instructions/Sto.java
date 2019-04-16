package dev.shiji.bishops.cs321.hw2.instructions;

import dev.shiji.bishops.cs321.hw2.interpreter.Register;

/**
 * Instruction for Store
 */
public class Sto extends Instruction {

    /**
     * Initialize a new Sto instruction
     * @param param parameter list
     */
    public Sto(int[] param){
        super("sto", param);
    }

    /**
     * Helper for further child class
     * @param name name
     * @param param parameter list
     */
    public Sto(String name, int[] param){
        super(name, param);
    }

    @Override
    public int execute(Register regs) {
        regs.set(parameters[0], parameters[1]);
        return -1;
    }

}

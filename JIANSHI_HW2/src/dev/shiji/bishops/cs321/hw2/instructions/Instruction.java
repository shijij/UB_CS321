package dev.shiji.bishops.cs321.hw2.instructions;

import dev.shiji.bishops.cs321.hw2.interpreter.Register;
import java.util.Arrays;

/**
 * An Abstract Class for instruction, which includes the name
 *      and the list of parameters
 * @author Shiji Jiang
 */
public abstract class Instruction {
    protected String name;
    protected int[] parameters;

    /**
     * Initialize a new Instruction
     * @param name name of the instruction
     * @param parameters list of registers and maybe a line number referenced by the label
     */
    public Instruction(String name, int[] parameters){
        this.name = name;
        this.parameters = parameters;
    }

    /**
     * Execeute the instruction
     * @param regs Register List
     * @return Line number of the next instruction;
     *         -1 for no redirection, move on to the next instruction.
     */
    public abstract int execute(Register regs);

    @Override
    public String toString(){
        return name+" "+ Arrays.toString(parameters);
    }
}

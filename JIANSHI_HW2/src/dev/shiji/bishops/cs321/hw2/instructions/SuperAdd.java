package dev.shiji.bishops.cs321.hw2.instructions;
import dev.shiji.bishops.cs321.hw2.interpreter.Register;

/**
 * For testing only, please ignore.
 */
public class SuperAdd  extends Add{

    public static final int num_param = 4;

    public SuperAdd(int[] param){
        super("superadd", param);
    }


    @Override
    public int execute(Register regs) {
        int result = regs.get(parameters[1] + parameters[2] + parameters[3]);
        regs.set(parameters[0], result);
        return -1;
    }
}

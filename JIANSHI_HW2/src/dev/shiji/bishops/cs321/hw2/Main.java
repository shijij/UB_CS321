package dev.shiji.bishops.cs321.hw2;

import dev.shiji.bishops.cs321.hw2.interpreter.Interpreter;
import java.io.File;

/**
 * Main Class, for testing
 *
 * @author Shiji Jiang
 */
public class Main {
    public static void main(String[] args){
        // Open and Read
        Interpreter i = new Interpreter(new File("./test.txt"));
        // Print Internal Form
        i.printInstructions();
        // Execute
        i.run();
        // Print Registers
        i.printRegisters();
    }
}

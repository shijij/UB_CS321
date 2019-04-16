package dev.shiji.bishops.cs321.hw2.interpreter;

import dev.shiji.bishops.cs321.hw2.instructions.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Simulate a TML Interpreter
 *
 * @author Shiji Jiang
 */
public class Interpreter {
    private Register regs;
    private HashMap<String, Integer> lables;
    private List<Instruction> ins;


    /**
     * Generate a Interpreter by File
     * @param file input
     */
    public Interpreter(File file){
        regs = new Register();
        lables = new HashMap<>();
        ins = new ArrayList<>();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));

            // First Loop, Generate Label -> Line Map
            String line = reader.readLine();
            int ai = 0;
            while (line != null) {
                mapLabels(line.trim(), ai);
                ai ++;
                line = reader.readLine();
            }

            // Second Loop, Read and Store Instructions
            reader = new BufferedReader(new FileReader(file));
            line = reader.readLine();
            while (line != null) {
                parse(line.trim());
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Store the label with line number
     * @param line line input
     * @param ai line number
     */
    private void mapLabels(String line, int ai){
        String[] parts = line.split("\\s+");
        lables.put(parts[0], ai);
    }

    /**
     * Parse the line and store the instruction
     * Labels in Instruction (bnz) are replaced with the actual line number of
     *     the target.
     * @param line line input
     * @throws TMLParsingException when the TML program can't be parsed
     */
    private void parse(String line) {
        // Skip blank line
        if(line.isBlank()){
            return;
        }

        // Explode String by blank char(s)
        String[] parts = line.split("\\s+");

        // Incomplete line, throw exception, expecting label and instruction keyword.
        if(parts.length < 2){
            throw new TMLParsingException("Label and/or instruction missing!");
        }

        int[] parts_int = new int[parts.length - 2];

        // make Parameter list int
        for(int i = 2; i < parts.length; i ++) {
            // Handle Bnz special case
            if(i == parts.length -1 && parts[1].toLowerCase().equals("bnz") ){
                // replace the redirection label with line number
                try{
                    parts_int[i - 2] = lables.get(parts[i]);
                } catch (NullPointerException e){
                    // if label not exist, throw exception
                    throw new TMLParsingException("Label Not Found");
                }
            } else {
                // Check if the parameter passed is integer
                try{
                    parts_int[i - 2] = Integer.parseInt(parts[i]);
                } catch (NumberFormatException e){
                    // if not integer, throw exception
                    throw new TMLParsingException("Invalid Register Index");
                }
            }
        }

        switch (parts[1].toLowerCase()){
            case "add":
                ins.add(new Add(parts_int));
                break;
            case "sub":
                ins.add(new Sub(parts_int));
                break;
            case "mul":
                ins.add(new Mul(parts_int));
                break;
            case "div":
                ins.add(new Div(parts_int));
                break;
            case "out":
                ins.add(new Out(parts_int));
                break;
            case "sto":
                ins.add(new Sto(parts_int));
                break;
            case "bnz":
                ins.add(new Bnz(parts_int));
                break;
            default:
                System.out.println("Unknown Instruction "
                        + parts[1] + ", Ignored.");
                break;
        }

    }

    /**
     * Execute all instructions from beginning
     */
    public void run(){
        int i = 0;
        System.out.println("\n## Instructions Executed ##");
        while (i < ins.size()){
            System.out.println(i + " " + ins.get(i));
            int result = ins.get(i).execute(regs);
            // If there's redirection, goto target
            if(result != -1){
                i = result;
            } else {
                // Otherwise keeps going
                i ++;
            }
        }
        System.out.println("## END of Instructions Executed ##");
    }

    /**
     * List all instructions stored
     */
    public void printInstructions(){
        System.out.println("\n## Internal Form ##");
        for(Instruction i : ins){
            System.out.println(i);
        }
        System.out.println("## END of Internal Form ##");
    }

    /**
     * Show the status of the registers
     */
    public void printRegisters(){
        System.out.println("\n## Registers ##");
        System.out.println(regs);
        System.out.println("## END of Registers ##");
    }

}

/**
 * @author shiji jiang <sjiang18@ubishops.ca>
 *
 */

import java.util.Arrays;
import java.util.stream.IntStream;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// A
		System.out.println("Shiji Jiang");

		// B
		int[][] matrix = {  
							{ 0, 2, 3, 0, 0 },
							{ 2, 0, 15, 2, 0 },
							{ 3, 15, 0, 0, 13 },
							{ 0, 2, 0, 0, 9 },
							{ 0, 0, 13, 9, 0 } 
						 };
		display(matrix);
		
		// C
		System.out.println(Arrays.toString(toLengths(matrix)));
		
		
		// D
		System.out.println(Arrays.toString(neightbours(4, matrix)));
		
		// E
		boolean[][] M = {  
				{ false, false,false,false,false,false },
				{ false, false,false,false,false,false },
				{ false, false,false,false,false,false },
				{ true, false,false,false,false,false },
				{ false, false,false,false,false,false },
				{ false, false,false,false,false,false },
				{ false, false,false,false,false,false }
			 };
		System.out.println(manDist(M));
		
	}

	// B
	public static void display(int[][] matrix) {

		// Print Table Header
		System.out.print("#  |\t");
		for (int i = 0; i < matrix.length; i++) {
			System.out.print(i);
			System.out.print("\t");
		}
		System.out.println("\r---+---------------------------------------");

		// Print table Content
		for (int i = 0; i < matrix.length; i++) {
			System.out.print(i + "  |");
			System.out.print("\t");
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j]);
				System.out.print("\t");
			}
			System.out.println();
		}
	}
	
	// C
	public static int[] toLengths(int[][] matrix) {
		int[] result = new int[matrix.length];
		for(int i = 0; i < matrix.length; i++) {
			// Get sum for sub array
			result[i] = IntStream.of(matrix[i]).sum();
		}
		return result;
	}
	
	// D, solution without using ArrayList
	public static int[] neightbours(int city, int [][] matrix) {
		int array_size = 0;
		
		// Find array size first
		for(int i = 0; i < matrix[city].length; i ++) {
			if(matrix[city][i] > 0) {
				array_size ++;
			}
		}
		
		int[] result = new int[array_size];
		
		// Assign value
		for(int i = 0, p = 0; i < matrix[city].length; i ++) {
			if(matrix[city][i] > 0) {
				result[p] = i;
				p ++;
			}
		}
		
		return result;
	}
	
	// E
	public static int manDist(boolean [][] M) {
		int x = 0, y = 0;
		for (int i = 0; i < M.length; i++) {
			for (int j = 0; j < M[i].length; j++) {
				if (M[i][j]) {
					x = i; y = j;
				}
			}
		}
		return  x + (M[0].length - 1 - y);
	}

	public int[] post4(int[] nums) {
		int pos = nums.length;

		for (int i = 0; i < nums.length; i ++){
			if(nums[i] == 4){
				pos = i + 1;
			}
		}

		int[] result = new int[nums.length - pos];

		for (int j = 0; j < nums.length - pos; j ++){
			result[j] = nums[j + pos];
		}

		return result;
	}

}

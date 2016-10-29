import java.util.Scanner;
import java.io.*;

public class NumFiles {
	public int[][][] digits = {makeMatrix("zero.txt"), makeMatrix("one.txt"), makeMatrix("two.txt"),
			makeMatrix("three.txt"), makeMatrix("four.txt"), makeMatrix("five.txt"), makeMatrix("six.txt"),
					makeMatrix("seven.txt"), makeMatrix("eight.txt"), makeMatrix("nine.txt")};
	
	public NumFiles() {
	}
	
	public int[][] getMatrix(String num){
		if (num == "one") {
			return digits[1];
		}
		else if (num == "two"){
			return digits[2];
		}
		else if (num == "three"){
			return digits[3];
		}
		else if (num == "four") {
			return digits[4];
		}
		else if (num == "five") {
			return digits[5];
		}
		else if (num == "six") {
			return digits[6];
		}
		else if (num == "seven"){
			return digits[7];
		}
		else if (num == "eight"){
			return digits[8];
		}
		else if (num == "nine") {
			return digits[9];
		}
		else if (num == "zero") {
			return digits[0];
		}
		else {
			int [][] k = {{-1},{-1}};
			return k;
		}
	}
	private int[][] makeMatrix(String filename) {
		int[][] matrix = new int[13][13];
		File fl = new File(filename);
		if (fl.exists()) {
			System.out.println("The file exists!");
		}
		try {
			Scanner fn = new Scanner(fl);
			int row = 0;
			while (fn.hasNext()) {
				String line = fn.nextLine();
				System.out.println(line);
				String[] numarr = line.split("\t");
				for (int i = 0; i<numarr.length;i++){
					matrix[row][i] = Integer.parseInt(numarr[i]);
				}
				row++;
			}
			fn.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error opening file. Please make sure that you have your text file in the same folder as the NumFiles.class");
            System.exit(0);
        }
		return matrix;

	}
	
}

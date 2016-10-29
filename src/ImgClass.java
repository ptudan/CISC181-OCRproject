import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JOptionPane;


public class ImgClass {
	private NumFiles numfile = new NumFiles();
	private double[][] imgMatrix = ReadMatrix();
	private int[][] cleanMatrix = cleanup();

	
	public double[][] ReadMatrix() {
		String filename = JOptionPane.showInputDialog("Enter The file to be read");
		File fl = new File(filename);
		double matrix[][] = new double[0][0];
		try {
			Scanner fn = new Scanner(fl);
			int row = 0;
			while (fn.hasNext()) {
				row ++;
				fn.nextLine();
			}
			fn.close();
			matrix = new double[row][];
			Scanner f2 = new Scanner(fl);
			row = 0;
			while (f2.hasNext()) {
				String line = f2.nextLine();
				System.out.println(line);
				String[] numarr = line.split(" ");
				int x = numarr.length;
				double[] arr = new double[x];
				matrix[row]= arr;
				for (int i = 0; i<numarr.length;i++){
					matrix[row][i] = Double.parseDouble(numarr[i]);
				}
				row++;
			}
			f2.close();
	    } catch (FileNotFoundException e) {
	        System.out.println("Error opening file. Please make sure that you have your text file in the same folder as the NumFiles.class");
	        System.exit(0);
	    }
		return matrix;
	}

	public int[][] cleanup() {
		int[][] cleanM = new int[imgMatrix.length][imgMatrix[0].length];
		for(int i = 0; i<imgMatrix.length; i++){
			for(int j = 0; j<imgMatrix[0].length; j++){
				if(imgMatrix[i][j]>.5){
					cleanM[i][j] = 1;
				}
				else{
					cleanM[i][j] = 0;
				}
			}
		}
		return cleanM;
	}

	public String FindBestMatch() {
		int highest = 0;
		for(int i = 0; i<10; i++){
			if(compareMatrices(numfile.digits[i])>compareMatrices(numfile.digits[highest])){
				highest = i;
			}
		}
		
		String[] nums = {"Zero", "One", "Two", "Three", "Four", "Five", "Six",
				"Seven", "Eight", "Nine"};
		return(nums[highest] + " is the best match");
	}
		
	
	public double compareMatrices(int[][] num) {
		int index = 0;
		double[] scores = new double[((1+cleanMatrix.length)-num.length)*
		                             ((1+cleanMatrix[0].length)-num[0].length)];
		
		for(int i = 0; i<((1+cleanMatrix.length)-num.length); i++){
			for(int j = 0; j<((1+cleanMatrix[0].length)-num[0].length); j++){
				for(int a = 0; a<num.length; a++){
					for(int b = 0; b<num.length; b++){
						if((cleanMatrix[a+i][b+j] == 1)
								&&(num[a][b]==1)){
							scores[index] += 1.0;
						}
						else if((cleanMatrix[a+i][b+j] == 0)
								&&(num[a][b] == 0)){
							scores[index] += 0.25;
						}
						else if((cleanMatrix[a+i][b+j] == 0)
								&&(num[a][b] == 1)){
							scores[index] -= 0.25;
						}
					}
				}
				index += 1;
			}
		}
		
		double highest = 0.0;
		for(double s : scores){
			if(s > highest){
				highest = s;
			}
		}
		return highest;
	}
}

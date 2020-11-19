package StudentRating;

import java.util.Arrays;

public class TestSplit {

	public static void main(String[] args) {

		
		String student [][]  = null;
		String A = "nguyen-ngoc-huyen";
		int i = 0;
		while(true) {
			student[i] = A.split("-");
			i ++;
		}
		//System.out.println(Arrays.deepToString(student));
	}

}

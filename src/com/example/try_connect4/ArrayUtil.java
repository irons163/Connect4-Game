package com.example.try_connect4;

public class ArrayUtil {
	public static boolean isArrayColElementAllNotZero(int[][] array, int whichCol){
		boolean isAllNotZero = true;
		
		for(int row : array[whichCol]){
			if(row==0){
				isAllNotZero = false;
				break;
			}
		}	
		return isAllNotZero;
	}
	
	public static int witchArrayColElementIsNotZeroOrderByRow(int[][] array, int whichCol){
		int witchIsNotZero = -1;
		
		for(int row = array[whichCol].length-1 ;row >= 0 ; row--){
			if(array[whichCol][row]==0){
				witchIsNotZero = row;
				break;
			}
		}	
		return witchIsNotZero;
	}
}

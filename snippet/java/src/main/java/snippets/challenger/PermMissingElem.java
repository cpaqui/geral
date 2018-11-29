package snippets.challenger;

import java.util.Arrays;

public class PermMissingElem {

    public static void main(String[] args) {
	Util.log(String.format("Solution: %d", solution(new int[] {1, 2})));
    }
    
    private static int solution (int[] arr) {
	Arrays.sort(arr);
	
	for (int i = 0; i < arr.length - 1; i++) {
	    if (arr[i+1] - arr[i] > 1) return arr[i]+1;
	}
	
	int solution = 1;
	
	if (arr.length == 1) solution = arr[0];
	else if (arr.length > 1 && arr[0] != 0) return arr[0] -1;
	else if (arr.length > 1 && arr[0] != 0) return arr[arr.length-1]+1;
	    
	return solution;
    }
}

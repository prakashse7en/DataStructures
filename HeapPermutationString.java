/*1. first get the number of occurrence of  each character
then create input array -> contains input characters in array format
			count array -> contains the number of occurrence of each character
			result array -> which used as buffer to hold the final result when level is reached
			array list -> contains the final set of all combinations
			
2. once all the initializations are done the call permute which does
		i) check if the level and string length is equal then combination is formed as per logic
		ii) iterate the string 	
			check if occurrence is 0 then continue
			else append the index to result and reduce count as it is added to res and call permute increasing level
			increase count as we need to identify other combinations as well
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class HeapPermutationString {
	public static void main(String[] args) {
		// first calculate the number of times a string has occured
		String inputString = "113";
		HashMap<Character, Integer> hm = (HashMap<Character, Integer>) cal(inputString.toCharArray());

		// key value in one array and result in one array
		char input[] = new char[inputString.length()];
		int count[] = new int[inputString.length()];
		char result[] = new char[inputString.length()];
		int index = 0;
		for (Entry<Character, Integer> s : hm.entrySet()) {
			input[index] = s.getKey();
			count[index] = s.getValue();
			index++;
		}
		List<String> finalList = new ArrayList<String>();
		permute(input, count, result, 0, finalList);
		finalList.forEach(System.out::println);
	}

	// if level = string length
	public static void permute(char ip[], int count[], char res[], int level, List<String> resList) {
		if (level == res.length) {
			resList.add(new String(res));
			return;
		}
		for (int i = 0; i < ip.length; i++) {
			if (count[i] == 0){
				continue;
			}
			res[level] = ip[i];
			count[i]--;
			permute(ip, count, res, level + 1, resList);
			count[i]++;
		}
	}

	public static Map<Character, Integer> cal(char[] input) {
		Map<Character, Integer> occurMap = new HashMap<>();

		for (char c : input) {
			occurMap.compute(c, (key, val) -> val == null ? 1 : val + 1);
		}

		return occurMap;
	}

	// permute with duplicates
}

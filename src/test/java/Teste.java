import java.util.*;

public class Teste {

	@SuppressWarnings("null")
	public static void main(String[] args) {
		List<Character> input = new ArrayList<>();
		input.add('a');
		input.add('b');
		input.add('c');
		input.add('d');
		input.add('a');
		Character character = input.get(0);
		int lastIndexOf = input.lastIndexOf('a');
		
		System.out.println(lastIndexOf);
	}
}

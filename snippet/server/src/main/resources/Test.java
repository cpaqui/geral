
public class Test {

    public static void main(String[] args) {
	final List<String> strings  
	   = Stream.of("one", "two", "three", "four")  
	      .peek(e-> out.println("Original Element: " + e))  
	      .filter(e -> e.length() > 3)  
	      .peek(e -> out.println("Filtered value: " + e))  
	      .map(String::toUpperCase)  
	      .peek(e -> out.println("Mapped value: " + e))  
	      .collect(Collectors.toList());  
	out.println("Final Results: " + strings);
    }
}

package server.undertow.main;

import java.io.PrintStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {

    public static void main(String[] args) {
	PrintStream out = System.out;
	
	final List<String> strings  
	   = Stream.of("one", "two", "three", "four")  
	      .peek(Test::println)  
	      .filter(e -> e.length() > 3)  
	      .peek(e -> out.println("Filtered value: " + e))  
	      .map(String::toUpperCase)  
	      .peek(e -> out.println("Mapped value: " + e))  
	      .collect(Collectors.toList());  
	out.println("Final Results: " + strings);
    }
    
    public static void println (String s) {
	System.out.println(s);
    }
}

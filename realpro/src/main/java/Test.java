import java.io.IOException;
import java.util.List;

import javax.management.RuntimeErrorException;


public class Test {
	
	public String xxx(int x) {
		var xxx = 5;
		if(x> 10) {
			throw new RuntimeException("zzzzz");
		}
		
		return "";
	}
	
	public static void main(String[] args) throws IOException {
		int a = 2 ;
		int b = ~a;
		int c = a^b;
		boolean d = a < b & a > c++;
		System.out.println(d + " " + c);
		boolean e = a > b && a > c++;
		System.out.println(e + " " + c);
	}

}

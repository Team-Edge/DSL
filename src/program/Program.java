/**
 * 
 */
package program;
import scanner.*;
import parser.*;

/**
 * @author Florian
 *
 */
public class Program {

	/**
	 * 
	 */
	public Program() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DslScanner s = new DslScanner("C://Users//Z003hkey//Desktop//test.txt");
		Parser p = new Parser(s);
		p.ParseShape();
	}

}

/**
 * 
 */
package scanner;

import java.io.File;

/**
 * @author Florian
 *
 */
public class DslScanner {

	private String mSource;
	
	public enum Tokens {
		ID,
		NUM,
		PLUS,
		MINUS,
		MULT,
		DIV,
		DEF,
		END,
		SHAPE,
		SIZE,
		GOTO,
		MOVETO,
		CUTTO,
		COMMA,
		SEMICOLON,
		BRACOPEN,
		BRACCLOSE		
	}
	
	/**
	 * 
	 */
	public DslScanner(String fileName) {
		// TODO Auto-generated constructor stub
		mSource = "";
		try  {
			java.io.File file = new java.io.File(fileName);
			java.io.FileReader reader = new java.io.FileReader(file);
			java.io.BufferedReader br = new java.io.BufferedReader(reader);
			while(br.ready())
				mSource += br.readLine();
		}
		catch(Exception e)
		{
			
		}
	}
	
	public Tokens la() {
		
	}
	
	public void consume() {
		
	}
	
	public String value() {
		
	}
}

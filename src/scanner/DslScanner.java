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
	private int count;
	private Tokens token;
	
	public enum Tokens {
		ID, // name
		NUM, // zahl
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
		count = 0;
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
		
		Character ch = mSource.charAt(count);
		
		if(ch.equals(' ')){
			count++;
			ch = mSource.charAt(count);
		}
		
		if(	ch.equals('+') || ch.equals('-') || ch.equals('/') || ch.equals('*') ||
			ch.equals(',') || ch.equals(';') || ch.equals('(') || ch.equals(')')){
			
			switch(ch){
				case('+'): 
					token = Tokens.PLUS;
					break;		
				
				case('-'): 
					token = Tokens.MINUS;
					break;
					
				case('/'): 
					token = Tokens.DIV;
					break;
					
				case('*'): 
					token = Tokens.MULT;
					break;
					
				case(','): 
					token = Tokens.COMMA;
					break;
					
				case(';'): 
					token = Tokens.SEMICOLON;
					break;
					
				case('('): 
					token = Tokens.BRACOPEN;
					break;
					
				case(')'): 
					token = Tokens.BRACCLOSE;
					break;
				default:
					System.exit(0);
					break;
		}
			
		
	}
}
	
	public String value() {
		
	}
}

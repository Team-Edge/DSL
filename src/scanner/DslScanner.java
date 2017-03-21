/**
/**
 * 
 */
package scanner;

/**
 * @author Florian
 *
 */

public class DslScanner {

	private String mSource;
	private int count;
	private Tokens token;	//look ahead
	private String value;
	
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
		/*GOTO,
		MOVETO,
		CUTTO,*/
		COMMA,
		SEMICOLON,
		BRACOPEN,
		BRACCLOSE,
		ERROR
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
			br.close();
		}
		catch(Exception e)
		{
			
		}
	}
	
	public Tokens la() {
		
		return token;
	}
	
	public void consume() {
		
		value = "";
		Character ch = mSource.charAt(count);
		
		while(Character.isWhitespace(ch)){
			count++;
			ch = mSource.charAt(count);
		}
		
		if(	ch.equals('+') || ch.equals('-') || ch.equals('/') || ch.equals('*') ||
			ch.equals(',') || ch.equals(';') || ch.equals('(') || ch.equals(')')){
			
			switch(ch){
				case('+'): 
					token = Tokens.PLUS;
					count++;
					break;		
				
				case('-'): 
					token = Tokens.MINUS;
					count++;
					break;
					
				case('/'): 
					token = Tokens.DIV;
					count++;
					break;
					
				case('*'): 
					token = Tokens.MULT;
					count++;
					break;
					
				case(','): 
					token = Tokens.COMMA;
					count++;
					break;
					
				case(';'): 
					token = Tokens.SEMICOLON;
					count++;
					break;
					
				case('('): 
					token = Tokens.BRACOPEN;
					count++;
					break;
					
				case(')'): 
					token = Tokens.BRACCLOSE;
					count++;
					break;
				default:
					System.exit(1);
					break;
			}
		}
		
		else if(Character.isAlphabetic(ch)){
			
			String word = "";
			do{
				word += ch;
				count++;
				if(count < mSource.length()) 
					ch = mSource.charAt(count);
				else break;
			}while(Character.isAlphabetic(ch) || Character.isDigit(ch));
			
			switch(word){
				case("def"):
					token = Tokens.DEF;
					break;
				case("shape"):
					token = Tokens.SHAPE;
					break;
				case("size"):
					token = Tokens.SIZE;
					break;
				case("end"):
					token = Tokens.END;
					break;
				default:
					token = Tokens.ID;
					value = word;
					break;
			}
		}
		
		else if(Character.isDigit(ch)){
			
			String number = "";
			do{
				number = number.concat(ch.toString());
				count++;
				ch = mSource.charAt(count);
			}while(Character.isDigit(ch));
			
			value = number;
			token = Tokens.NUM;
		}
		
		else{
			
			token = Tokens.ERROR;
		}
	}
	
	public String value() {
		
		return value;
	}
}

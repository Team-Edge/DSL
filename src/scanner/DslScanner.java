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
		GOTO,
		MOVETO,
		CUTTO,
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
		
		else if(Character.isAlphabetic(ch)){
			
			String word = "";
			do{
				word.concat(ch.toString());
				count++;
				ch = mSource.charAt(count);
			}while(Character.isAlphabetic(ch) || Character.isDigit(ch));
			
			switch(word){
				case("DEF"):
					token = Tokens.DEF;
					break;
				case("SHAPE"):
					token = Tokens.SHAPE;
					break;
				case("SIZE"):
					token = Tokens.SIZE;
					break;
				case("GOTO"):
					token = Tokens.GOTO;
					break;
				case("MOVETO"):
					token = Tokens.MOVETO;
					break;
				case("CUTTO"):
					token = Tokens.CUTTO;
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
				number.concat(ch.toString());
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

/**
 * 
 */
package parser;

import scanner.DslScanner;

/**
 * @author Florian
 *
 */
public class Parser {

	private DslScanner mScanner;
	
	/**
	 * 
	 */
	public Parser(DslScanner scanner) {
		// TODO Auto-generated constructor stub
		mScanner = scanner;
		mScanner.consume();
	}
	
	public void ParseShape()
	{
		expect(DslScanner.Tokens.SHAPE);
		mScanner.consume();
		expect(DslScanner.Tokens.ID);
		mScanner.consume();
		expect(DslScanner.Tokens.SIZE);
		mScanner.consume();
		expect(DslScanner.Tokens.NUM);
		mScanner.consume();
		expect(DslScanner.Tokens.NUM);
		mScanner.consume();
		expect(DslScanner.Tokens.SEMICOLON);
		mScanner.consume();
		while (mScanner.la()==DslScanner.Tokens.ID||mScanner.la()==DslScanner.Tokens.DEF){
			parseStat();
		}
		expect(DslScanner.Tokens.END);
		mScanner.consume();

			
	}
	
	private void parseStat(){
		if(mScanner.la()==DslScanner.Tokens.ID){
			parseCommand();
		}
		else if(mScanner.la()==DslScanner.Tokens.DEF){
			parseDef();
		}
	}
	
	private void parseCommand(){
		expect(DslScanner.Tokens.ID);
		mScanner.consume();
		parseArgList();
		expect(DslScanner.Tokens.SEMICOLON);
		mScanner.consume();
	}
	private void parseDef(){
		expect(DslScanner.Tokens.DEF);
		mScanner.consume();
		expect(DslScanner.Tokens.ID);
		mScanner.consume();
		parseOptParam();
		while(mScanner.la()==DslScanner.Tokens.ID||mScanner.la()==DslScanner.Tokens.DEF){
			parseStat();
		}
		expect(DslScanner.Tokens.END);
		mScanner.consume();
	}
	private void parseArgList(){
		while(mScanner.la()==DslScanner.Tokens.MINUS||mScanner.la()==DslScanner.Tokens.ID||mScanner.la()==DslScanner.Tokens.NUM){
			parseExpr();
		}
	}
	private void parseExpr(){
		parseUnary();
		while(mScanner.la()==DslScanner.Tokens.PLUS||mScanner.la()==DslScanner.Tokens.MINUS||mScanner.la()==DslScanner.Tokens.MULT||mScanner.la()==DslScanner.Tokens.DIV){
			parseOP();
			parseUnary();
		}
	}
	private void parseUnary(){
		if(mScanner.la()==DslScanner.Tokens.MINUS){
			mScanner.consume();
		}
		parseTerm();
	}
	
	private void parseTerm(){
		if(mScanner.la()==DslScanner.Tokens.ID||mScanner.la()==DslScanner.Tokens.NUM){
			mScanner.consume();
		}
	}

	void expect(DslScanner.Tokens token){
		if (mScanner.la()!=token){
			System.out.println("Error");
			System.exit(1);
		}
		
		
	}
	


}

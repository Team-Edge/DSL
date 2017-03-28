/**
 * 
 */
package parser;

import java.util.LinkedList;
import java.util.List;

import scanner.DslScanner;
import syntaxbaum.*;
import syntaxbaum.Number;

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
	
	public Shape ParseShape()
	{
		expect(DslScanner.Tokens.SHAPE);
		mScanner.consume();
		expect(DslScanner.Tokens.ID);
		String shapeName = mScanner.value();
		mScanner.consume();
		expect(DslScanner.Tokens.SIZE);
		mScanner.consume();
		expect(DslScanner.Tokens.NUM);
		int width = Integer.parseInt(mScanner.value());
		mScanner.consume();
		expect(DslScanner.Tokens.NUM);
		int height = Integer.parseInt(mScanner.value());
		mScanner.consume();
		expect(DslScanner.Tokens.SEMICOLON);
		mScanner.consume();
		List<Stat> statements = new LinkedList<Stat>();
		while (mScanner.la()==DslScanner.Tokens.ID||mScanner.la()==DslScanner.Tokens.DEF){
			statements.add(parseStat());
		}
		expect(DslScanner.Tokens.END);
		System.out.println("Hat geklappt");

		return new Shape(shapeName, width, height, statements);
	}
	
	private Stat parseStat(){
		if(mScanner.la()==DslScanner.Tokens.ID){
			return parseCommand();
		}
		else if(mScanner.la()==DslScanner.Tokens.DEF){
			return parseDef();
		}
		else customQuit();
		return null;
	}
	
	private Command parseCommand(){
		expect(DslScanner.Tokens.ID);
		String cmdName = mScanner.value();
		mScanner.consume();
		ArgList args = parseArgList();
		expect(DslScanner.Tokens.SEMICOLON);
		mScanner.consume();
		return new Command(cmdName, args);
	}
	private Def parseDef(){
		expect(DslScanner.Tokens.DEF);
		mScanner.consume();
		expect(DslScanner.Tokens.ID);
		String defName = mScanner.value();
		mScanner.consume();
		OptParam parameters = parseOptParam();
		List<Stat> statements = new LinkedList<Stat>();
		while(mScanner.la()==DslScanner.Tokens.ID||mScanner.la()==DslScanner.Tokens.DEF){
			statements.add(parseStat());
		}
		expect(DslScanner.Tokens.END);
		mScanner.consume();
		return new Def(defName, parameters, statements);
	}
	private ArgList parseArgList(){
		List<Expr> expressions = new LinkedList<Expr>();
		while(mScanner.la()==DslScanner.Tokens.MINUS||mScanner.la()==DslScanner.Tokens.ID||mScanner.la()==DslScanner.Tokens.NUM){
			expressions.add(parseExpr());
		}
		return new ArgList(expressions);
	}
	private Expr parseExpr(){
		List<Unary> unaries = new LinkedList<Unary>();
		List<OP> operators = new LinkedList<OP>();
		unaries.add(parseUnary());
		while(mScanner.la()==DslScanner.Tokens.PLUS||mScanner.la()==DslScanner.Tokens.MINUS||mScanner.la()==DslScanner.Tokens.MULT||mScanner.la()==DslScanner.Tokens.DIV){
			operators.add(parseOP());
			unaries.add(parseUnary());
		}
		return new Expr(unaries, operators);
	}
	private Unary parseUnary(){
		boolean negative = false;
		if(mScanner.la()==DslScanner.Tokens.MINUS){
			negative = true;
			mScanner.consume();
		}
		Term term = parseTerm();
		return new Unary(negative, term);
	}
	
	private Term parseTerm(){
		if(mScanner.la()==DslScanner.Tokens.ID){
			String varName = mScanner.value();
			mScanner.consume();
			return new Variable(varName);
		} else if (mScanner.la()==DslScanner.Tokens.NUM) {
			int num = Integer.parseInt(mScanner.value());
			mScanner.consume();
			return new Number(num);
		}
		else { 
			customQuit();
			return null;
		}
	}
	private OP parseOP(){
		if(mScanner.la()==DslScanner.Tokens.PLUS||mScanner.la()==DslScanner.Tokens.MINUS||mScanner.la()==DslScanner.Tokens.MULT||mScanner.la()==DslScanner.Tokens.DIV){
			String operator = mScanner.value();
			mScanner.consume();
			return new OP(operator);
		}
		else {
			customQuit();
			return null;
		}
	}
	
	private OptParam parseOptParam(){
		if(mScanner.la()!=DslScanner.Tokens.BRACOPEN){
			return new OptParam(null);
		}
		mScanner.consume();
		ParamList paramlist = parseParamList();
		expect(DslScanner.Tokens.BRACCLOSE);
		mScanner.consume();
		return new OptParam(paramlist);
	}

	private ParamList parseParamList(){
		List<String> paramNames = new LinkedList<String>();
		if(mScanner.la()!=DslScanner.Tokens.ID){
			return new ParamList(paramNames);
		}
		paramNames.add(mScanner.value());
		mScanner.consume();
		while(mScanner.la()==DslScanner.Tokens.COMMA){
			mScanner.consume();
			expect(DslScanner.Tokens.ID);
			paramNames.add(mScanner.value());
			mScanner.consume();
		}
		return new ParamList(paramNames);
	}
	void expect(DslScanner.Tokens token){
		if (mScanner.la()!=token){
			customQuit();
		}
		
		
	}
	private void customQuit(){
		System.out.println("Error");
		System.exit(1);
	}
	
	


}

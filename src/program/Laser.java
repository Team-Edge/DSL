/**
 * 
 */
package program;

import java.io.IOException;

/**
 * @author z003hkey
 *
 */
public class Laser {
	
private boolean m_laser;
private int x;
private int y;
private int x_max;
private int y_max; 
private boolean array[][];
	
	public Laser(int x, int y){
		
		x_max = x;
		y_max = y;
		m_laser = false;
	}
	
	void laserOn(){
		
		m_laser = true;
	}
	
	void laserOff(){
		
		m_laser = false;
	}
	
	void reset(){
		
		x = 0;
		y = 0;
	}
	
	
	boolean stepX(int plusX) throws IOException{
		
		if(m_laser){
			
			if(x+plusX < x_max){
				
				for(int i = x; i < x+plusX; i++){
					
					array[i][y] = true;
				}
				
				printArray();
				return true;
			}
			
			return false;
		}
		
		
		return true;
	}
	
	boolean stepY(int plusY) throws IOException{
		
		if(m_laser){
			
			if(y+plusY < y_max){
				
				for(int i = y; i < y+plusY; i++){
					
					array[x][i] = true;
				}
				
				printArray();
				return true;
			}
			
			return false;
		}
		
		
		return true;
	}
	
	
	public void printArray() throws IOException{
		
		Runtime.getRuntime().exec("cls");
		
		for(int i = 0; i < y_max; i++){
			
			for(int j = 0; j < x_max; j++){
				
				if(array[i][j]) System.out.print('#');
				else System.out.print(' ');
			}
			
			System.out.println();
		}
	}
}

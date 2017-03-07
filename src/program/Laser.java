/**
 * 
 */
package program;

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
	
	
	boolean stepX(int plusX){
		
		x += plusX;
		if(x > x_max){
			x -= plusX;
			return false;
		}
		
		return true;
	}
	
	
	boolean stepY(int plusY){
		
		y += plusY;
		if(y > y_max){
			y -= plusY;
			return false;
		}
		
		return true;
	}
}

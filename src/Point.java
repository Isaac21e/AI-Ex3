/**
 *This class represents a point (x,y) in the space R^2
 * it has a x value and a y value.
 */
public class Point {

	private int _x;
	private int _y;

	public Point(int x, int y){
		_x=x;
		_y=y;
	}

	/**
	 * returns x
	 * @return x
	 */
	public int GetX(){return _x;}
	/**
	 * returns y
	 * @return y
	 */
	public int GetY(){return _y;}
}

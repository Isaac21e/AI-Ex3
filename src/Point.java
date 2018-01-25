/**
 * This class represents a point (x,y) in the space R^2
 * it has a x value and a y value.
 */
public class Point {

	private double _x;
	private double _y;
	private int _numOfCluster;

	public Point(double x, double y){
		_x=x;
		_y=y;
	}

	/**
	 * returns x
	 * @return x
	 */
	public double GetX(){return _x;}
	/**
	 * returns y
	 * @return y
	 */
	public double GetY(){return _y;}
	public void SetNumOfCluster(int num){
		_numOfCluster=num;

	}

	/***
	 * return num of cluster
	 * @return num of cluster
	 */
	public int GetNumOfCluster(){
		return _numOfCluster;

	}
}

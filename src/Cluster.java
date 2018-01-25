import java.util.LinkedList;
import java.util.List;

/**
 * The cluster class.
 * This class represents a cluster, that is, a group of points.
 */
public class Cluster {
	//members
	private List<Point> _points;
	private int _index;

	/**
	 * Constructor
	 * @param firstPoint gets the first point in cluster on initialization
	 */
	public Cluster(Point firstPoint){
		_points =new LinkedList<>();
		_points.add(firstPoint);
	}

	/**
	 * sets index of cluster
	 * @param index val
	 */
	public void SetIndex(int index){
		_index=index;
	}

	/***
	 * get point of cluster
	 * @return points
	 */
	public List<Point> GetPoints(){
		return _points;
	}

	/***
	 * updates all points to clusters index
	 * the point class has a member- cluster's index
	 */
	public void UpdatePointsIndex(){
		for(Point p: _points){
			p.SetNumOfCluster(_index);
		}
	}

}

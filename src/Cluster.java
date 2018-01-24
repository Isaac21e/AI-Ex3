import java.util.LinkedList;
import java.util.List;

/**
 * Created by Isaac on 1/25/2018.
 */
public class Cluster {

	private List<Point> _points;
	public Cluster(Point firstPoint){
		_points =new LinkedList<>();
		_points.add(firstPoint);
	}
	public void Add(Point p){
		_points.add(p);
	}
	public List<Point> GetPoints(){
		return _points;
	}

}

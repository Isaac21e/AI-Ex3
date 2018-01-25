import java.util.LinkedList;
import java.util.List;

/**
 * Created by Isaac on 1/25/2018.
 */
public class Cluster {

	private List<Point> _points;
	//private static int index=1;
	private int _index;
	public Cluster(Point firstPoint){
		_points =new LinkedList<>();
		_points.add(firstPoint);
		//_index=index;
		//index++;
	}
	public void Add(Point p){
		_points.add(p);
	}
	public void SetIndex(int index){
		_index=index;
	}

	public List<Point> GetPoints(){
		return _points;
	}
	public void updatePointsIndex(){
		for(Point p: _points){
			p.SetNumOfCluster(_index);
		}
	}

}

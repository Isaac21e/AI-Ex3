import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Isaac on 1/25/2018.
 */
public class Algorithm {
	private Map<String,Surveyor> _map= new HashMap<String, Surveyor>();
	private List<Cluster> _clusters;
	private int _numOfClusters;

	/**
	 * CTOR
	 * @param points list of points
	 * @param numOfClusters number of desired clusters
	 */
	public Algorithm(List<Point> points,int numOfClusters){
		_map= new HashMap<>();
		_map.put("single link",new SingleLinkSurveyor());
		_map.put("average link",new AverageLinkSurveyor());
		_numOfClusters=numOfClusters;
		initializeClusters(points);
	}
	public void Run(String type){
		Surveyor surveyor=_map.get(type);
		while(_clusters.size()!=_numOfClusters){
			Pair closestClusters=GetClosestClusters(surveyor);
			closestClusters.First.GetPoints().addAll(closestClusters.Second.GetPoints());
			closestClusters.First.updatePointsIndex();
			_clusters.remove(closestClusters.Second);
		}

	}
	public void UpdateClustersIndexes(){
		int i=1;
		for(Cluster c: _clusters){
			c.SetIndex(i);
			c.updatePointsIndex();
			i++;
		}
	}
	public Pair GetClosestClusters(Surveyor surveyor){
		Pair closestClusters= new Pair();
		double min= Double.MAX_VALUE;
		for (Cluster currentCluster:_clusters) {
			for (Cluster otherCluster:_clusters) {
				if(currentCluster!=otherCluster){
					double distance=surveyor.GetDistance(currentCluster,otherCluster);
					if(distance<min){
						min=distance;
						closestClusters.First=currentCluster;
						closestClusters.Second=otherCluster;
					}
				}
			}
		}
		return closestClusters;
	}
	public void initializeClusters(List<Point> points){
		_clusters=new LinkedList<>();
		for (Point p: points) {
			Cluster c= new Cluster(p);
			//c.updatePointsIndex();
			_clusters.add(c);
		}
	}





	private abstract class  Surveyor{
		abstract double  GetDistance(Cluster c1,Cluster c2);
		protected double GetDistance(Point current,Point other){
			double a = (Math.pow(current.GetX()-other.GetX(),2)) +
					           (Math.pow(current.GetY()-other.GetY(),2));
			return Math.sqrt(a);
		}
	}
	private class AverageLinkSurveyor extends Surveyor{
		@Override
		public double GetDistance(Cluster c1,Cluster c2){
			double sum = 0;
			for (Point currentClusterPoint : c1.GetPoints()) {
				for (Point otherClusterPoint: c2.GetPoints()) {
					sum += this.GetDistance(currentClusterPoint ,otherClusterPoint);
				}
			}
			int numOfCalc=c1.GetPoints().size()*c2.GetPoints().size();
			return sum / numOfCalc;
		}


	}
	private class SingleLinkSurveyor extends Surveyor{
		@Override
		public double GetDistance(Cluster c1,Cluster c2){
			double minDistance = Double.MAX_VALUE;
			for (Point currentClusterPoint : c1.GetPoints()) {
				for (Point otherClusterPoint : c2.GetPoints()) {
					double temp = GetDistance(currentClusterPoint,otherClusterPoint);
					if (temp < minDistance) {
						minDistance = temp;
					}
				}
			}
			return minDistance;
		}

	}
	private  class Pair{
		public Cluster First;
		public  Cluster Second;
	}


}

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
		_map= new HashMap<String, Surveyor>();
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
			_clusters.remove(closestClusters.Second);
		}

	}
	public List<Cluster> GetClusters(){
		return _clusters;
	}
	public Pair GetClosestClusters(Surveyor surveyor){
		Pair closestClusters= new Pair();
		double min= Double.MAX_VALUE;
		for (Cluster currentCluster:_clusters) {
			for (Cluster otherCluster:_clusters) {
				double distance=surveyor.getDistance(currentCluster,otherCluster);
				if(distance<min){
					min=distance;
					closestClusters.First=currentCluster;
					closestClusters.Second=otherCluster;
				}
			}
		}
		return closestClusters;
	}
	public void initializeClusters(List<Point> points){
		_clusters=new LinkedList<>();
		for (Point p: points) {
			Cluster c= new Cluster(p);
			_clusters.add(c);
		}
	}





	private interface  Surveyor{
			 double getDistance(Cluster c1,Cluster c2);
	}
	private class AverageLinkSurveyor implements Surveyor{
		public double getDistance(Cluster c1,Cluster c2){return 0.0;}

	}
	private class SingleLinkSurveyor implements Surveyor{
		public double getDistance(Cluster c1,Cluster c2){return 0.0;}

	}
	private  class Pair{
		public Cluster First;
		public  Cluster Second;
	}


}

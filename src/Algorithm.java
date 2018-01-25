import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * This class represents the Hierarchical clustering algorithm.
 * it has gets the type of clustering method and clusters it by it.
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

	/***
	 * runs the algorithm
	 * @param type type of algorithm
	 */
	public void Run(String type){
		Surveyor surveyor=_map.get(type);
		while(_clusters.size()!=_numOfClusters){
			Pair closestClusters=GetClosestClusters(surveyor);
			closestClusters.First.GetPoints().addAll(closestClusters.Second.GetPoints());
			closestClusters.First.UpdatePointsIndex();
			_clusters.remove(closestClusters.Second);
		}

	}

	/***
	 * updates the clusters indexes.
	 * iterates over the current clusters and gives every cluster its index.
	 */
	public void UpdateClustersIndexes(){
		int i=1;
		for(Cluster c: _clusters){
			c.SetIndex(i);
			c.UpdatePointsIndex();
			i++;
		}
	}

	/**
	 * gets a surveyor and (using it) returns a pair of most close clusters
	 * @param surveyor surveyor
	 * @return pair of close clusters
	 */
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

	/**
	 * initializes clusters
	 * @param points point to cluster
	 */
	public void initializeClusters(List<Point> points){
		_clusters=new LinkedList<>();
		for (Point p: points) {
			Cluster c= new Cluster(p);
			//c.updatePointsIndex();
			_clusters.add(c);
		}
	}

	/***
	 * The Surveyor class.
	 * this class represents a surveyor that knows how to calculate distances between clusters
	 */
	private abstract class  Surveyor{
		/**
		 * calculates distance between clusters
		 * @param c1 1st cluster
		 * @param c2 2nd cluster
		 * @return distance between them
		 */
		abstract double  GetDistance(Cluster c1,Cluster c2);

		/***
		 * calculates distance between points.
		 * @param current 1st point
		 * @param other 2nd point
		 * @return
		 */
		protected double GetDistance(Point current,Point other){
			double a = (Math.pow(current.GetX()-other.GetX(),2)) +
					           (Math.pow(current.GetY()-other.GetY(),2));
			return Math.sqrt(a);
		}
	}

	/***
	 * This class represents an AverageLinkSurveyor.
	 * it calculates the distance between clusters using the average link method
	 */
	private class AverageLinkSurveyor extends Surveyor{
		/**
		 * calculates distance between clusters
		 * @param c1 1st cluster
		 * @param c2 2nd cluster
		 * @return distance between them
		 */
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
	/***
	 * This class represents an SingleLinkSurveyor.
	 * it calculates the distance between clusters using the single link method
	 */
	private class SingleLinkSurveyor extends Surveyor{
		/**
		 * calculates distance between clusters
		 * @param c1 1st cluster
		 * @param c2 2nd cluster
		 * @return distance between them
		 */
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

	/***
	 * represents a paie of clusters
	 * has 2 properties- First (cluster)& Second (cluster)
	 */
	private  class Pair{
		public Cluster First;
		public  Cluster Second;
	}


}

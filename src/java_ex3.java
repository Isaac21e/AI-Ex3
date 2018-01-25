import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Isaac on 1/24/2018.
 */
public class java_ex3 {


	public static void main(String args[]){
		String[] content=FileHandler.Read("input4.txt");
		String algorithmType= content[0];
		int numOfClusters= Integer.parseInt(content[1]);
		String[] stringPoints =Arrays.copyOfRange(content, 2, content.length);
		List<Point> points=Parser.getPoints(stringPoints);
		Algorithm algorithm= new Algorithm(points,numOfClusters);
		algorithm.Run(algorithmType);
		algorithm.UpdateClustersIndexes();
		StringBuffer output=new StringBuffer();
		for (Point p : points){
			output.append(p.GetNumOfCluster());
			output.append("\n");
		}
		FileHandler.Write("output4.txt",output.toString());


	}

}

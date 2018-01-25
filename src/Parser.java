import java.util.LinkedList;
import java.util.List;

/**
 * Created by Isaac on 1/25/2018.
 */
public class Parser {


	public static List<Point> getPoints(String[] stringPoints){
		List<Point> points= new LinkedList<>();
		for (String str:stringPoints) {
			String[] split=str.split(",");
			double x=Double.parseDouble(split[0]);
			double y=Double.parseDouble(split[1]);
			Point p= new Point(x,y);
			points.add(p);
		}
		return points;
	}
}

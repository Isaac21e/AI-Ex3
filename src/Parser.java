import java.util.LinkedList;
import java.util.List;

/**
 * Represents a Parser class
 * gets points as string an parses it to doubles.
 */
public class Parser {

	/***
	 * gets points aas string an parses it to doubles
	 * @param stringPoints strings to parse
	 * @return points (doubles)
	 */
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

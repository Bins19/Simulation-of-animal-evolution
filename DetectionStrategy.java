import java.awt.Point;
import java.util.SortedMap;

public interface DetectionStrategy {
	
	public Animal detect(Animal prey, SortedMap<Animal, Point> field);

}

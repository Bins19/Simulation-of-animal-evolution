import java.awt.Point;
import java.util.Random;

public class DefaultReproduction implements ReproductionStrategy {

	@Override
	public Animal reproduce(double probabilityReproduction, Animal animal) {
		// TODO Auto-generated method stub
		double p = new Random().nextDouble();
		if(p < probabilityReproduction) {
			if (animal.getColor() == "ORANGE")
				return new Fox(0, new Point(0, 0));
			else if (animal.getColor() == "YELLOW")
				return new Chicken(0, new Point(0, 0));
			else if (animal.getColor() == "GREEN")
				return new Viper(0, new Point(0, 0));
		}
		return null;
	}

}

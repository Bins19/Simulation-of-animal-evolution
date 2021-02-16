import java.awt.Point;

public class Chicken extends Animal {
	
	public Chicken(int id, Point position) {
		super(id, position);
		this.setColor("YELLOW");
		this.setPreyDetectionProbability(0.1);
		this.setReproductionProbability(0.4);
		this.setAttackBonus(6);
		this.setAttackCapability(15);	
		this.setDefenseCapability(23);
		this.setTravelSpeed(2);
		this.setFoodEndurance(20);
		this.setLifeExpectancy(150);
	}	

}

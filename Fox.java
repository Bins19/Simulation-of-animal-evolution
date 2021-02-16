import java.awt.Point;

public class Fox extends Animal {
	
	public Fox(int id, Point position) {
		super(id, position);
		this.setColor("ORANGE");
		this.setPreyDetectionProbability(0.2);
		this.setReproductionProbability(0.14);
		this.setAttackBonus(12);
		this.setAttackCapability(20);
		this.setDefenseCapability(7);
		this.setTravelSpeed(3);
		this.setFoodEndurance(8);
		this.setLifeExpectancy(250);
	}

}

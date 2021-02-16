import java.awt.Point;

public class Viper extends Animal {
	
	public Viper(int id, Point position) {
		super(id, position);
		this.setColor("GREEN");
		this.setPreyDetectionProbability(0.5);
		this.setReproductionProbability(0.5);
		this.setAttackBonus(8);
		this.setAttackCapability(5);
		this.setDefenseCapability(17);
		this.setTravelSpeed(1);
		this.setFoodEndurance(35);
		this.setLifeExpectancy(120);
	}	

}

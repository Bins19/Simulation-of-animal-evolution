import java.util.Random;

public class DefaultAlimentation implements AlimentationStrategy {

	@Override
	public boolean eat(int attackBonus, int attackCapability, Animal prey) {
		// TODO Auto-generated method stub
		int n = new Random().nextInt(attackBonus + 1) + attackCapability;
		return (n > prey.getDefenseCapability()) ? true : false;
	}

}

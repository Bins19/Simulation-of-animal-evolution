public class DefaultDeath implements DeathStrategy {

	@Override
	public boolean die(int fast, int foodEndurance, int age, int lifeExpectancy) {
		// TODO Auto-generated method stub
		return (fast >= foodEndurance) || (age >= lifeExpectancy);
	}

}

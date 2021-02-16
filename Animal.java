import java.awt.Point;

public abstract class Animal implements Comparable<Animal> {
	
	protected int id;
	protected Point position;
	protected int age;
	protected int fast;
	protected boolean alive;
	protected int decomposition;
	protected boolean turn;
	
	protected String color;
	protected double preyDetectionProbability;
	protected double reproductionProbability;
	protected int attackBonus;
	protected int attackCapability;
	protected int defenseCapability;
	protected int travelSpeed;
	protected int  foodEndurance;
	protected int lifeExpectancy;
	
	public Animal(int id, Point position) {
		this.id = id;
		this.position = position;
		this.age = 0;
		this.fast = 0;
		this.alive = true;
		this.decomposition = 0;
		this.turn = true;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Point getPosition() {
		return position;
	}


	public void setPosition(Point position) {
		this.position = position;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}

	public int getFast() {
		return fast;
	}

	public void setFast(int fast) {
		this.fast = fast;
	}	

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public int getDecomposition() {
		return decomposition;
	}

	public void setDecomposition(int decomposition) {
		this.decomposition = decomposition;
	}

	public boolean isTurn() {
		return turn;
	}

	public void setTurn(boolean turn) {
		this.turn = turn;
	}

	public int getDefenseCapability() {
		return defenseCapability;
	}

	public void setDefenseCapability(int defenseCapability) {
		this.defenseCapability = defenseCapability;
	}

	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}

	public double getPreyDetectionProbability() {
		return preyDetectionProbability;
	}

	public void setPreyDetectionProbability(double preyDetectionProbability) {
		this.preyDetectionProbability = preyDetectionProbability;
	}

	public double getReproductionProbability() {
		return reproductionProbability;
	}

	public void setReproductionProbability(double reproductionProbability) {
		this.reproductionProbability = reproductionProbability;
	}
	
	public int getAttackBonus() {
		return attackBonus;
	}

	public void setAttackBonus(int attackBonus) {
		this.attackBonus = attackBonus;
	}

	public int getAttackCapability() {
		return attackCapability;
	}

	public void setAttackCapability(int attackCapability) {
		this.attackCapability = attackCapability;
	}

	public int getTravelSpeed() {
		return travelSpeed;
	}
	
	public void setTravelSpeed(int travelSpeed) {
		this.travelSpeed = travelSpeed;
	}

	public int getFoodEndurance() {
		return foodEndurance;
	}
	
	public void setFoodEndurance(int foodEndurance) {
		this.foodEndurance = foodEndurance;
	}

	public int getLifeExpectancy() {
		return lifeExpectancy;
	}
	
	public void setLifeExpectancy(int lifeExpectancy) {
		this.lifeExpectancy = lifeExpectancy;
	}
	
	public boolean eat(Animal animal) {
		AlimentationStrategy alimentation = new DefaultAlimentation();
		return alimentation.eat(getAttackBonus(), getAttackCapability(), animal);
	}
	
	public Animal reproduce(Animal animal) {
		ReproductionStrategy reproduction = new DefaultReproduction();
		return reproduction.reproduce(getReproductionProbability(), animal);
	}
	
	public boolean die(boolean haveEaten) {
		DeathStrategy death = new DefaultDeath();
		boolean result = death.die(getFast(), getFoodEndurance(), getAge(), getLifeExpectancy());
		if(!result) {
			this.age ++;
			if(!haveEaten) {
				this.fast ++;
			} else {
				this.fast = 0;
			}
			System.out.println("L'animal a maintenant " + this.getAge() + " an(s). Il n'a pas mangé depuis " + this.getFast() + " tour(s).");
		} else {
			System.out.println("L'animal est mort de vieillesse ou de faim. Il va se décomposer");
			setAlive(false);
			setDefenseCapability(-1);
		}
		return result;
	}
	
	public boolean decompose() {
		this.decomposition ++;
		return this.decomposition >= 10;
	}
	
	@Override
	public int compareTo(Animal animal) {
		if(getAge() < animal.getAge())
			return -1;
		else if(getAge() == animal.getAge())
			return 0;
		else
			return 1;
	}
	
}

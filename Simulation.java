import java.awt.Point;
import java.util.ArrayList;
import java.util.Map;

public class Simulation {

	private ChampGraphique land;
	private int lastId;
	
	public Simulation(int width, int height, int foxes, int chickens, int vipers) {
		this.land = new ChampGraphique(width, height, foxes, chickens, vipers);
		this.lastId = foxes + chickens + vipers;
	}
	
	
	
	public ChampGraphique getLand() {
		return land;
	}

	public void simulate() {
		Animal animal, prey, partner = null, babyAnimal;
		boolean haveEaten;
		ArrayList<Animal> deadAnimals;
		ArrayList<Animal> babiesAnimals;
		int i = 1;
		while(i > 0) {
			deadAnimals = new ArrayList<Animal>();
			babiesAnimals = new ArrayList<Animal>();
			System.out.println("-------------------------------------------------------Jour: " + i + "-------------------------------------------------------");
			System.out.println(". Nombre d'nimaux: " + land.getField().size());
			if(!(land.isThereAnimals())) { 
				//HashMap<Animal, Point> field = (HashMap<Animal, Point>) land.getField();
				land.setTurnTrue();				
		        for (Map.Entry<Animal, Point> entry : land.getField().entrySet()) {
		            animal = entry.getKey();
		            System.out.println("-----Tour de l'animal d'id " + animal.getId() + " et de couleur " + animal.getColor() + " à la position " + animal.getPosition() + "-----");
		            if(!(animal.isAlive())) {
		            	System.out.println("Cet animal est mort. Il se décompose depuis " + animal.getDecomposition() + " jours.");
		            	boolean isDecomposing = animal.decompose(); 
		            	if(isDecomposing) { 
		            		System.out.println("Cet animal est complètement décomposé. Il va disparaître. Bye!");
		            		deadAnimals.add(animal);
		            	}
		            } else {
		            	if(animal.isTurn()) {
		            		haveEaten = false;
		            		prey = land.lookingForFood(animal);
			            	if(prey != null) {
			            		System.out.println("Cet animal a trouvé une proie d'id " + prey.getId() + " et de couleur " + prey.getColor() + " à la position " + prey.getPosition() + ". Va-t-il la manger?");
			            		haveEaten = animal.eat(prey);
			            		if(haveEaten) {
			            			System.out.println("Il l'a mangé. Il est mort.");
			            			deadAnimals.add(prey);
			            			prey.setAlive(false);
			            			prey.setTurn(false);
			            		} else {
			            			land.moveAnimalByOneCase(prey);
			            			System.out.println("La proie a réussi à s'échapper. Il a fui à la position " + prey.getPosition() + ".");
			            		}
			            	} else {
			            		System.out.println("L'animal n'a pas trouvé de proie.");
			            		if(land.getActualWeather() == 0) {
			            			System.out.println("C'est l'hiver. Il faut se réchauffer. L'animal cherche à se reproduire.");
			            			partner = land.seekToReproduce(animal);
			            			if(partner != null) {
			            				System.out.println("Il a trouvé un/une partenaire d'id " + partner.getId() + " à la position " + partner.getPosition() + ". Va-t-il/elle accepter de s'accoupler?");
			            				babyAnimal = animal.reproduce(partner);
			            				if(babyAnimal != null) {			            					
			            					partner.setTurn(false);
			            					this.lastId ++;
			            					babyAnimal.setId(this.lastId);
			            					System.out.println("Oui et ils ont eu un bébé d'id " + babyAnimal.getId() + ".");
			            					babiesAnimals.add(babyAnimal);
			            				} else {
			            					System.out.println("Oui mais ils n'ont pas réusii à avoir d'enfant.");
			            				}
			            			}
			            		} 
			            		if(partner == null) {
			            			System.out.println("Il n'a pas trouvé de partenaire.");
		            				land.moveAnimal(animal);
		            				System.out.println("L'animal s'est déplacé à la position " + animal.getPosition() + ".");
			            		}
			            	}
			            	animal.die(haveEaten);
		            	}
		            }
		            //land.repaint();
		            //land.setTurnTrue();
		            System.out.println();
		            try {
			            Thread.sleep(000);
			        } catch (InterruptedException e) {
			            e.printStackTrace();
			        }
			        //land.sortHashMap();
			        //land.repaint();
		        }
		        for(Animal deadAnimal : deadAnimals) {
		        	land.getField().remove(deadAnimal);
		        	System.out.println("L'animal d'id " + deadAnimal.getId() + " vient de disparaître.");
		        }
		        for(Animal babyFuckingAnimal: babiesAnimals) {
		        	land.addAnimal(babyFuckingAnimal);
		        	System.out.println("Le bébé animal d'id " + babyFuckingAnimal.getId() + " est né à la position " + babyFuckingAnimal.getPosition() + ".");
		        }
		        try {
		            Thread.sleep(000);
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
		        land.sortHashMap();
		        land.repaint();
		        if(i % 30 == 0) {
		        	land.changeWeather();
		        	System.out.println("Changement de saison!");
		        }	
		        System.out.println();
		        System.out.println();
		        i++;
			} else {
				i = 0;
				System.out.println("Fin de la simulation.");
			}
		}
		System.exit(0);
	}
	
}

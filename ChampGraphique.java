
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Point;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

//Merci à StackOverflow pour sa précieuse contribution !


public class ChampGraphique extends JPanel {
	
	private int largeur, hauteur;
	private HashMap<Animal, Point> field;
	private int numberFoxes;
	private int numberChickens;
	private int numberVipers;
	private Color weathers[];
	private int actualWeather;
	
	//private List<Point> casesAColorier;

	/**
	 * Constructeur.
	 * @param largeur La largeur (en nombre de cases) de la grille affichée.
	 * @param hauteur La hauteur (en nombre de cases) de la grille affichée.
	 */
	public ChampGraphique(int largeur, int hauteur, int foxes, int chickens, int vipers) {
		this.largeur = largeur;
		this.hauteur = hauteur;
		//casesAColorier = new ArrayList<>(25);

		JFrame window = new JFrame();
		window.setSize(largeur*10+50, hauteur*10+50);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(this);
		window.setVisible(true);
		
		field = new HashMap<Animal, Point>();
		numberFoxes = foxes;
		numberChickens = chickens;
		numberVipers = vipers;
		weathers = new Color[]{Color.BLUE, Color.GREEN, Color.RED, Color.GRAY};
		actualWeather = 0;
		
		Point p;
		Random r = new Random();
		int x, y;
		int i = 0;
		while(i < foxes) {
			x = r.nextInt(largeur);
			y = r.nextInt(hauteur);
			p = new Point(x, y);
			if(!(field.values().contains(p))) {
				field.put(new Fox(i+1, p), p);
				try {
		            Thread.sleep(0);
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
				i ++;
			}
		}
		repaint();
		while(i < foxes + chickens) {
			x = r.nextInt(largeur);
			y = r.nextInt(hauteur);
			p = new Point(x, y);
			if(!(field.values().contains(p))) {
				field.put(new Chicken(i+1, p), p);
				try {
		            Thread.sleep(0);
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
				i ++;
			}
		}
		repaint();
		while(i < foxes + chickens + vipers) {
			x = r.nextInt(largeur);
			 y = r.nextInt(hauteur);
			p = new Point(x, y);
			if(!(field.values().contains(p))) {
				field.put(new Viper(i+1, p), p);
				try {
		            Thread.sleep(0);
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
				i ++;
				
			}
		}
		repaint();
		sortHashMap();
	}

	@Override
	//Fonction d'affichage de la grille.
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Map.Entry<Animal, Point> mapEntry: field.entrySet()) {
			Animal animal = (Animal) mapEntry.getKey();
			Point fillCell = (Point) mapEntry.getValue();
			int cellX = 10 + (fillCell.x * 10);
			int cellY = 10 + (fillCell.y * 10);
			g.setColor(convertStringToColor(animal.getColor()));
			g.fillRect(cellX, cellY, 10, 10);
		}
		g.setColor(weathers[actualWeather]);
		g.drawRect(10, 10, largeur*10, hauteur*10);
		for (int i = 10; i <= largeur*10; i += 10) {
			g.drawLine(i, 10, i, hauteur*10+10);
		}
		for (int i = 10; i <= hauteur*10; i += 10) {
			g.drawLine(10, i, largeur*10+10, i);
		}
	}

	/**
	 * Fonction permettant de colorier, en rouge, une case de la grille
	 * @param x Abscisse de la case à colorier (entre 0 et largeur de grille - 1).
	 * @param y Ordonnée de la case à colorier (entre 0 et hauteur de grille - 1).
	 */
	/*public void colorierCase(int x, int y) 
	{
		casesAColorier.add(new Point(x, y));
		repaint();
	}*/
	
	/**
	 * Accesseur.
	 * @return Renvoie la largeur de la grille
	 */
	public int getLargeur() {
		return largeur;
	}
	
	/**
	 * Accesseur.
	 * @return Renvoie la hauteur de la grille
	 */
	public int getHauteur() {
		return hauteur;
	}
	
	public Map<Animal, Point> getField() {
		return field;
	}
	
	public int getActualWeather() {
		return actualWeather;
	}

	public void setTurnTrue() {
        for (Map.Entry<Animal, Point> mapEntry : field.entrySet()) {
            Animal animal = mapEntry.getKey();
            animal.setTurn(true);
        }
	}
	
	public boolean isThereAnimals() {
		return field.isEmpty();
	}
	
	private Color convertStringToColor(String colorString) {
		Color color;
		try {
		    Field field = Class.forName("java.awt.Color").getField(colorString);
		    color = (Color)field.get(null);
		} catch (Exception e) {
		    color = null; // Not defined
		}
		return color;
	}
	
	public void sortHashMap(){
        List<Map.Entry<Animal, Point>> list = new LinkedList<Map.Entry<Animal, Point>>(field.entrySet() );
        Collections.sort(list, new Comparator<Map.Entry<Animal, Point>>() {
           public int compare(Map.Entry<Animal, Point>o1, Map.Entry<Animal, Point> o2) {
              //comparer deux cl�s
              return (o1.getKey()).compareTo(o2.getKey());
           }
        });
        //cr�er une nouvelle HashMap � partir de LinkedList
        HashMap<Animal, Point> sortHMap = new HashMap<Animal, Point>();
        for(Map.Entry<Animal, Point> entry : list) {
            sortHMap.put(entry.getKey(), entry.getValue());
        }
        field = sortHMap;
    }
	
	public void changeWeather() {
		actualWeather ++;
		if(actualWeather == 4)
			actualWeather = 0;
	}
	
	public void addAnimal(Animal animal) {
		if(field.size() < largeur*hauteur) {
			Point p = null;
			boolean contains = true;
			while(contains) {
				Random r = new Random();
				p = new Point(r.nextInt(largeur), r.nextInt(hauteur));
				contains = field.containsValue(p);
			}
			animal.setPosition(p);
			field.put(animal, p);
//			repaint();
		}
	}
	
	private void lookingToMove(LinkedList<int[]> possibleMoves, Animal animal) {
		Random r;
		int addX, addY;
		boolean check = (possibleMoves.size() == 0);
		int x = animal.getPosition().x, y = animal.getPosition().y;
		while(!check) {
			r = new Random();
			int i = r.nextInt(possibleMoves.size());
			addX = possibleMoves.get(i)[0]; 
			addY = possibleMoves.get(i)[1];
			if(!(field.containsValue(new Point(x+addX, y+addY)))) {
				animal.setPosition(new Point(x+addX, y+addY));
				//field.replace(animal, animal.getPosition());
				check = true;
			}
			else {
				possibleMoves.remove(i);
				check = (possibleMoves.size() == 0);
			}
		}
	}
		
	private Animal searchAnimal(int x, int y) {
		Set<Animal> animals = field.keySet();
		for(Animal animal: animals) {
			if(field.get(animal).x == x  && field.get(animal).y == y)
				return animal;
		}
		return null;
	}
	
	private Animal searchingAnAnimal(Animal animal, String color) {
		Random r;
		int addX, addY;
		Animal target = null;
		Animal possibleTarget;
		int[] move1 = {1, 0}, move2 = {1, 1}, move3 = {0, 1}, move4 = {-1, 1}, move5 = {-1, 0};
		int[] move6 = {-1, -1}, move7 = {0, -1}, move8= {1, -1};
		LinkedList<int[]> adjacentBoxes = new LinkedList<int[]>();
		adjacentBoxes.add(move1);
		adjacentBoxes.add(move2);
		adjacentBoxes.add(move3);
		adjacentBoxes.add(move4);
		adjacentBoxes.add(move5);
		adjacentBoxes.add(move6);
		adjacentBoxes.add(move7);
		adjacentBoxes.add(move8);
		boolean check = (adjacentBoxes.size() == 0);
		int x = animal.getPosition().x, y = animal.getPosition().y;
		while(!check) {
			r = new Random();
			int i = r.nextInt(adjacentBoxes.size());
			addX = adjacentBoxes.get(i)[0]; 
			addY = adjacentBoxes.get(i)[1];
			if(field.containsValue(new Point(x+addX, y+addY))) {
				possibleTarget = searchAnimal(x+addX, y+addY);
				if(possibleTarget != null) {
					if(possibleTarget.getColor().equals(color)) {
						target = possibleTarget;
						check = true;
					}
					else {
						adjacentBoxes.remove(i);
						check = (adjacentBoxes.size() == 0);
					} 
				} else {
					adjacentBoxes.remove(i);
					check = (adjacentBoxes.size() == 0);
				}
			} else {
				adjacentBoxes.remove(i);
				check = (adjacentBoxes.size() == 0);
			}
		}
		return target;
	}
	
	
	public void moveAnimalByOneCase(Animal animal) {
		if(field.size() < largeur*hauteur) {
			LinkedList<int[]> list;
			Point p = animal.getPosition();
			int x = p.x, y = p.y;
			if(x == 0 && y == 0) {
				int[] move1 = {1, 0}, move2 = {1, 1}, move3 = {0, 1};
				list = new LinkedList<int[]>();
				list.add(move1);
				list.add(move2);
				list.add(move3);
			}
			else if(x == largeur-1 && y == 0) {
				int[] move1 = {0, 1}, move2 = {-1, 1}, move3 = {-1, 0};
				list = new LinkedList<int[]>();
				list.add(move1);
				list.add(move2);
				list.add(move3);
			}
			else if(x == largeur-1 && y == hauteur-1) {
				int[] move1 = {-1, 0}, move2 = {-1, -1}, move3 = {0, -1};
				list = new LinkedList<int[]>();
				list.add(move1);
				list.add(move2);
				list.add(move3);
			}
			else if(x == 0 && y == hauteur-1) {
				int[] move1 = {1, 0}, move2 = {0, -1}, move3 = {1, -1};
				list = new LinkedList<int[]>();
				list.add(move1);
				list.add(move2);
				list.add(move3);
			}
			else if(x == 0) {
				int[] move1 = {1, 0}, move2 = {1, 1}, move3 = {0, 1}, move4 = {0, -1}, move5 = {1, -1};
				list = new LinkedList<int[]>();
				list.add(move1);
				list.add(move2);
				list.add(move3);
				list.add(move4);
				list.add(move5);
			}
			else if(x == largeur-1) {
				int[] move1 = {0, 1}, move2 = {-1, 1}, move3 = {-1, 0}, move4 = {-1, -1}, move5 = {0, -1};
				list = new LinkedList<int[]>();
				list.add(move1);
				list.add(move2);
				list.add(move3);
				list.add(move4);
				list.add(move5);
			}
			else if(y == 0) {
				int[] move1 = {1, 0}, move2 = {1, 1}, move3 = {0, 1}, move4 = {-1, 1}, move5 = {-1, 0};
				list = new LinkedList<int[]>();
				list.add(move1);
				list.add(move2);
				list.add(move3);
				list.add(move4);
				list.add(move5);
			}
			else if(y == hauteur-1) {
				int[] move1 = {1, 0}, move2 = {-1, 0}, move3 = {-1, -1}, move4 = {0, -1}, move5 = {1, -1};
				list = new LinkedList<int[]>();
				list.add(move1);
				list.add(move2);
				list.add(move3);
				list.add(move4);
				list.add(move5);
			}
			else {
				int[] move1 = {1, 0}, move2 = {1, 1}, move3 = {0, 1}, move4 = {-1, 1}, move5 = {-1, 0};
				int[] move6 = {-1, -1}, move7 = {0, -1}, move8= {1, -1};
				list = new LinkedList<int[]>();
				list.add(move1);
				list.add(move2);
				list.add(move3);
				list.add(move4);
				list.add(move5);
				list.add(move6);
				list.add(move7);
				list.add(move8);
			}
			lookingToMove(list, animal);
			//System.out.println(animal.getPosition());
			//field.put(animal, animal.getPosition());
			field.replace(animal, animal.getPosition());
			repaint();
		}
	}
		
	public void moveAnimal(Animal animal) {
		int numberMoves = animal.getTravelSpeed();
		for(int i = 0; i < numberMoves; i++) {
			moveAnimalByOneCase(animal);
			try {
	            Thread.sleep(00);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
		}
			
	}
	
	public Animal seekToReproduce(Animal animal) {
		return searchingAnAnimal(animal, animal.getColor());
	}
	
	public Animal lookingForFood(Animal animal) {
		switch (animal.getColor()) {
			case "ORANGE":
				return searchingAnAnimal(animal, "YELLOW");
			case "YELLOW":
				return searchingAnAnimal(animal, "GREEN");
			case "GREEN":
				return searchingAnAnimal(animal, "ORANGE");
			default:
				return null;
		}
	}
	
}

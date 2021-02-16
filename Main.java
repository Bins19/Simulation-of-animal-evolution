import javax.swing.JFrame;

import java.awt.List;
import java.awt.Point;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
	
	public static void main(String[] args) {
		
		Simulation simulation = new Simulation(50, 60, 10, 10, 10);
		simulation.simulate();
	}

}

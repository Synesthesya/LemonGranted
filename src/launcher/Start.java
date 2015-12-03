package launcher;

import graphic.Frame;
import control.Controller;
import control.MyShipController;
import core.Player;

public class Start {
	
	public static void main(String[] args) {
		
		Player uno=new Player();
		
		Controller c=new MyShipController(uno);
		
		Frame f=new Frame(c, null, null);
		
		//System.out.println(Start.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		
		f.getPanel().getInfo().mockup(Start.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		
	}

}

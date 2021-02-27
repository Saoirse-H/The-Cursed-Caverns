/*
 * Saoirse Houlihan
 * 17340803
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import util.*;


/*
 * Created by Abraham Campbell on 15/01/2020.
 *   Copyright (c) 2020  Abraham Campbell

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
   
   (MIT LICENSE ) e.g do what you want with this :-) 
 
 * Credits: Kelly Charles (2020)
 */ 
public class Viewer extends JPanel {
	private static final long serialVersionUID = 1L;

	private long CurrentAnimationTime= 0; 
	
	Model gameworld;
	 
	public Viewer(Model world) {
		this.gameworld = world;
	}

	public Viewer(LayoutManager layout) {
		super(layout);
	}

	public Viewer(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
	}

	public Viewer(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
	}

	public void updateview() {
		this.repaint();
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		CurrentAnimationTime++; // runs animation time step 
		
		Player player = gameworld.getPlayer();
		
		//Draw background 
		drawBackground(g);
		
		//Draw player
		drawPlayer(	(int) player.getCentre().getX(), (int) player.getCentre().getY(), (int) player.getWidth(),
					(int) player.getHeight(), player.getTexture(), player.getDirection(), g);
		  
		//Draw Bullets 
		gameworld.getBullets().forEach((bullet) -> { 
			drawBullet(	(int) bullet.getCentre().getX(), (int) bullet.getCentre().getY(), (int) bullet.getWidth(), 
						(int) bullet.getHeight(), bullet.getTexture(), bullet.getDirection(), g);	 
		}); 
		
		//Draw Enemies   
		gameworld.getEnemies().forEach((enemy) -> {
			drawEnemies((int) enemy.getCentre().getX(), (int) enemy.getCentre().getY(), (int) enemy.getWidth(), 
						(int) enemy.getHeight(), enemy.getTexture(), enemy.getDirection(), g);	 
		 
	    }); 
		
		//Draw UI
		drawHealth(gameworld.getHealth(), g);
		if(player.getHasKey())
			drawKey(g);
	}
	
	private void drawEnemies(int x, int y, int width, int height, String texture, char direction, Graphics g) {
		File TextureToLoad = new File(texture);  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
		try {
			Image myImage = ImageIO.read(TextureToLoad);
			int xFrame = ((int) ((CurrentAnimationTime%9)/3)) * ((myImage.getWidth(null))/3); //slows down animation so every 3 frames we get another frame so every 50ms 
			int yFrame = 0;
			switch(direction) {
				case 's':
					yFrame = 0;
					break;
				case 'a':
					yFrame = (int) ((myImage.getHeight(null)) / 4);
					break;
				case 'd':
					yFrame = (int) (((myImage.getHeight(null)) / 4) * 2);
					break;
				case 'w':
					yFrame = (int) ((((myImage.getHeight(null)) / 4) *2) + ((myImage.getHeight(null)) / 4));
					break;
			}
			g.drawImage(myImage, x,y, x+width, y+height, xFrame, yFrame, xFrame + (myImage.getWidth(null)/3), yFrame + (myImage.getHeight(null)/4), null); 	
		} catch (IOException e) { e.printStackTrace(); } 
	}

	private void drawBackground(Graphics g) {
		File TextureToLoad = new File("res/map.png");  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
		try {
			Image myImage = ImageIO.read(TextureToLoad); 
			 g.drawImage(myImage, 0,0, 1024, 1024, 0 , 0, 1024, 1024, null); 
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	private void drawBullet(int x, int y, int width, int height, String texture, char direction, Graphics g) {
		File TextureToLoad = new File(texture);  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
		try {
			Image myImage = ImageIO.read(TextureToLoad); 
			//64 by 128 
			int xframe = 0;
			int yframe = 0;
			
			if(texture.contains("witch")) {
				xframe = 32;
				yframe = 32;
				g.drawImage(myImage, x,y, x+width, y+height, 0 , 0, xframe, yframe, null);
			}
			
			if(texture.contains("archer")) {
				yframe = 50;
				switch(direction) {
					case 'a':
						xframe = 0;
						break;
					case 'd':
						xframe = 43;
						break;
					case 'w':
						xframe = 86;
						break;
					case 's':
						xframe = 129;
						break;
				}
				g.drawImage(myImage, x,y, x+width, y+height, xframe , 0, xframe + 42, yframe, null);
			}
			
			if(texture.contains("brawler")) {
				yframe = 52;
				xframe = (int) ((CurrentAnimationTime%4) * ((myImage.getWidth(null))/4));
				g.drawImage(myImage, x, y, x+width, y+width, xframe, 0, xframe + (myImage.getWidth(null)/4), yframe, null);
			}
		} catch (IOException e) { e.printStackTrace(); }
	}	

	private void drawPlayer(int x, int y, int width, int height, String texture, char direction, Graphics g) { 
		File TextureToLoad = new File(texture);  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
		try {
			Image myImage = ImageIO.read(TextureToLoad);
			
			//The sprite is 32x32 pixel wide, with 4 rows and 3 columns in each row
			int xFrame = ((int) ((CurrentAnimationTime%15)/5))*32; //slows down animation so every 5 frames we get another frame so every 50ms 
			int yFrame = 0;
			switch(direction) {
				case 's':
					yFrame = 0;
					break;
				case 'a':
					yFrame = 32;
					break;
				case 'd':
					yFrame = 64;
					break;
				case 'w':
					yFrame = 96;
					break;
			}
			g.drawImage(myImage, x,y, x+width, y+height, xFrame, yFrame, xFrame+31, yFrame+32, null); 
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	private void drawHealth(int health, Graphics g) {
		Font font = new Font("SansSerif", Font.PLAIN, 20);
		g.setFont(font);
		g.setColor(Color.white);
		g.drawString("Health: " + health, 900, 20);
	}
	
	private void drawKey(Graphics g) {
		File textureToLoad = new File("res/key.png");
		try {
			Image myImage = ImageIO.read(textureToLoad);
			g.drawImage(myImage, 890, 10, 930, 60, 0, 0, 16, 16, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


/*
 * 
 * 
 *              VIEWER HMD into the world                                                             
                                                                                
                                      .                                         
                                         .                                      
                                             .  ..                              
                               .........~++++.. .  .                            
                 .   . ....,++??+++?+??+++?++?7ZZ7..   .                        
         .   . . .+?+???++++???D7I????Z8Z8N8MD7I?=+O$..                         
      .. ........ZOZZ$7ZZNZZDNODDOMMMMND8$$77I??I?+?+=O .     .                 
      .. ...7$OZZ?788DDNDDDDD8ZZ7$$$7I7III7??I?????+++=+~.                      
       ...8OZII?III7II77777I$I7II???7I??+?I?I?+?+IDNN8??++=...                  
     ....OOIIIII????II?I??II?I????I?????=?+Z88O77ZZO8888OO?++,......            
      ..OZI7III??II??I??I?7ODM8NN8O8OZO8DDDDDDDDD8DDDDDDDDNNNOZ= ......   ..    
     ..OZI?II7I?????+????+IIO8O8DDDDD8DNMMNNNNNDDNNDDDNDDNNNNNNDD$,.........    
      ,ZII77II?III??????DO8DDD8DNNNNNDDMDDDDDNNDDDNNNDNNNNDNNNNDDNDD+.......   .
      7Z??II7??II??I??IOMDDNMNNNNNDDDDDMDDDDNDDNNNNNDNNNNDNNDMNNNNNDDD,......   
 .  ..IZ??IIIII777?I?8NNNNNNNNNDDDDDDDDNDDDDDNNMMMDNDMMNNDNNDMNNNNNNDDDD.....   
      .$???I7IIIIIIINNNNNNNNNNNDDNDDDDDD8DDDDNM888888888DNNNNNNDNNNNNNDDO.....  
       $+??IIII?II?NNNNNMMMMMDN8DNNNDDDDZDDNN?D88I==INNDDDNNDNMNNMNNNNND8:..... 
   ....$+??III??I+NNNNNMMM88D88D88888DDDZDDMND88==+=NNNNMDDNNNNNNMMNNNNND8......
.......8=+????III8NNNNMMMDD8I=~+ONN8D8NDODNMN8DNDNNNNNNNM8DNNNNNNMNNNNDDD8..... 
. ......O=??IIIIIMNNNMMMDDD?+=?ONNNN888NMDDM88MNNNNNNNNNMDDNNNMNNNMMNDNND8......
........,+++???IINNNNNMMDDMDNMNDNMNNM8ONMDDM88NNNNNN+==ND8NNNDMNMNNNNNDDD8......
......,,,:++??I?ONNNNNMDDDMNNNNNNNNMM88NMDDNN88MNDN==~MD8DNNNNNMNMNNNDND8O......
....,,,,:::+??IIONNNNNNNDDMNNNNNO+?MN88DN8DDD888DNMMM888DNDNNNNMMMNNDDDD8,.... .
...,,,,::::~+?+?NNNNNNNMD8DNNN++++MNO8D88NNMODD8O88888DDDDDDNNMMMNNNDDD8........
..,,,,:::~~~=+??MNNNNNNNND88MNMMMD888NNNNNNNMODDDDDDDDND8DDDNNNNNNDDD8,.........
..,,,,:::~~~=++?NMNNNNNNND8888888O8DNNNNNNMMMNDDDDDDNMMNDDDOO+~~::,,,.......... 
..,,,:::~~~~==+?NNNDDNDNDDNDDDDDDDDNNND88OOZZ$8DDMNDZNZDZ7I?++~::,,,............
..,,,::::~~~~==7DDNNDDD8DDDDDDDD8DD888OOOZZ$$$7777OOZZZ$7I?++=~~:,,,.........   
..,,,,::::~~~~=+8NNNNNDDDMMMNNNNNDOOOOZZZ$$$77777777777II?++==~::,,,......  . ..
...,,,,::::~~~~=I8DNNN8DDNZOM$ZDOOZZZZ$$$7777IIIIIIIII???++==~~::,,........  .  
....,,,,:::::~~~~+=++?I$$ZZOZZZZZ$$$$$777IIII?????????+++==~~:::,,,...... ..    
.....,,,,:::::~~~~~==+?II777$$$$77777IIII????+++++++=====~~~:::,,,........      
......,,,,,:::::~~~~==++??IIIIIIIII?????++++=======~~~~~~:::,,,,,,.......       
.......,,,,,,,::::~~~~==+++???????+++++=====~~~~~~::::::::,,,,,..........       
.........,,,,,,,,::::~~~======+======~~~~~~:::::::::,,,,,,,,............        
  .........,.,,,,,,,,::::~~~~~~~~~~:::::::::,,,,,,,,,,,...............          
   ..........,..,,,,,,,,,,::::::::::,,,,,,,,,.,....................             
     .................,,,,,,,,,,,,,,,,.......................                   
       .................................................                        
           ....................................                                 
               ....................   .                                         
                                                                                
                                                                                
                                                                 GlassGiant.com
                                                                 */

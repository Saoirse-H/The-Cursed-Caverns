import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import util.UnitTests;
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
 */ 



public class MainWindow {
	private static JFrame frame = new JFrame("The Cursed Caverns");
	private static Model gameworld = new Model();
	private static Viewer canvas = new Viewer(gameworld);
	private Controller Controller = new Controller();
	
	private static Sound startMenuMusic = new Sound("res/audio/start-menu.wav");
	private static Sound inGameMusic = new Sound("res/audio/in-game-music.wav");
	private static Sound gameOverMusic = new Sound("res/audio/game-over.wav");
	private static Sound gameWonMusic = new Sound("res/audio/game-won.wav");
	private static int TargetFPS = 100;
	private static boolean startGame = false; 
	private static boolean inBeginning = false;
	private static boolean gameOver = false;
	private static boolean gameWon = false;
	private JLabel startMenu;
	  
	private static BufferedImage LoadImage(String imageName) throws IOException
	{
		File image = new File("res/" + imageName);
		BufferedImage bufferedImage = ImageIO.read(image);
		return bufferedImage;
	}
	public MainWindow() throws IOException {
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setLayout(null); 
	    
	    canvas.setBounds(0, 0, 1024, 1024); 
		canvas.setBackground(new Color(255,255,255));
		canvas.setVisible(false);   // this will become visible after you press the key. 
		frame.add(canvas);
		
	    JButton startMenuButton = new JButton("Start Game");  // start button 
	    startMenuButton.setBounds(369, 735, 284, 58); 
	    frame.add(startMenuButton);  
	    startMenuButton.addActionListener(new ActionListener() { 
	    	@Override
			public void actionPerformed(ActionEvent e) { 
	    		startMenuButton.setVisible(false);
	    		startMenu.setVisible(false); 
				canvas.setVisible(true); 
				canvas.addKeyListener(Controller);    //adding the controller to the Canvas  
				canvas.addMouseListener(Controller);
	            canvas.requestFocusInWindow();   // making sure that the Canvas is in focus so keyboard input will be taking in .
				startGame=true;
				inBeginning = true;
				startMenuMusic.stop();
			}
	    });  
        
		BufferedImage image = LoadImage("startscreen.png");
		startMenu = new JLabel(new ImageIcon(image));
		startMenu.setBounds(0, 0, image.getWidth(), image.getHeight());
		frame.add(startMenu); 
		
        frame.setSize(1024, 1050);
        frame.setResizable(false);
        frame.setVisible(true);  
	}

	public static void main(String[] args) {
		try {
			new MainWindow();  //sets up environment 
			startMenuMusic.loop();
			play();
			
			if(gameOver || gameWon) {
				inGameMusic.stop();
				if (gameOver)
					gameOverMusic.loop();
				else
					gameWonMusic.loop();
	
					BufferedImage myPicture = LoadImage(gameOver ? "gameoverscreen.png" : "gamewonscreen.png");
					JLabel endScreen = new JLabel(new ImageIcon(myPicture));
					endScreen.setBounds(0, 0, 1024, 1024);
					frame.add(endScreen); 
					canvas.setVisible(false);
					endScreen.setVisible(true);		
			}
		} catch (IOException e) { 
			e.printStackTrace();
		}
	} 
	
	public static void play() {
		while(!gameOver && !gameWon) { 
			//swing has timer class to help us time this but I'm writing my own, you can of course use the timer, but I want to set FPS and display it 
			int TimeBetweenFrames =  1000 / TargetFPS;
			long FrameCheck = System.currentTimeMillis() + (long) TimeBetweenFrames; 
			
			//wait till next time step 
			while (FrameCheck > System.currentTimeMillis()){ } 
			
			if(inBeginning) {
				Object[] characters = {"Archer", "Witch", "Brawler"};
				ImageIcon icon = new ImageIcon("res/hoodedfigure.png");
				int chosenCharacter = -1;
				while(chosenCharacter == -1) {	
					chosenCharacter = JOptionPane.showOptionDialog( frame, 
																	"Please choose a player:", 
																	"Who are you?", 
																	JOptionPane.DEFAULT_OPTION, 
																	JOptionPane.PLAIN_MESSAGE, 
																	icon, 
																	characters, 
																	characters[0]);
				}
				gameworld.selectPlayer(chosenCharacter);
				JOptionPane.showMessageDialog(frame, "You need to escape. It's dangerous.");
				JOptionPane.showMessageDialog(frame, "Find the key and get out of here.");
				inGameMusic.loop();
				JLabel lbl = new JLabel(new ImageIcon(("res/controlhelp.png")));
			    JOptionPane.showMessageDialog(	frame, 
			    								lbl, 
			    								"Controls", 
			    								JOptionPane.PLAIN_MESSAGE, 
			    								null);
				inBeginning = false;
			}
			
			if(startGame)
				gameloop();
			
			//Check if game over
			if(gameworld.getHealth() == 0) {
				gameOver = true;
			}
			
			//Check if opened chest without a key
			if(gameworld.getCheckedChestWithoutKey()) {
				JOptionPane.showMessageDialog(	frame, 
												"The key doesn't seem to be here...");
			}
			
			//Check if opened chest with key
			if(gameworld.getCheckedChestWithKey()) {
				ImageIcon icon = new ImageIcon("res/key.png");
				JOptionPane.showMessageDialog(	frame, 
												"You found the key!", 
												"Key found!", 
												JOptionPane.PLAIN_MESSAGE, 
												icon);
			}
			
			//Check if tried to open door with no key
			if(gameworld.getCheckedDoor() && !gameworld.getPlayer().getHasKey()) {
				JOptionPane.showMessageDialog(	frame, 
												"You can't open the door... Look for the key.");
			}
			
			//Check if opened door with key
			if(gameworld.getCheckedDoor() && gameworld.getPlayer().getHasKey()) {
				JOptionPane.showMessageDialog(	frame, 
												"You unlocked the door!");
				gameWon = true;
			}  
		}
	}
	
	//Basic Model-View-Controller pattern 
	private static void gameloop() { 		
		// model update   
		gameworld.gamelogic();
		
		// view update 
		canvas.updateview(); 
	}
}

/*
 * 
 * 

Hand shake agreement 
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,=+++
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,,,,,,:::::,=+++????
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,,,,,,,,,,,,,,:++++????+??
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,:,:,,:,:,,,,,,,,,,,,,,,,,,,,++++++?+++++????
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,=++?+++++++++++??????
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,:,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,~+++?+++?++?++++++++++?????
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,:::,,,,,,,,,,,,,,,,,,,,,,,,,,,~+++++++++++++++????+++++++???????
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,:,,,,,,,,,,,,,,,,,,,,,,:===+=++++++++++++++++++++?+++????????????????
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,,,,,,,,,,,,,,,,,,~=~~~======++++++++++++++++++++++++++????????????????
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,::::,,,,,,=~.,,,,,,,+===~~~~~~====++++++++++++++++++++++++++++???????????????
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,:,,,,,~~.~??++~.,~~~~~======~=======++++++++++++++++++++++++++????????????????II
:::::::::::::::::::::::::::::::::::::::::::::::::::::::,:,,,,:=+++??=====~~~~~~====================+++++++++++++++++++++?????????????????III
:::::::::::::::::::::::::::::::::::::::::::::::::::,:,,,++~~~=+=~~~~~~==~~~::::~~==+++++++==++++++++++++++++++++++++++?????????????????IIIII
::::::::::::::::::::::::::::::::::::::::::::::::,:,,,:++++==+??+=======~~~~=~::~~===++=+??++++++++++++++++++++++++?????????????????I?IIIIIII
::::::::::::::::::::::::::::::::::::::::::::::::,,:+????+==??+++++?++====~~~~~:~~~++??+=+++++++++?++++++++++??+???????????????I?IIIIIIII7I77
::::::::::::::::::::::::::::::::::::::::::::,,,,+???????++?+?+++???7?++======~~+=====??+???++++++??+?+++???????????????????IIIIIIIIIIIIIII77
:::::::::::::::::::::::::::::::::::::::,,,,,,=??????IIII7???+?+II$Z77??+++?+=+++++=~==?++?+?++?????????????III?II?IIIIIIIIIIIIIIIIIIIIIIIIII
::::::::::::::::::::::::::::::,,,,,,~=======++++???III7$???+++++Z77ZDZI?????I?777I+~~+=7+?II??????????????IIIIIIIIIIIIIIIIIIIIII??=:,,,,,,,,
::::::::,:,:,,,,,,,:::~==+=++++++++++++=+=+++++++???I7$7I?+~~~I$I??++??I78DDDO$7?++==~I+7I7IIIIIIIIIIIIIIIIII777I?=:,,,,,,,,,,,,,,,,,,,,,,,,
++=++=++++++++++++++?+????+??????????+===+++++????I7$$ZZ$I+=~$7I???++++++===~~==7??++==7II?~,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
+++++++++++++?+++?++????????????IIIII?I+??I???????I7$ZOOZ7+=~7II?+++?II?I?+++=+=~~~7?++:,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
+?+++++????????????????I?I??I??IIIIIIII???II7II??I77$ZO8ZZ?~~7I?+==++?O7II??+??+=====.,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
?????????????III?II?????I?????IIIII???????II777IIII7$ZOO7?+~+7I?+=~~+???7NNN7II?+=+=++,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
????????????IIIIIIIIII?IIIIIIIIIIII????II?III7I7777$ZZOO7++=$77I???==+++????7ZDN87I??=~,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
IIII?II??IIIIIIIIIIIIIIIIIIIIIIIIIII???+??II7777II7$$OZZI?+$$$$77IIII?????????++=+.,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII?+++?IIIII7777$$$$$$7$$$$7IIII7I$IIIIII???I+=,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII???????IIIIII77I7777$7$$$II????I??I7Z87IIII?=,,,,,,,,,,,:,,::,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
777777777777777777777I7I777777777~,,,,,,,+77IIIIIIIIIII7II7$$$Z$?I????III???II?,,,,,,,,,,::,::::::::,,:,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
777777777777$77777777777+::::::::::::::,,,,,,,=7IIIII78ZI?II78$7++D7?7O777II??:,,,:,,,::::::::::::::,:,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
$$$$$$$$$$$$$77=:,:::::::::::::::::::::::::::,,7II$,,8ZZI++$8ZZ?+=ZI==IIII,+7:,,,,:::::::::::::::::,:::,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
$$$I~::::::::::::::::::::::::::::::::::::::::::II+,,,OOO7?$DOZII$I$I7=77?,,,,,,:::::::::::::::::::::,,,:,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
::::::::::::::::::::::::::::::::::::::::::::::::::::::+ZZ?,$ZZ$77ZZ$?,,,,,::::::::::::::::::::::::::,::::,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::I$:::::::::::::::::::::::::::::::::::::::::::,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,,,:,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,,,,,,,,,,,,,,,,,,,,,,,,,,,
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,,,,,,,,,,,,,,,,,,,,,,,,,,,
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,,,,,,,,,,,,,,,,,,,,,,,,,
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,,,,,,,,,,,,,,,,,,,,,,
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,,,,,,,,,,,,,,,,,,,,,,
                                                                                                                             GlassGiant.com
 * 
 * 
 */

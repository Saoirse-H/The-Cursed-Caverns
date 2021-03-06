/*
 * Saoirse Houlihan
 * 17340803
 */
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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

//Singeton pattern
public class Controller implements KeyListener, MouseListener { 
	private static boolean KeyAPressed = false;
	private static boolean KeySPressed = false;
	private static boolean KeyDPressed = false;
	private static boolean KeyWPressed = false;
	private static boolean KeyShiftAPressed = false;
	private static boolean KeyShiftSPressed = false;
	private static boolean KeyShiftDPressed = false;
	private static boolean KeyShiftWPressed = false;
	private static boolean LeftClickPressed = false;
	   
	private static final Controller instance = new Controller();
	   
	public Controller() {}
	 
	public static Controller getInstance() {
		return instance;
	}
	   
	@Override
	// Key pressed , will keep triggering 
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) { 
		switch (e.getKeyChar()) {
			case 'a':
				setKeyAPressed(true);
				break; 
			case 's':
				setKeySPressed(true);
				break;
			case 'w':
				setKeyWPressed(true);
				break;
			case 'd':
				setKeyDPressed(true);
				break;
			case 'A':
				setKeyShiftAPressed(true);
				break;
			case 'S':
				setKeyShiftSPressed(true);
				break;
			case 'W':
				setKeyShiftWPressed(true);
				break;
			case 'D':
				setKeyShiftDPressed(true);
				break;
		    default:
		    	//System.out.println("Controller test:  Unknown key pressed");
		        break;
		}
	 // You can implement to keep moving while pressing the key here . 
	}

	@Override
	public void keyReleased(KeyEvent e) { 
		switch (e.getKeyChar()) {
			case 'a':
				setKeyAPressed(false);
				break;  
			case 's':
				setKeySPressed(false);
				break;
			case 'w':
				setKeyWPressed(false);
				break;
			case 'd':
				setKeyDPressed(false);
				break;
			case 'A':
				setKeyShiftAPressed(false);
				break;
			case 'S':
				setKeyShiftSPressed(false);
				break;
			case 'W':
				setKeyShiftWPressed(false);
				break;
			case 'D':
				setKeyShiftDPressed(false);
				break;
		    default:
		    	//System.out.println("Controller test:  Unknown key pressed");
		        break;
		}  
		 //upper case 
	}
	
	@Override
	public void mouseClicked(MouseEvent m) {}

	@Override
	public void mouseEntered(MouseEvent m) {}

	@Override
	public void mouseExited(MouseEvent m) {}

	@Override
	public void mousePressed(MouseEvent m) {
		if(m.getButton() == MouseEvent.BUTTON1) {
			setLeftClickPressed(true);
		} else {
			System.out.println("Controller test: Unknown mouse click pressed");
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent m) {
		if(m.getButton() == MouseEvent.BUTTON1) {
			setLeftClickPressed(false);
		} else {
			System.out.println("Controller test: Unknown mouse click released");
		}
	}


	public boolean isKeyAPressed() {
		return KeyAPressed;
	}


	public void setKeyAPressed(boolean keyAPressed) {
		KeyAPressed = keyAPressed;
	}


	public boolean isKeySPressed() {
		return KeySPressed;
	}


	public void setKeySPressed(boolean keySPressed) {
		KeySPressed = keySPressed;
	}


	public boolean isKeyDPressed() {
		return KeyDPressed;
	}


	public void setKeyDPressed(boolean keyDPressed) {
		KeyDPressed = keyDPressed;
	}


	public boolean isKeyWPressed() {
		return KeyWPressed;
	}


	public void setKeyWPressed(boolean keyWPressed) {
		KeyWPressed = keyWPressed;
	}
	
	public boolean isKeyShiftAPressed() {
		return KeyShiftAPressed;
	}
	
	public void setKeyShiftAPressed(boolean keyShiftAPressed) {
		KeyShiftAPressed = keyShiftAPressed;
	}
	
	public boolean isKeyShiftSPressed() {
		return KeyShiftSPressed;
	}
	
	public void setKeyShiftSPressed(boolean keyShiftSPressed) {
		KeyShiftSPressed = keyShiftSPressed;
	}
	
	public boolean isKeyShiftWPressed() {
		return KeyShiftWPressed;
	}
	
	public void setKeyShiftWPressed(boolean keyShiftWPressed) {
		KeyShiftWPressed = keyShiftWPressed;
	}
	
	public boolean isKeyShiftDPressed() {
		return KeyShiftDPressed;
	}
	
	public void setKeyShiftDPressed(boolean keyShiftDPressed) {
		KeyShiftDPressed = keyShiftDPressed;
	}
	
	public boolean isLeftClickPressed() {
		return LeftClickPressed;
	}
	
	public void setLeftClickPressed(boolean leftClickPressed) {
		LeftClickPressed = leftClickPressed;
	}
}

/*
 * 
 * KEYBOARD :-) . can you add a mouse or a gamepad 

 *@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ @@@@@@@@@@@@@@@

  @@@     @@@@    @@@@    @@@@    @@@@     @@@     @@@     @@@     @@@     @@@  

  @@@     @@@     @@@     @@@@     @@@     @@@     @@@     @@@     @@@     @@@  

  @@@     @@@     @@@     @@@@    @@@@     @@@     @@@     @@@     @@@     @@@  

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

@     @@@     @@@     @@@      @@      @@@     @@@     @@@     @@@     @@@     @

@     @@@   W   @@@     @@@      @@      @@@     @@@     @@@     @@@     @@@     @

@@    @@@@     @@@@    @@@@    @@@@    @@@@     @@@     @@@     @@@     @@@     @

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@N@@@@@@@@@@@@@@@@@@@@@@@@@@@

@@@     @@@      @@      @@      @@      @@@     @@@     @@@     @@@     @@@    

@@@   A   @@@  S     @@  D     @@      @@@     @@@     @@@     @@@     @@@     @@@    

@@@@ @  @@@@@@@@@@@@ @@@@@@@    @@@@@@@@@@@@    @@@@@@@@@@@@     @@@@   @@@@@   

    @@@     @@@@    @@@@    @@@@    $@@@     @@@     @@@     @@@     @@@     @@@

    @@@ $   @@@      @@      @@ /Q   @@ ]M   @@@     @@@     @@@     @@@     @@@

    @@@     @@@      @@      @@      @@      @@@     @@@     @@@     @@@     @@@

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

@       @@@                                                @@@       @@@       @

@       @@@              SPACE KEY       @@@        @@ PQ     

@       @@@                                                @@@        @@        

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 * 
 * 
 * 
 * 
 * 
 */

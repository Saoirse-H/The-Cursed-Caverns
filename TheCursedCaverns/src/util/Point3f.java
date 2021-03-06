/*
 * Saoirse Houlihan
 * 17340803
 */
 package util;
/*
 * Modified by Abraham Campbell on 15/01/2020.
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
//Modified from Graphics 3033J course point class  by Abey Campbell 

public class Point3f {

	private float x;
	private float y;
	private float z;
	
	private int boundary = 992;
	
	// default constructor
	public Point3f() { 
		setX(0.0f);
		setY(0.0f);
		setZ(0.0f);
	}
	
	//initializing constructor
	public Point3f(float x, float y, float z) { 
		this.setX(x);
		this.setY(y);
		this.setZ(z); 
	}

	// sometimes for different algorithms we will need to address the point using positions 0 1 2 
	public float getPostion(int postion)
	{
		switch(postion) {
			case 0: 
				return getX();
			case 1: 
				return getY();
			case 2: 
				return getZ(); 
			default: 
				return Float.NaN;  
		} 
	}
	
	public String toString() {
		return ("(" + getX() +"," + getY() +"," + getZ() +")");
    }

	public Point3f plusVector(Vector3f additonal) { 
		return new Point3f(this.getX()+additonal.getX(), this.getY()+additonal.getY(), this.getZ()+additonal.getZ());
	} 
	
	public Point3f minusVector(Vector3f minus) { 
		return new Point3f(this.getX()-minus.getX(), this.getY()-minus.getY(), this.getZ()-minus.getZ());
	}
	
	public Vector3f minusPoint(Point3f minus) { 
		return new Vector3f(this.getX()-minus.getX(), this.getY()-minus.getY(), this.getZ()-minus.getZ());
	}
	
	public double distance(Point3f point2) {
		// distance = sqrt((x2-x1)^2 + (y2-y1)^2)
		float xDistance = (point2.getX() - this.getX()) * (point2.getX() - this.getX());
		float yDistance = (point2.getY() - this.getY()) * (point2.getY() - this.getY());
		
		double distance = Math.sqrt(xDistance + yDistance);
		return distance;
	}
	
	public Point3f checkVector(Vector3f vector) {
		float newX = checkBoundary(this.getX() + vector.getX());
		float newY = checkBoundary(this.getY() - vector.getY());
		float newZ = checkBoundary(this.getZ() - vector.getZ());
		
		return new Point3f(newX, newY, newZ);
	}
	
	//Use for direct application of a Vector 
	public void applyVector(Vector3f vector) { 
		setX(checkBoundary(this.getX() + vector.getX()));
		setY(checkBoundary(this.getY() - vector.getY()));
		setZ(checkBoundary(this.getZ() - vector.getZ())); 
	}

	private float checkBoundary(float f) {
		if (f < 0) 
			f = 0.0f;
		
		if (f > boundary)
			f = (float) boundary;
		
		return f;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}
	
	// Remember point + point  is not defined so we not write a method for it.  
}

/*................................................................................
................................................................................
................................................................................
................................................................................
................................................................................
................................................................................
................................................................................
................................................................................
................................................................................
....................................=?7777+.....................................
.............................,8MMMMMMMMMMMMMMMMM7...............................
...........................$MMMMMMMMMMMMMMMMMMMMMM7.............................
........................IMMMMMMMMMMMMMMMMMMMMMMMMMMMM?..........................
......................?MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMN........................
.....................MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM$......................
...................ZMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM.....................
..................MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM8....................
.................NMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM...................
................IMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM..................
................MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM$.................
...............=MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMZ................
..............:MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM................
..............7MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM:...............
..............DMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMZ...............
..............MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM8...............
..............MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMN...............
..............NMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMO...............
..............$MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMI...............
..............+MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM=...............
...............8MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM................
................MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM8................
................MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMN,................
................=MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM..................
.................MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMZ..................
..................MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMD...................
...................?MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM.....................
....................8MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM......................
.....................,8MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM,.......................
........................NMMMMMMMMMMMMMMMMMMMMMMMMMMMMN?.........................
..........................NMMMMMMMMMMMMMMMMMMMMMMMMMI...........................
.............................$MMMMMMMMMMMMMMMMMMM?..............................
.................................,I$NMMMMMN$?...................................
................................................................................
................................................................................
................................................................................
.......................................................................*/

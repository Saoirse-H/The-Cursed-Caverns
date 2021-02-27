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
public class Vector3f {

	private float x = 0;
	private float y = 0;
	private float z = 0;
	
	public Vector3f() {  
		setX(0.0f);
		setY(0.0f);
		setZ(0.0f);
	}
	 
	public Vector3f(float x, float y, float z) { 
		this.setX(x);
		this.setY(y);
		this.setZ(z);
	}
	 
	public Vector3f plusVector(Vector3f additonal) { 
		return new Vector3f(this.getX()+additonal.getX(), this.getY()+additonal.getY(), this.getZ()+additonal.getZ());
	} 
	
	public Vector3f minusVector(Vector3f minus) { 
		return new Vector3f(this.getX()-minus.getX(), this.getY()-minus.getY(), this.getZ()-minus.getZ());
	}
	 
	public Point3f plusPoint(Point3f additional) { 
		return new Point3f(this.getX()+additional.getX(), this.getY()+additional.getY(), this.getZ()+additional.getZ());
	} 
	
	public Vector3f byScalar(float scale) {
		return new Vector3f(this.getX()*scale, this.getY()*scale, this.getZ()*scale);
	}
	 
	public Vector3f negateVector() {
		return new Vector3f(-this.getX(), -this.getY(), -this.getZ());
	}
	
	public float length() {
		float length = (float) Math.sqrt(getX()*getX() + getY()*getY() + getZ()*getZ());
	    return length;
	}
	
	public Vector3f normal() {
		float length = this.length();
		return this.byScalar(1.0f/ length); 
	} 
	
	public float dot(Vector3f v) { 
		float dotProduct = this.getX()*v.getX() + this.getY()*v.getY() + this.getZ()*v.getZ();
		return dotProduct;
	}
	 
	public Vector3f cross(Vector3f v) { 
	    float u0 = (this.getY() * v.getZ() - getZ() * v.getY());
	    float u1 = (getZ() * v.getX() - getX() * v.getZ());
	    float u2 = (getX() * v.getY() - getY() * v.getX());
	    return new Vector3f(u0, u1, u2);
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
}
	 
	   

/*

										MMMM                                        
										MMMMMM                                      
 										MM MMMM                                    
 										MMI  MMMM                                  
 										MMM    MMMM                                
 										MMM      MMMM                              
  										MM        MMMMM                           
  										MMM         MMMMM                         
  										MMM           OMMMM                       
   										MM             .MMMM                     
MMMMMMMMMMMMMMM                        MMM              .MMMM                   
MM   IMMMMMMMMMMMMMMMMMMMMMMMM         MMM                 MMMM                 
MM                  ~MMMMMMMMMMMMMMMMMMMMM                   MMMM               
MM                                  OMMMMM                     MMMMM            
MM                                                               MMMMM          
MM                                                                 MMMMM        
MM                                                                   ~MMMM      
MM                                                                     =MMMM    
MM                                                                        MMMM  
MM                                                                       MMMMMM 
MM                                                                     MMMMMMMM 
MM                                                                  :MMMMMMMM   
MM                                                                MMMMMMMMM     
MM                                                              MMMMMMMMM       
MM                             ,MMMMMMMMMM                    MMMMMMMMM         
MM              IMMMMMMMMMMMMMMMMMMMMMMMMM                  MMMMMMMM            
MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM               ZMMMMMMMM              
MMMMMMMMMMMMMMMMMMMMMMMMMMMMM          MM$             MMMMMMMMM                
MMMMMMMMMMMMMM                       MMM            MMMMMMMMM                  
  									MMM          MMMMMMMM                     
  									MM~       IMMMMMMMM                       
  									MM      DMMMMMMMM                         
 								MMM    MMMMMMMMM                           
 								MMD  MMMMMMMM                              
								MMM MMMMMMMM                                
								MMMMMMMMMM                                  
								MMMMMMMM                                    
  								MMMM                                      
  								MM                                        
                             GlassGiant.com */
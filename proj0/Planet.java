import java.lang.Math;

public class Planet {
	/**
	 *   Planet class
	 */
    
    /** Public instance variables */
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    /** Constants defined as public instance variable */
    private double G_Const = 6.67e-11;

    /** First constructor */
    public Planet(double xP, double yP, double xV,
    			  double yV, double m, String img) {
    	xxPos = xP;
    	yyPos = yP;
    	xxVel = xV;
    	yyVel = yV;
    	mass = m;
    	imgFileName = img;
    }

    /** Second constructor */
    public Planet(Planet p) {
    	xxPos = p.xxPos;
    	yyPos = p.yyPos;
    	xxVel = p.xxVel;
    	yyVel = p.yyVel;
    	mass = p.mass;
    	imgFileName = p.imgFileName;
    }

    /** Helper method for distance calculation */
    public double calcDistance(Planet p) {
    	double xDis = p.xxPos - xxPos;
    	double yDis = p.yyPos - yyPos;
    	double rDis = Math.pow(xDis * xDis + yDis * yDis, 0.5);
    	return rDis;
    }
 	
 	/** Helper method for gravitational force calculation */
 	public double calcForceExertedBy(Planet p) {
 		double force = G_Const * mass * p.mass / calcDistance(p) / calcDistance(p);
 		return force;
 	}

 	/** Helper method for grav. force x-component calculation */
 	public double calcForceExertedByX(Planet p) {
 		double xDis = p.xxPos - xxPos;
 		double rDis = calcDistance(p);
 		double forceX = calcForceExertedBy(p) * xDis / rDis;
 		return forceX;
 	}

 	/** Helper method for grav. force y-component calculation */
 	public double calcForceExertedByY(Planet p) {
 		double yDis = p.yyPos - yyPos;
 		double rDis = calcDistance(p);
 		double forceY = calcForceExertedBy(p) * yDis / rDis;
 		return forceY;
 	}

	/** Helper method for net grav. force x-component calculation */
 	public double calcNetForceExertedByX(Planet[] allPlanet) {
 		double netForceX = 0.0;
 		for (Planet p : allPlanet) {
 			if (this.equals(p)) {
 				continue;
 			}
 			netForceX += calcForceExertedByX(p);
 		}
 		return netForceX;
 	}

 	/** Helper method for net grav. force y-component calculation */
 	public double calcNetForceExertedByY(Planet[] p) {
 		double netForceY = 0.0;
 		for (int i = 0; i < p.length; i += 1) {
 			if (this.equals(p[i])) {
 				continue;
 			}
 			netForceY += calcForceExertedByY(p[i]); 
 		}
 		return netForceY;
 	}

 	/** Helper method for updating pos. and vel. instance vars */
 	public void update(double dt, double fX, double fY) {
 		double accX = fX / mass;
 		double accY = fY / mass;
 		xxVel += accX * dt;
 		yyVel += accY * dt;
 		xxPos += xxVel * dt;
 		yyPos += yyVel * dt;
 	}

    /** Draw method for drawing the Planet's image */
    // To compile with StdDraw, using 'javac -encoding utf-8 Planet.java'
    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }

}


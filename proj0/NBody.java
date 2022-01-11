

public class NBody {

	/** method for read radius (double) */
	public static double readRadius(String fileName) {
		In input = new In(fileName);
		int num_planet = input.readInt();
		double radius  = input.readDouble();
		return radius;
	}

	/** method for read Planet objects */
	public static Planet[] readPlanets(String fileName) {
		In input = new In(fileName);
		int num_planet = input.readInt();
		double radius = input.readDouble();
		/** initialize Planet array*/
		Planet[] allPlanets = new Planet[num_planet];
		for(int i = 0; i < num_planet; i += 1) {
			double xxPos = input.readDouble();
			double yyPos = input.readDouble();
			double xxVel = input.readDouble();
			double yyVel = input.readDouble();
			double mass = input.readDouble();
			String imgFileName = input.readString();
			allPlanets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
		}
		return allPlanets;
	}


	/** main */
	public static void main(String[] args) {
		StdDraw.enableDoubleBuffering();

		/** read all parameters */
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		Planet[] allPlanets = readPlanets(filename);
		double radius = readRadius(filename);

		/** draw the background */
		StdDraw.setXscale(-radius, radius);
		StdDraw.setYscale(-radius, radius);
		String imgBackgnd = "images/starfield.jpg";
		StdDraw.picture(0, 0, imgBackgnd);

		// draw the stars
		for(Planet p : allPlanets) {
			p.draw();
		}
		StdDraw.show();

		// draw animation!


		double time = 0.0;

		while (time < T) {
			double[] xForces = new double[allPlanets.length];
			double[] yForces = new double[allPlanets.length];
			for(int i = 0; i < allPlanets.length; i += 1) {
				xForces[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
				yForces[i] = allPlanets[i].calcNetForceExertedByY(allPlanets);
				
			}
			for(int i = 0; i < allPlanets.length; i += 1) {
				allPlanets[i].update(dt, xForces[i], yForces[i]);
			}

			// draw
			StdDraw.picture(0, 0, imgBackgnd);
			for(Planet p : allPlanets) {
				p.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			time += dt;
		}

		// print out the final state
		StdOut.printf("%d\n", allPlanets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < allPlanets.length; i++) {
	    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
	                  allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel,
	                  allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);
		}

	}
}

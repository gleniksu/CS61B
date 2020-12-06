public class NBody{
  /*
   * Return a double corresponding to the radius of the universe in that file
   */
   public static double readRadius(String fileName){
     In in = new In(fileName);
     in.readInt();
     return in.readDouble();
   }

   public static Body[] readBodies(String fileName){
     In in = new In(fileName);
     Body[] planet = new Body[5];
     in.readInt();
     in.readDouble();
     int i = 0;
     while(i < 5){
       planet[i] = new Body(
       in.readDouble(),
       in.readDouble(),
       in.readDouble(),
       in.readDouble(),
       in.readDouble(),
       in.readString()
       );
       i++;
     }
     return planet;
   }

   public static void main(String[] args){
     double T = Double.parseDouble(args[0]);
     double dt = Double.parseDouble(args[1]);
     String filename = args[2];
     double radius = readRadius(filename);
     Body[] planet = readBodies(filename);

     /*
      * Draw the background.
      */
     StdDraw.setScale(-radius, radius);
     StdDraw.clear();
     StdDraw.picture(0, 0, "images/starfield.jpg");
     /**
      * Draw the planet.
      */
     for(Body each: planet){
       each.draw();
     }
     /**
      * Animation
      */
     StdDraw.enableDoubleBuffering();

     for (double t = 0; t <= T;){
       double[] xForces = new double[planet.length];
       double[] yForces = new double[planet.length];
       /**
        * Calculate the net forces for every planet
        */
        for(int i = 0; i < planet.length; i++){
          xForces[i] = planet[i].calcNetForceExertedByX(planet);
          yForces[i] = planet[i].calcNetForceExertedByY(planet);
        }
        /**
         * Update positions, velocity, and accleration.
         */
         for(int i = 0; i < planet.length; i++){
           planet[i].update(dt, xForces[i], yForces[i]);
         }
         /**
          * Draw the background image.
          */
          StdDraw.picture(0, 0, "images/starfield.jpg");

          /**
           * Draw all of the Bodys.
           */
           for(Body each: planet){
             each.draw();
           }
           /**
            * Show the offscreen buffer.
            */
            StdDraw.show();

           /**
            * Pause the animation for 10 miliseconds.
            */
            StdDraw.pause(10000000);

           /**
            * Increase time by dt.
            */
             t += dt;

     }
     StdOut.printf("%d\n", planet.length);
     StdOut.printf("%.2e\n", radius);
     for (int i = 0; i < planet.length; i++) {
     StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planet[i].xxPos, planet[i].yyPos, planet[i].xxVel,
                  planet[i].yyVel, planet[i].mass, planet[i].imgFileName);
}
     }
}

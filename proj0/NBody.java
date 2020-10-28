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

   public void main(String[] args){
     double T = Double.parseDouble(args[0]);
     double dt = Double.parseDouble(args[1]);
     String filename = args[2];
     double radius = readRadius(filename);
     Body[] planet = readBodies(filename);
   }
}

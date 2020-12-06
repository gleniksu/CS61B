public class Body{
  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;
  static final double G = 6.67e-11; //A final variable can be explicitly initialized only once. A reference variable declared final can be never be reassigned to refer to an different object.
/*
 * Class Body constructor
 */
  public Body(double xP, double yP, double xV, double yV, double m, String img){
    xxPos = xP;
    yyPos = yP;
    xxVel = xV;
    yyVel = yV;
    mass = m;
    imgFileName = img;
  }

  public Body(Body b){
    xxPos = b.xxPos;
    yyPos = b.yyPos;
    xxVel = b.xxVel;
    yyVel = b.yyVel;
    mass = b.mass;
    imgFileName = b.imgFileName;
  }

  /*
   * Return the distance between two Bodys.
   */
  public double calcDistance(Body b){
    double xxDis = b.xxPos - xxPos;
    double yyDis = b.yyPos - yyPos;
    return Math.sqrt(Math.pow(xxDis, 2) + Math.pow(yyDis, 2));
  }

  /*
   * Return the force exerted on this body by given body.
   */
   public double calcForceExertedBy(Body b){
     return (G * mass * b.mass) / Math.pow(calcDistance(b), 2);
   }

   /*
    * Return the force exerted in X and Y direction.
    */
   public double calcForceExertedByX(Body b){
     double toDis = calcDistance(b);
     double dx = b.xxPos - xxPos;
     return (dx / toDis) * calcForceExertedBy(b);
   }

   public double calcForceExertedByY(Body b){
     double toDis = calcDistance(b);
     double dy = b.yyPos - yyPos;
     return (dy / toDis) * calcForceExertedBy(b);
   }

   /*
    * Return the net X and net Y force exerted by all bodies in that array.
    */
    public double calcNetForceExertedByX(Body[] allbodies){
        double NetForceX = 0;
        for(Body each_Body: allbodies){
            if(!this.equals(each_Body)){
              NetForceX += calcForceExertedByX(each_Body);
            }
        }
        return NetForceX;
    }

    public double calcNetForceExertedByY(Body[] allbodies){
        double NetForceY = 0;
        for(Body each_Body: allbodies){
          if(!this.equals(each_Body)){
            NetForceY += calcForceExertedByY(each_Body);
          }
        }
        return NetForceY;
    }

    public void update(double dt, double Fx, double Fy){
      double xxAcc, yyAcc;
      xxAcc = Fx / mass;
      yyAcc = Fy / mass;
      xxVel = xxVel + dt * xxAcc;
      yyVel = yyVel + dt * yyAcc;
      xxPos = xxPos + dt * xxVel;
      yyPos = yyPos + dt * yyVel;
    }

    /*
    * Draw the Body's image at the Body's position.
    */
    public void draw(){
      StdDraw.picture(xxPos, yyPos, imgFileName);
    }

}

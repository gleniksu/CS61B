public class Body{
  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;
  static final double G = 6.67e-11;
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
}

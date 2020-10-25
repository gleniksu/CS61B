public class Body{
  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;
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

}

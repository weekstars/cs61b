public class Planet {
    static final double G = 6.67e-11;
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;
    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }
    public double calcDistance(Planet p){
        return Math.sqrt((xxPos - p.xxPos) * (xxPos - p.xxPos) + (yyPos - p.yyPos) * (yyPos - p.yyPos));
    }
    public double calcForceExertedBy(Planet p){
        return (G * mass * p.mass)/(calcDistance(p)*calcDistance(p));
    }
    public double calcForceExertedByX(Planet p){
        double dx = p.xxPos - xxPos;
        double r = calcDistance(p);
        return calcForceExertedBy(p) * dx / r; 
    }
    public double calcForceExertedByY(Planet p){
        double dy = p.yyPos - yyPos;
        double r = calcDistance(p);
        return calcForceExertedBy(p) * dy / r; 
    }
    public double calcNetForceExertedByX(Planet[] allPlanets){
        double NetFX = 0;
        for (int i = 0; i < allPlanets.length; i++) {
            if (this.equals(allPlanets[i])) {
                continue;
            }
            NetFX = NetFX + calcForceExertedByX(allPlanets[i]);
        }
        return NetFX;
    }
    public double calcNetForceExertedByY(Planet[] allPlanets){
        double NetFY = 0;
        for (int i = 0; i < allPlanets.length; i++) {
            if (this.equals(allPlanets[i])) {
                continue;
            }
            NetFY = NetFY + calcForceExertedByY(allPlanets[i]);
        }
        return NetFY;
    }
    public void update(double dt, double FX, double FY){
        double aX = FX / mass;
        double aY = FY / mass;
        xxVel = xxVel + aX * dt;
        yyVel = yyVel + aY * dt;
        xxPos = xxPos + xxVel * dt;
        yyPos = yyPos + yyVel * dt;
    }
}

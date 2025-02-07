public class NBody {
    public static double readRadius(String FileName){
        In in = new In(FileName);
        in.readInt();
        double Radius = in.readDouble();
        return Radius;
    }
    public static Planet[] readPlanets(String FileName){
        In in = new In(FileName);
        int Num = in.readInt();
        Planet[] Planets = new Planet[Num];
        in.readDouble();
        for (int i = 0; i < Planets.length; i++) {
            double x = in.readDouble();
            double y = in.readDouble();
            double vx = in.readDouble();
            double vy = in.readDouble();
            double m = in.readDouble();
            String imagfilename = in.readString(); 
            Planets[i] = new Planet(x, y, vx, vy, m, imagfilename);
        }
        return Planets;
    }
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double Radius = readRadius(filename);
        Planet[] Planets = readPlanets(filename);
        StdDraw.enableDoubleBuffering();
        for (int t = 0; t <= T; t += dt){
            double[] xForces = new double[Planets.length];
            double[] yForces = new double[Planets.length];
            for (int i = 0; i < Planets.length; i++) {
                    xForces[i] = Planets[i].calcNetForceExertedByX(Planets);
                    yForces[i] = Planets[i].calcNetForceExertedByY(Planets);
                    Planets[i].update(dt, xForces[i], yForces[i]);
            } 
            StdDraw.setScale(-Radius, Radius);
            StdDraw.clear();
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (int i = 0; i < Planets.length; i++) {
                Planets[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);	
        }
        StdOut.printf("%d\n", Planets.length);
        StdOut.printf("%.2e\n", Radius);
        for (int i = 0; i < Planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                        Planets[i].xxPos, Planets[i].yyPos, Planets[i].xxVel,
                        Planets[i].yyVel, Planets[i].mass, Planets[i].imgFileName);   
                    }
    }
}

package byog.Core;

import byog.TileEngine.TETile;

public class TrySeed {
    public static void main(String[] args) {
        Game game = new Game();
        TETile[][] world = game.playWithInputString("n5197880843569031643s");
        System.out.println(TETile.toString(world));
        TETile[][] world2 = game.playWithInputString("n5197880843569031643s");
        System.out.println(TETile.toString(world2));
        TETile[][] world3 = game.playWithInputString("n51970843569031643s");
        System.out.println(TETile.toString(world3));
    }
}

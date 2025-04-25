package byog.Core;

import byog.TileEngine.TETile;

public class TrySeed {
    public static void main(String[] args) {
        Game game = new Game();
        TETile[][] world = game.playWithInputString("N1234S");
        System.out.println(TETile.toString(world));
    }
}

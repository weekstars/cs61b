package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class MapVisualTest {


    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        int WIDTH = WorldGenerator.WIDTH;
        int HEIGHT = WorldGenerator.HEIGHT;
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        // Position p = new Position(10, 10);
        // Room r = new Room(8, 8, p);
        // WorldGenerator.DrawRoom(world, r);


        // Position pH = new Position(20, 20);
        // Hallway H = new Hallway(pH, 1, 5);
        // WorldGenerator.DrawHallway(world, H);
        WorldGenerator wg = new WorldGenerator(1234);
        wg.drawtest(world);

        ter.renderFrame(world);
    }
}

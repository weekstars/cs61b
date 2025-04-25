package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;



/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    private static final int WIDTH = 60;
    private static final int HEIGHT = 60;
    private static final long SEED = 287313;
    private static final Random RANDOM = new Random(SEED);

    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(5);
        switch (tileNum) {
            case 0: return Tileset.GRASS;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.WATER;
            case 3: return Tileset.MOUNTAIN;
            case 4: return Tileset.TREE;
            default: return Tileset.NOTHING;
        }
    }

    public static void addHexagon(TETile[][] world, Position p, int s, TETile t){
        for (int i = 0; i <= s - 1; i++) {
            for (int j = p.x - i; j <= p.x + s + i - 1; j++){
                world[j][p.y + i] = t;
            }
        }
        
        for (int i = 0; i <= s - 1; i++) {
            for (int j = p.x - i; j <= p.x + s + i - 1; j++){
                world[j][p.y + (2 * s - i - 1)] = t;
            }
        }
    }


    public static Position topRightNeighbor(Position p, int s){
        Position trN = new Position(p.x + s + s - 1, p.y + s);
        return trN;
    }

    public static Position bottomRightNeighbor(Position p, int s){
        Position brN = new Position(p.x + s + s - 1, p.y - s);
        return brN;
    }


    public static void drawRandomVerticalHexes(TETile[][] world, Position p, int s, int N){
        for (int i = 0; i < N; i++) {
            Position pi = new Position(p.x, p.y + 2 * i * s);
            addHexagon(world, pi, s, randomTile());
        }    
    }



    public static void main(String[] args) {
         // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        //
        Position p = new Position(20, 20);
        drawRandomVerticalHexes(world, p, 3, 3);
        p = bottomRightNeighbor(p, 3);
        drawRandomVerticalHexes(world, p, 3, 4);
        p = bottomRightNeighbor(p, 3);
        drawRandomVerticalHexes(world, p, 3, 5);
        p = topRightNeighbor(p, 3);
        drawRandomVerticalHexes(world, p, 3, 4);
        p = topRightNeighbor(p, 3);
        drawRandomVerticalHexes(world, p, 3, 3);

        //
        ter.renderFrame(world);

    }


}

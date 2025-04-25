package byog.Core;

import java.util.Random;

import byog.Core.RandomUtils;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;


public class WorldGenerator {
    static final int WIDTH = 80;
    static final int HEIGHT = 30;
    static int iter = 30;
    static int MaxRoomSize = 7;
    static int MaxHallwayLength = 8;
    static Position intialPos = new Position(30, 15);
    static Room[] rooms = new Room[iter + 1];
    static Hallway[] Hallways = new Hallway[iter];
    static long SEED = 32;

    public WorldGenerator(long s){
        SEED = s;
    }


    private static final Random RANDOM = new Random(SEED);

    public static boolean isRoomConflict(Room r){
        if (r.p.y + r.h - 1 > HEIGHT - 1 || r.p.y < 0 || r.p.x + r.w - 1 > WIDTH - 1 || r.p.x < 0 ){
            return true;
        }
        return false;
    }

    public static boolean isHallwayConflict(Hallway H){
        if (H.direction==0){
            if(H.p.x + H.length - 1 > WIDTH - 1 - 2 ){
                return true;
            }
            return false;
        }
        if (H.direction==1){
            if(H.p.y - H.length + 1 < 2 ){
                return true;
            }
            return false;
        }
        if (H.direction==2){
            if(H.p.x - H.length + 1 < 2 ){
                return true;
            }
            return false;
        }
        else{
            if(H.p.y + H.length - 1 > HEIGHT - 1 - 2 ){
                return true;
            }
            return false;
        }
    }

    public static void DrawRoom(TETile[][] world, Room r){
        for (int x = r.p.x + 1; x < r.p.x + r.w - 1; x += 1) {
            for (int y = r.p.y + 1; y < r.p.y + r.h - 1; y += 1) {
                world[x][y] = Tileset.FLOOR;
            }
        }

        for (int x = r.p.x; x < r.p.x + r.w ; x += 1){
            if (world[x][r.p.y] != Tileset.FLOOR){
                world[x][r.p.y] = Tileset.WALL;
            }
            if (world[x][r.p.y + r.h - 1] != Tileset.FLOOR){
                world[x][r.p.y + r.h - 1] = Tileset.WALL;
            }
           
        }
        for (int y = r.p.y; y < r.p.y + r.h ; y += 1){
            if (world[r.p.x][y] != Tileset.FLOOR){
                world[r.p.x][y] = Tileset.WALL;
            }
            if (world[r.p.x + r.w - 1][y] != Tileset.FLOOR){
                world[r.p.x + r.w - 1][y] = Tileset.WALL;
            }
           
        }
    }
    public static void DrawHallway(TETile[][] world, Hallway H){
        if (H.direction == 0){
            for (int x = H.p.x; x < H.p.x + H.length; x += 1){
                world[x][H.p.y] = Tileset.FLOOR;
                if (world[x][H.p.y + 1] != Tileset.FLOOR){
                    world[x][H.p.y + 1] = Tileset.WALL;
                }
                if (world[x][H.p.y - 1] != Tileset.FLOOR){
                    world[x][H.p.y - 1] = Tileset.WALL;
                }
                
            }
        }
        if (H.direction == 1){
            for (int y = H.p.y; y > H.p.y - H.length; y -= 1){
                world[H.p.x][y] = Tileset.FLOOR;
                if (world[H.p.x + 1][y] != Tileset.FLOOR){
                    world[H.p.x + 1][y] = Tileset.WALL;
                }
                if (world[H.p.x - 1][y] != Tileset.FLOOR){
                    world[H.p.x - 1][y] = Tileset.WALL;
                }
                
            }
        }
        if (H.direction == 2){
            for (int x = H.p.x; x > H.p.x - H.length; x -= 1){
                world[x][H.p.y] = Tileset.FLOOR;
                if (world[x][H.p.y + 1] != Tileset.FLOOR){
                    world[x][H.p.y + 1] = Tileset.WALL;
                }
                if (world[x][H.p.y - 1] != Tileset.FLOOR){
                    world[x][H.p.y - 1] = Tileset.WALL;
                }
                
            }
        }
        if (H.direction == 3){
            for (int y = H.p.y; y < H.p.y + H.length; y += 1){
                world[H.p.x][y] = Tileset.FLOOR;
                if (world[H.p.x + 1][y] != Tileset.FLOOR){
                    world[H.p.x + 1][y] = Tileset.WALL;
                }
                if (world[H.p.x - 1][y] != Tileset.FLOOR){
                    world[H.p.x - 1][y] = Tileset.WALL;
                }
                
            }
        }
    }
    public static Hallway NextHallway(Room r){
        int direction = RandomUtils.uniform(RANDOM, 4);
        int length = RandomUtils.uniform(RANDOM,2, MaxHallwayLength);
        Position p = null;
        if (direction == 0){
            int y = RandomUtils.uniform(RANDOM, r.p.y + 1, r.p.y + r.h - 1);
            p = new Position(r.p.x + r.w - 1, y);
        }
        if (direction == 1){
            int x = RandomUtils.uniform(RANDOM, r.p.x + 1, r.p.x + r.w - 1);
            p = new Position(x, r.p.y);
        }
        if (direction == 2){
            int y = RandomUtils.uniform(RANDOM, r.p.y + 1, r.p.y + r.h - 1);
            p = new Position(r.p.x, y);
        }
        if (direction == 3){
            int x = RandomUtils.uniform(RANDOM, r.p.x + 1, r.p.x + r.w - 1);
            p = new Position(x, r.p.y + r.h - 1);
        }
        Hallway nextHallway = new Hallway(p, direction, length);
        return nextHallway;
    }


    public static Room NextRoom(Hallway H){
        Position EndOfHallway = null;
        int nextw = RandomUtils.uniform(RANDOM, 3, MaxRoomSize + 1);
        int nexth = RandomUtils.uniform(RANDOM, 3, MaxRoomSize + 1);
        Position nextposition = null;
        if (H.direction == 0){
            EndOfHallway = new Position(H.p.x + H.length - 1, H.p.y);
            if (nexth == 3){
                nextposition = new Position(EndOfHallway.x, EndOfHallway.y - 1);
            } 
            else{
                nextposition = new Position(EndOfHallway.x, RandomUtils.uniform(RANDOM, EndOfHallway.y - nexth + 2 , EndOfHallway.y - 1));
            }
            

        }
        if (H.direction == 1){
            EndOfHallway = new Position(H.p.x , H.p.y - H.length + 1);
            if (nextw == 3){
                nextposition = new Position(EndOfHallway.x - 1, EndOfHallway.y - nexth + 1);
            } 
            else{
                nextposition = new Position(RandomUtils.uniform(RANDOM, EndOfHallway.x - nextw + 2 , EndOfHallway.x - 1), EndOfHallway.y - nexth + 1);
            }
        }
        if (H.direction == 2){
            EndOfHallway = new Position(H.p.x - H.length + 1, H.p.y);
            if (nexth == 3){
                nextposition = new Position(EndOfHallway.x - nextw + 1, EndOfHallway.y - 1);
            } 
            else{
                nextposition = new Position(EndOfHallway.x - nextw + 1, RandomUtils.uniform(RANDOM, EndOfHallway.y - nexth + 2 , EndOfHallway.y - 1));
            }
        }
        if (H.direction == 3){
            EndOfHallway = new Position(H.p.x, H.p.y + H.length - 1);
            if (nextw == 3){
                nextposition = new Position(EndOfHallway.x - 1, EndOfHallway.y);
            } 
            else{
                nextposition = new Position(RandomUtils.uniform(RANDOM, EndOfHallway.x - nextw + 2 , EndOfHallway.x - 1), EndOfHallway.y);
            }
        }
        Room nextRoom = new Room(nextw, nexth, nextposition);
        return nextRoom;
    }





    public void drawtest(TETile[][] world){
        int w = RandomUtils.uniform(RANDOM, 3, MaxRoomSize + 1);
        int h = RandomUtils.uniform(RANDOM, 3, MaxRoomSize + 1);
        Room room0 = new Room(w, h, intialPos);
        rooms[0] = room0;

        for (int i = 0; i < iter; i += 1) {
            Hallway hallway = NextHallway(rooms[i]);
            while (isHallwayConflict(hallway)){
                hallway = NextHallway(rooms[i]);
            }
            Room room = NextRoom(hallway);

            
            while (isRoomConflict(room)){
                room = NextRoom(hallway);
            }
            Hallways[i] = hallway;
            rooms[i + 1] = room;


        }

        for (int i = 0; i< rooms.length; i += 1){
            DrawRoom(world, rooms[i]);
           
        }
        for (int i = 0; i< Hallways.length; i += 1){
            DrawHallway(world, Hallways[i]);
           
        }
        // DrawHallway(world, Hallways[0]);
        // DrawRoom(world, rooms[0]);
        // DrawRoom(world, rooms[1]);
        // DrawHallway(world, Hallways[0]);
        // DrawHallway(world, Hallways[1]);
        // DrawRoom(world, rooms[2]);
       
    }

    

   
}

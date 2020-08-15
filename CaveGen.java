//import sun.lwawt.macosx.CViewEmbeddedFrame;
import java.util.Random;
public class CaveGen {
    static Random rand = new Random();
    public static boolean[][] Generate(int[] resolution, int deathLimit, int birthLimit, int NumSteps, double spawnRate){
        //boolean[][] out = new boolean[resolution[0]][resolution[1]];
        boolean[][] temp = new boolean[resolution[0]][resolution[1]];
        for (int x = 0; x < resolution[0]; x++){// Generation of Random Cave
            for (int i = 0; i < resolution[1]; i++){
                if(rand.nextFloat() < spawnRate){
                    temp[x][i] = true;
                }else{
                    temp[x][i] = false;
                }
            }
        }
        for (int a = 0; a <= NumSteps; a++){// Outer loop for Applying rules
            temp = doSimStep(temp, birthLimit, deathLimit, resolution);
        }
        return temp;
    }
    public static int countAliveNeighbors(boolean[][] map, int x, int y){
        int count = 0;
        for(int i=-1; i<2; i++){
            for(int j=-1; j<2; j++){
                int neighbour_x = x+i;
                int neighbour_y = y+j;
                //If we're looking at the middle point
                if(i == 0 && j == 0){
                    //Do nothing, we don't want to add ourselves in!
                }
                //In case the index we're looking at it off the edge of the map
                else if(neighbour_x < 0 || neighbour_y < 0 || neighbour_x >= map.length || neighbour_y >= map[0].length){
                    count = count + 1;
                }
                //Otherwise, a normal check of the neighbour
                else if(map[neighbour_x][neighbour_y]){
                    count = count + 1;
                }
            }
        }
        return count;
    }
    public static boolean[][] doSimStep(boolean[][] oldMap, int birthLimit, int deathLimit, int[] size){
        boolean[][] newMap = new boolean[size[0]][size[1]];
        //Loop over each row and column of the map
        for(int x=0; x<oldMap.length; x++){
            for(int y=0; y<oldMap[0].length; y++){
                int nbs = countAliveNeighbors(oldMap, x, y);
                //The new value is based on our simulation rules
                //First, if a cell is alive but has too few neighbours, kill it.
                if(oldMap[x][y]){
                    if(nbs < deathLimit){
                        newMap[x][y] = false;
                    }
                    else{
                        newMap[x][y] = true;
                    }
                } //Otherwise, if the cell is dead now, check if it has the right number of neighbours to be 'born'
                else{
                    if(nbs > birthLimit){
                        newMap[x][y] = true;
                    }
                    else{
                        newMap[x][y] = false;
                    }
                }
            }
        }
        return newMap;
    }
}
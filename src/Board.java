import java.math.MathContext;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;


/**
* The Board class contains a 2D grid of Patches.
* Patches on the Board affect each other's temperature, which also depends on
 * the daisy on each patch.
* After each 'tick', the Board would update the global temperature and
* */
public class Board {
    private float globalTemp;
    private final int width;
    private final int height;
    private Patch[][] patches;
    private int tickCount;

    /**
     * The Board has a width and a height
     * The initial global temperature is 0
     * */
    public Board(int width, int height) {
        this.globalTemp = 0;
        this.width = width;
        this.height = height;
        this.patches = new Patch[height][width];
        this.tickCount = 0;
    }


    /**
     * initPatches will randomly put daisies on patches,
     * based on the initial percentage of white, black and grey daisies
     * then updates local and global temperatures.
     * */
    public void initPatches(){
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++){
                double prob = Math.random();
                //if the prob is smaller than initial percentage of white
                // then seed white daisy on the patch
                if(prob < Params.INI_PER_WHITE){
                    Daisy whiteDaisy = new Daisy(Params.DAISY_COLOUR.white);
                    patches[j][i] = new Patch(whiteDaisy);
                }
                // if the prob is larger than (1-percentage of black) seed black
                else if (prob > (1 - Params.INI_PER_BLACK)){
                    Daisy blackDaisy = new Daisy(Params.DAISY_COLOUR.black);
                    patches[j][i] = new Patch(blackDaisy);
                }
                // (extended)
                // if the prob is within the range of percentage of white and
                // the white + grey, then seed grey
                else if (prob > Params.INI_PER_WHITE
                        && prob < (Params.INI_PER_WHITE + Params.INI_PER_GREY)){
                    Daisy geryDaisy = new Daisy(Params.DAISY_COLOUR.grey);
                    patches[j][i] = new Patch(geryDaisy);
                }
                // else the patch will contain no daisy when init
                else {
                    patches[j][i] = new Patch();
                }
            }
        }

        // updates all temperatures after first init
        this.updateBoard();
    }



    /**
     * updateBoard() execute one tick operations on the board:
     * 1. update each patch's temperature
     * 2. each patch diffuse heat update temperature again
     * 3. check the daisy survivability on patches(seeding)
     * 4. update the global temperature
     * 5. increase tickCount;
     * **/
    public void updateBoard() {

        // 1. update local temp
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++){
                this.patches[j][i].updateTemp();
            }
        }

        // 2. diffuse
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++){
                Patch curPatch = this.patches[j][i];
                curPatch.diffuse(this.getNeighbours(i,j));
            }
        }

        // 3. update survivability
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++){
                Patch curPatch = this.patches[j][i];
                curPatch.checkSurvivability(this.getNeighbours(i,j));
            }
        }

        // 4. update global temp
        this.updateGlobalTemp();

        // 5. increase count
        this.tickCount++;
    }

    /**
     * This method sum up the temperature of all patches
     * and divide sum by total number of patches for average temperature
     * to update the new global temperature
     * (should be called after all patch update temperature)
     * **/
    public void updateGlobalTemp() {
        float sum = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                sum += patches[i][j].temp;
            }
        }
        this.globalTemp = sum / (height * width);
        System.out.println("the global temp at tick " + tickCount + " is " +
                this.globalTemp);
    }

    /**
     * getNeighbours returns a list of all patches that are next to
     * the given coordinate
     * **/
    public List<Patch> getNeighbours(int x, int y) {

        ArrayList<Patch> neighbours = new ArrayList<Patch>();

        // find possible neighbours for given coordinate
        for (int i = y - 1; i <= y + 1; i++) {
            for (int j = x - 1; j <= x + 1; j++) {

                // check if patch exist
                if (i < height && i >= 0 && j < width && j >= 0) {

                    // avoid duplicate of given coordinate
                    if (i != y || j != x) {

                        neighbours.add(patches[j][i]);
                    }
                }
            }
        }

        return neighbours;
    }

    /**
     * toListOfString returns a list of strings that contains information of
     * Patches of the Board, and could then be converted to csv file
     * the information includes: population of daisies, and the global
     * temperature
     * */
    public StringBuilder getData(StringBuilder sb) {

        // calculating data
        int whitePopularity = 0;
        int blackPopularity = 0;
        int greyPopularity = 0;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++){
                Daisy currDaisy = this.patches[j][i].daisy;
                if (currDaisy == null) {
                    continue;
                }
                if (currDaisy.colour == Params.DAISY_COLOUR.white) {
                    whitePopularity++;
                }
                if (currDaisy.colour == Params.DAISY_COLOUR.grey) {
                    greyPopularity++;
                }
                if (currDaisy.colour == Params.DAISY_COLOUR.black) {
                    blackPopularity++;
                }
            }
        }

        // build the data lines to be output to csv file
        sb.append(tickCount)
                .append(",")
                .append(Integer.toString(whitePopularity))
                .append(",")
                .append(Integer.toString(greyPopularity))
                .append(",")
                .append(Integer.toString(blackPopularity))
                .append(",")
                .append(globalTemp)
                .append("\n");

        return sb;
    }

}

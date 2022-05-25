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
    private final Patch[][] patches;

    public Board(float globalTemp, int width, int height, Patch[][] patches) {
        this.globalTemp = globalTemp;
        this.width = width;
        this.height = height;
        this.patches = patches;
    }



    /**
     * one tick operations on the board:
     * 1. update each patch's temperature
     * 2. each patch diffuse heat update temperature again
     * 3. check the daisy survivability on patches(seeding)
     * 4. update the global temperature
     *
     * **/
    public void updateBoard() {

        // 1. update temp
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++){
                this.patches[i][j].updateTemp();
            }
        }

        // 2. diffuse
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++){
                Patch curPatch = this.patches[i][j];
                curPatch.receiveDiffusion(this.getNeighbours(i,j));
            }
        }

        // 3. update survivability
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++){
                Patch curPatch = this.patches[i][j];
                curPatch.checkSurvivability(this.getNeighbours(i,j));
            }
        }

        // 4. update global temp
        this.updateGlobalTemp();

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

                        neighbours.add(patches[i][j]);
                    }
                }
            }
        }

        return neighbours;
    }
}

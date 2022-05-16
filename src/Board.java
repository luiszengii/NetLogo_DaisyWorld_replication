import java.util.List;
import java.util.ArrayList;

public class Board {
    private float global_temp;
    private int width;
    private int height;
    private Patch[][] patches;

    public Board(float global_temp, int width, int height, Patch[][] patches) {
        this.global_temp = global_temp;
        this.width = width;
        this.height = height;
        this.patches = patches;
    }

    public Board(float global_temp, Patch[][] patches) {
        this.global_temp = global_temp;
        this.patches = patches;
    }

    public void update_global_temp() {

        // sum up the temperature of all patches
        float sum = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                sum += patches[i][j].temp;
            }
        }

        // divide sum by total number of patches for average temperature
        this.global_temp = sum / (height * width);
    }

    public List<Patch> get_neighbours(int x, int y) {

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

    public float getGlobal_temp() {
        return global_temp;
    }

    public void setGlobal_temp(float global_temp) {
        this.global_temp = global_temp;
    }
}

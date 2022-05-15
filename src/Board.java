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

    public void update_global_temp(){
        //TODO: calculate based on daisies in all patches
    }

    public List<Patch> get_neighbours(int x, int y){
        //TODO: return all neighbors around the given coors
        //consider the edge patches
        return new ArrayList<Patch>();
    }

    public float getGlobal_temp() {
        return global_temp;
    }

    public void setGlobal_temp(float global_temp) {
        this.global_temp = global_temp;
    }

    public Patch[][] getPatches() {
        return patches;
    }

    public void setPatches(Patch[][] patches) {
        this.patches = patches;
    }
}

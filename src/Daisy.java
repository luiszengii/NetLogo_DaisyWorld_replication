/**
 * white daisies have a high albedo(reflect heat),
 * and black has low(absorb heat)
 * if the temperature is too high or low daisy dies
 * the daisy die of old age as well
* */

public class Daisy {
    protected Params.DAISY_COLOUR colour;
    protected int age;

    public Daisy(Params.DAISY_COLOUR colour) {
        this.colour = colour;
        this.age = 0; // random initial age
    }

    public boolean isDead(){
        return this.age >= Params.MAX_AGE;
    }

    public double getAlbedo() {
        switch (this.colour) {
            case black:
                return Params.ALBEDO_BLACK;
            case white:
                return Params.ALBEDO_WHITE;
            case grey:
                return Params.ALBEDO_GREY;
            default:
                System.err.println("Daisy color is invalid.");
                return -1;
        }
    }
}

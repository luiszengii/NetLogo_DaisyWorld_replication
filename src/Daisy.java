/**
 * white daisies have a high albedo(reflect heat),
 * and black has low(absorb heat)
 * if the temperature is too high or low daisy dies
 * the daisy die of old age as well
* */

public class Daisy {
    protected Params.DAISY_COLOUR colour;
    protected int age;

    /**
     * The daisy has a colour and an age
     * When initializing the board all daisies are of random age.
     * Age will increase after each tick.
     * */
    public Daisy(Params.DAISY_COLOUR colour) {
        this.colour = colour;
        this.age = (int) (Math.random() * Params.MAX_AGE); // random initial age
    }

    /**
     * when seeding a new daisy, the age should be 0
     * */
    public Daisy(Params.DAISY_COLOUR colour, int age) {
        this.colour = colour;
        this.age = age;
    }

    /**
     * isDead() returns if the daisy has reached its life expectancy
     * */
    public boolean isDead(){
        return this.age >= Params.MAX_AGE;
    }

    /**
     * getAlbedo() returns the albedo of the daisy
     * differs by its color
     * */
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

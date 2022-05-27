/**
* Parameters that influence the behaviour of the system.
* */

public class Params {
    public enum DAISY_COLOUR { black, white, grey }

    // the initial percentage of white and black daisies
    // (sum should not be bigger than 1)
    public final static double INI_PER_WHITE = 0.3;
    public final static double INI_PER_BLACK = 0.3;
    public final static double INI_PER_GREY = 0;

    // the size of the board
    public final static int BOARD_WIDTH = 35;
    public final static int BOARD_HEIGHT = 35;


    // the life expectancy of a daisy
    public final static int MAX_AGE = 25;

    // the albedo of white and black daisies
    public final static double ALBEDO_WHITE = 0.75;
    public final static double ALBEDO_BLACK = 0.25;
    public final static double ALBEDO_GREY = 0.5;

    // the amount of heat absorbed by an empty patch.
    public final static double ALBEDO_SURFACE = 0.4;

    // the average solar luminosity of our sun
    public final static double SOLAR_LUMINOSITY = 0.8;

    // the percentage of diffusion
    public final static double DIFFUSION = 0.5;

}

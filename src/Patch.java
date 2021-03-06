import java.util.ArrayList;
import java.util.List;

/**
 * the Patch class represent a block of land on the map.
 * It can have a daisy on the patch, and each patch has its own temperature
 *
 * each land has an albedo when there is no daisy(Params.ALBEDO_SURFACE)
 * each step update temperature by:
 *      1. the energy absorbed/unleashed by the daisy(if any)
 *      2. the energy diffused from neighbors(50% of neighbor's temp / 8)
 *
 * Also, after each step
 * if there is daisy on the patch and the neighbour patch is open,
 * there is probability of sprouting daisy on open neighbours
 * with the same colour as the patch's daisy
 *
 * The probability is 100% when the temperature is 22.5
 * **/

public class Patch {
    protected Daisy daisy;
    protected double temp;


    /**
     * the patch by default has no daisy
     * the initial temperature on the patch is 0
     * */
    public Patch() {
        this.daisy = null;
        this.temp = 0;
    }

    // a daisy could be passed in when initializing the board
    public Patch(Daisy daisy) {
        this.daisy = daisy;
        this.temp = 0;
    }


    /**
     * updateTemp() add the local heating to local temperature
     * by calculating the absorbed luminosity based on local daisy's albedo
     * of the patch's surface albedo if is empty
     * **/
    public void updateTemp(){
        double absorbedLuminosity;
        double localHeating;

        if(daisy == null){
            // the percentage of absorbed energy is calculated
            // with (1 - albedo-of-surface) * solar-luminosity
            absorbedLuminosity = (1 - Params.ALBEDO_SURFACE)
                    * Params.SOLAR_LUMINOSITY;
        } else {
            // if it has daisy calculate luminosity with daisy's albedo
            absorbedLuminosity = (1 - daisy.getAlbedo())
                    * Params.SOLAR_LUMINOSITY;
        }

        // in case the solar luminosity could be 0, add if statement
        if(absorbedLuminosity > 0){
            localHeating = 72 * Math.log(absorbedLuminosity) + 80;
        } else {
            localHeating = 80;
        }

        // set the temperature to be the average of
        // the current temperature and the local-heating effect
        this.temp = (this.temp + localHeating) / 2;
        // System.out.println(this.temp);
    }

    /**
     * diffuse() decrease the local heat
     * and returns the energy diffused to neighbour
     * the diffusion rate in defined in Params.java
     * **/
    public void diffuse(List<Patch> neighbours) {
        double oldTemp = this.temp;
        this.temp *= (1 - Params.DIFFUSION);

        for (Patch p : neighbours) {
            p.temp += (oldTemp * Params.DIFFUSION) / 8;
        }
    }

    /**
    * receiveDiffusion takes a list of neighbours, and calculate each
     * neighbours' diffusion, then update its temperature
    * */


    /**
     * checkSurvivability() updates the occupancy of the patch
     * if the daisy is dead, set the patch open
     * if not, daisy grows older, check again if dead
     * then calculate the probability of
     * sprouting a new daisy based on local temperature
     * **/
    public void checkSurvivability(List<Patch> neighbours){
        if (this.daisy != null) {
            this.daisy.age++;
            if (this.daisy.isDead()){
                this.daisy = null;
            } else {
                // calculate the possibility of seeding
                double seedThreshold = 0.1457 * this.temp - 0.0032
                        * Math.pow(this.temp, 2) - 0.6443;

                // check if any neighbour is empty
                List<Patch> openNeighbours = new ArrayList<>();
                for(Patch neighbour : neighbours){
                    if(neighbour.daisy == null){
                        openNeighbours.add(neighbour);
                    }
                }

                // choose one open neighbour randomly and sprout a new daisy
                double possibility = Math.random();
                if(!openNeighbours.isEmpty() && possibility < seedThreshold){
                    int index = (int)(Math.random() * openNeighbours.size());
                    openNeighbours.get(index).daisy =
                            new Daisy(this.daisy.colour, 0);
                }
            }
        }
    }
}

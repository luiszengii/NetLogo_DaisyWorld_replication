import java.util.ArrayList;
import java.util.List;

/**
 * each land has an albedo when there is no daisy(Params.ALBEDO_SURFACE)
 * each time step update temperature by:
 *      1. the energy absorbed/unleashed by the daisy(if any)
 *      2. the energy diffused from neighbors(50% of neighbor's temp / 8)
 *
 * Also, after each step if there is daisy on adjacent patch and the patch is open,
 * there is probability of sprouting daisy with the same colour as the neighbour.
 *
 * The probability is 100% when the temperature is 22.5
 * **/

public class Patch {
    protected Daisy daisy;
    protected double temp;

    public Patch(Daisy daisy, double temperature) {
        this.daisy = daisy;
        this.temp = temperature;
    }

    /**
     * receives heat diffusion from neighbors(calculated in Board.java)
     * then calculate local heat changes
     * **/
    public void update_temp(double receivedDiffusion){
        double absorbedLuminosity;
        double localHeating;
        if(daisy == null){
            // the percentage of absorbed energy is calculated (1 - albedo-of-surface)
            // and then multiplied by the solar-luminosity to give a scaled absorbed-luminosity.
            absorbedLuminosity = (1 - Params.ALBEDO_SURFACE) * Params.SOLAR_LUMINOSITY;
        } else {
            if (this.daisy.colour == Params.DAISY_COLOUR.white){
                absorbedLuminosity = (1 - Params.ALBEDO_WHITE) * Params.SOLAR_LUMINOSITY;
            } else {
                absorbedLuminosity = (1 - Params.ALBEDO_BLACK) * Params.SOLAR_LUMINOSITY;
            }
        }

        if(absorbedLuminosity > 0){
            localHeating = 72 * Math.log(absorbedLuminosity) + 80;
        } else {
            localHeating = 80;
        }

        this.temp = (this.temp + localHeating) / 2 + receivedDiffusion;
    }

    /**
     * returns the energy diffuse to neighbors
     * **/
    public double diffuse(){
        return (this.temp * Params.DIFFUSION) / 8;
    }


    /**
     * updates the occupancy of the patch
     * if the daisy is dead, set the patch open
     * if not, daisy grows older, check again if dead
     * then calculate the probability of sprouting a new daisy based on local temperature
     * **/
    public Daisy checkSurvivability(List<Patch> neighbours){
        if (this.daisy != null) {
            this.daisy.age++;
            if (this.daisy.is_dead()){
                this.daisy = null;
            } else {
                double prob = 0.1457 * this.temp - 0.0032 * Math.pow(this.temp, 2) - 0.6443;
                //check if any neighbour is empty
                List<Patch> openNeighbours = new ArrayList<>();
                for(Patch neighbour : neighbours){
                    if(neighbour.daisy == null){
                        openNeighbours.add(neighbour);
                    }
                }
                //choose one open neighbour randomly and sprout the daisy
                if(!openNeighbours.isEmpty()){
                    int index = (int)(Math.random() * openNeighbours.size());
                    openNeighbours.get(index).daisy = new Daisy(this.daisy.colour);
                }
            }
        }
        return null;
    }

    public void update_local_temp(){
        //TODO: updates this.temp by calculation(daisy's albedo)

    }
}

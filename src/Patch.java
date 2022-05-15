public class Patch {
    private Daisy daisy;
    private float temp;

    public Patch(Daisy daisy, float temperature) {
        this.daisy = daisy;
        this.temp = temperature;
    }

    public boolean check_survivability(){
        //TODO: returns if the daisy survives
        boolean survival = true;
        return survival;
    }

    public void update_local_temp(){
        //TODO: updates this.temp by calculation(daisy's albedo)

    }

    public Daisy getDaisy() {
        return this.daisy;
    }

    public void setDaisy(Daisy daisy) {
        this.daisy = daisy;
    }

    public float getTemperature() {
        return this.temp;
    }

    public void setTemperature(float temperature) {
        this.temp = temperature;
    }
}

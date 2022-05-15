public class Daisy {
    private Params.DAISY_COLOUR colour;
    private int age;
    private float albedo;

    public Daisy(Params.DAISY_COLOUR colour, int age, float albedo) {
        this.colour = colour;
        this.age = age;
        this.albedo = albedo;
    }

    public void dead(){
        //TODO: remove this daisy from the world
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getAlbedo() {
        return albedo;
    }

    public void setAlbedo(float albedo) {
        this.albedo = albedo;
    }
}

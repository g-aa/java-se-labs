package task4_animal;

public class Bird extends Animal
{

    public Bird(String name) { super(name); } //, "fly", "chirp"); }

    @Override
    public String moves() {
        return "fly";
    }

    @Override
    public String sound() {
        return "chirp";
    }
}
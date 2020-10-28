package task4_animal;

public class Fish extends Animal
{
    public Fish(String name) { super(name);} //, "swim", "nothing"); }

    @Override
    public String moves() {
        return "swim";
    }

    @Override
    public String sound() {
        return "nothing";
    }
}

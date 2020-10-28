package task4_animal;

public class SeaMammal extends Mammal
{
    public SeaMammal(String name, String sound)
    {
        super(name, sound);
    }

    @Override
    public String moves() {
        return "swim";
    }
}

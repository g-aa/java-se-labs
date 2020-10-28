package task4_animal;

public class TerreMammal extends Mammal
{
    public TerreMammal(String name, String sound)
    {
        super(name, sound);
    }

    @Override
    public String moves()
    {
        return "run";
    }
}

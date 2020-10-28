package task4_animal;

public abstract class Mammal extends Animal
{
    private String soundForm;

    public Mammal(String name, String sound)
    {
        super(name); //, moves,sound);
        this.soundForm = sound;
    }

    @Override
    public String sound()
    {
        return soundForm;
    }
}

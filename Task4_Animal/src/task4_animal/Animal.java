package task4_animal;

import java.util.List;

public abstract class Animal
{
    private String name;
    // private String movesForm;
    // private String soundForm;

    public Animal(String name)
    {
        this.name = name;
    }
    // {
    //    this.name = name;
    //    this.movesForm = moves;
    //    this.soundForm = sound;
    // }

    public abstract String moves(); // { return movesForm; }
    public abstract String sound(); // { return soundForm; }

    public String output()
    {
        return name + " " + this.getClass();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task4_animal;

/**
 *
 * @author Andrey
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Fish animal_1 = new Fish("shark");
        Bird animal_2 = new Bird("eagle");
        SeaMammal animal_3 = new SeaMammal("whale", "sing");
        TerreMammal animal_4 = new TerreMammal("dog", "bark");

        Animal[] animals = {animal_1, animal_2, animal_3, animal_4};
        for (Animal item: animals) {
            System.out.println(item.output() + ",\t" + item.moves()+ ",\t" + item.sound());
        }
        
    }
    
}

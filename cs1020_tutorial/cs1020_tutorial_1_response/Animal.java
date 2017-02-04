class Animal {
  private String name;
  private String sound;
  public void print(){
    System.out.print(name + " goes " + sound);
  }
}

public class Song{
  
  private Animal[] _animals;
  private static final int ANIMAL_COUNT = 5;
  
  public Song(){

     Animal[] _animals[] = new Animal[ANIMAL_COUNT];
     _animals[0] = new Animal("Dog", "woof");
     _animals[1] = new Animal("Cat", "meow");
     _animals[2] = new Animal("Bird", "tweet");
     _animals[3] = new Animal("Mouse", "squeak");
     _animals[4] = new Animal("Cow", "moo");
     
     for (int i = 0; i < ANIMAL_COUNT; i++){
       _animal[i].print();
     }
  }
  
  public void dispaly(){
    
    for (int i = 0; i< ANIMAL_COUNT;i++)
      System.out.println(_animal[i].makeSound());
  }
  
  public static void main(String[] args){
   (new Song()).display();
  }
} 


public class Song{
  
  private Animal[] _animals;
  private static final int ANIMAL_COUNT = 5;
  
  public Song(){ //constructor

     Animal[] _animals = new Animal[ANIMAL_COUNT];
     _animals[0] = new Animal("Dog", "woof");
     _animals[1] = new Animal("Cat", "meow");
     _animals[2] = new Animal("Bird", "tweet");
     _animals[3] = new Animal("Mouse", "squeak");
     _animals[4] = new Animal("Cow", "moo");
     
  }
  
  public void display(){
    
    for (int i = 0; i < ANIMAL_COUNT;i++)
      _animals[i].print();
  }
  
  public static void main(String[] args){
   (new Song()).display();
  }
} 

class Animal {
  private String name;
  private String sound;
  
  public Animal(String nameIn, String soundIn){
  
    this.name = nameIn;
    this.sound = soundIn; 
  }
  
  public void print(){
    
    System.out.println(name + " goes " + sound);
  }
}
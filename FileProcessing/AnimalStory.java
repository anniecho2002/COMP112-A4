/* Code for COMP-102-112 - 2021T1, Assignment 4
 * Name: Annie Cho
 * Username: choanni
 * ID: 300575457
 */

import ecs100.*;
import java.awt.Color;
import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.util.HashMap;

/** Displays an animated story of at least two characters from a script in a file.
 *  Each line of a script file is an instruction.
 *  See "simple-script.txt" for an example.
 *
 *  These are the possible instructions (<..> is describing a value
 *    CHARACTER  <name> <type>
 *         creates a new Animal with the given name and type, but holds them off-screen
 *         <type> can be any of bird, dinosaur, dog, grasshopper, snake, tiger, turtle.
 *         This instruction must occur before any other instructions involving the animal
 *         It is OK to insist that all the CHARACTER instructions are at the beginning of
 *         the script.
 *    ENTER <name> <x> <y>   :
 *         make the animal with that name appear on the screen at the position <x>,<y>
 *    GO <name> <direction> <distance> :
 *         turn the animal with that name to the direction (left or right) and move by <distance>
 *    TELEPORT <name> <x> <y> :
 *         teleport the animal with that name to the new position
 *    EXIT <name>
 *         make the animal with that name disappear from the screen
 *    JUMP <name> <height> :
 *         make the animal with that name jump by the height
 *    SPEAK <name> <words> :
 *         make the animal with that name speak the words (can be multiple tokens)
 *    THINK/SHOUT/INTRODUCE work just like SPEAK.
 *    CAPTION <words>  :
 *         display the words as a caption at the top of the window.
 * 
 *  extras (for top marks)
 *    ASK <question words>, ......
 *    ELSE
 *    ENDASK
 *         ask the user a question and follow two different paths in the story depending
 *         on the answer:
 *           the instructions between ASK and ELSE if they said "yes"
 *           the instructions between ELSE and ENDASK if they said "no"
 *         You do not have to handle ASK instructions inside other ASK instructions,
 *         but you can if you want! (you might need to label the ASK/ELSE/ENDASK instructions!
 *    
 *    REPEAT <number>
 *    ENDREPEAT
 *         repeat the instructions between REPEAT and ENDREPEAT <number> times.
 *    
 */
public class AnimalStory{
    /**
     * storyFromFile opens a script file, and animates the story in the file
     *  by following all the instructions in the script
     *  Each line of the file is one instruction (see above).
     */
    public void storyFromFile(){
        HashMap<String, Animal> animals = new HashMap<String, Animal>();
        String filename = UI.askString("What is the name of the file?");
        
        try{
            List<String> lines = Files.readAllLines(Path.of(filename)); //opens file
            int characterCount = 0;
            for (String line : lines) {
                Scanner scanner = new Scanner(line);
                String method = scanner.next();
                String character = scanner.next();
                String methodInputString = ""; // initializes as nothing
                
                if (method.equals("CHARACTER")){
                    methodInputString = scanner.next();
                    animals.put(character, new Animal(methodInputString, character, -1000, 0));
                }
                
                if (method.equals("SPEAK")){
                    methodInputString = scanner.nextLine();
                    animals.get(character).speak(methodInputString);
                }
                else if(method.equals("THINK")){
                    methodInputString = scanner.nextLine();
                    animals.get(character).think(methodInputString);
                }
                else if (method.equals("SHOUT")){
                    methodInputString = scanner.nextLine();
                    animals.get(character).shout(methodInputString);
                }
                else if(method.equals("ENTER") || method.equals("TELEPORT")){
                    double x = scanner.nextDouble();
                    double y = scanner.nextDouble();
                    animals.get(character).teleport(x,y);
                }
                else if (method.equals("GO")){
                    String direction = scanner.next();
                    double distance = scanner.nextDouble();
                    if (direction.equals("right")){
                        animals.get(character).goRight(distance);
                        }
                    if (direction.equals("left")){
                        animals.get(character).goLeft(distance);
                        }
                }
                else if (method.equals("JUMP")){
                    double jumpDistance = scanner.nextDouble();
                    animals.get(character).jump(jumpDistance);
                }
                else if (method.equals("INTRODUCE")){
                    methodInputString = scanner.nextLine();
                    animals.get(character).introduce(methodInputString);
                }
                else if (method.equals("EXIT")){
                    animals.get(character).goLeft(800);
                }
                //UI.println(method + " " + character);
            }
        }catch(IOException e){UI.println("File reading failed");}
    }
    /** Set up the buttons */
    public void setupGUI (){
        UI.addButton("Clear", UI::clearGraphics );
        UI.addButton("Story", this::storyFromFile);
        UI.addButton("Quit", UI::quit );
    }        

    public static void main(String[] args){
        AnimalStory Ani = new AnimalStory();
        Ani.setupGUI();
    }

}

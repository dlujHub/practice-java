package CharactersInPlay;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Compute the number of times each character appears in "AS YOU LIKE IT" by William Shakespeare
 *
 * Print the names that appear more that 10 times.
  */

public class CharactersInPlay {
   private static ArrayList<String>  characters = new ArrayList<>();
    private static ArrayList<Integer> freqs = new ArrayList<>();

    public static void main(String args[]) throws FileNotFoundException {
               findAllCharacters();
               for (int i=0; i<characters.size(); i++) {
                   if(characters.get(i).length()<15 && freqs.get(i)>10) {
                       System.out.println(characters.get(i) + " " + freqs.get(i));
                   }

               }
    }

    private static void update(String person) {
         int index = characters.indexOf(person);
         if (index == -1) {
            characters.add(person);
            freqs.add(1);
         }
         else {
             int value = freqs.get(index);
             freqs.set(index, value + 1);
         }
    }

    private static void findAllCharacters() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("CharactersInPlay/shakespeare.txt"));
        while(sc.hasNextLine()) {
            String curr = sc.nextLine();
            int indexDot = curr.indexOf('.');
            if (indexDot != -1){
                update(curr.substring(0, indexDot));
            }
        }
    }
}

package CodonDNACount;

import java.io.FileNotFoundException;
import java.util.HashMap;

/**
 * Counts of codons in DNA sequence.
 *
 */
public class Count {
    private static HashMap<String, Integer> map = new HashMap<>();

    public static void main(String args[]) throws FileNotFoundException {
        String dna = "CGTTCAAGTTCAA";
        for (int i=0; i<3; i++) {
            buildCodonMap(i, dna);
            System.out.println("Reading frame starting with " + i + " results in " + map.size() + " unique codons");

          /*  System.out.println("All codons");
            for (String codon : map.keySet()) {
                System.out.print(codon + " ");
            }*/
          String common = getMostCommonCodon();
            System.out.println("and most common codon is " + common + " with count " + map.get(common));
            System.out.println("Counts of codons between 1 and 5 inclusive are:");
            printCodonCounts(1,5);
        }
    }

    private static void buildCodonMap(int start, String dna){
        map.clear();
        while (start < dna.length() - 2) {
            String codon = dna.substring(start, start + 3);
            if (map.keySet().contains(codon)) {
                map.put(codon, map.get(codon) + 1);
            } else {
                map.put(codon, 1);
            }
            start += 3;
        }
    }

    private static String getMostCommonCodon() {
        int occurrences = 0;
        String result = "";
        for (String codon : map.keySet()) {
            if (map.get(codon) > occurrences) {
                occurrences = map.get(codon);
                result = codon;
            }
        }
        return result;
    }

    private static void printCodonCounts(int start, int end) {
        int counts ;
        for (String codon : map.keySet()) {
            counts = map.get(codon);
            if (counts>=start && counts<=end) {
                System.out.println(codon + " " + counts);
            }
        }
    }

}
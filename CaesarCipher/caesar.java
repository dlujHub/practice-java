package CaesarCipher;

//a Caesar cipher, also known as Caesar's cipher, the shift cipher, Caesar's code or Caesar shift, is one of the simplest
// and most widely known encryption techniques. It is a type of substitution cipher in which each letter in the plaintext
// is replaced by a letter some fixed number of positions down the alphabet. For example, with a left shift of 3, D would
// be replaced by A, E would become B, and so on.

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class caesar {
    private static CaesarCipher cs = new CaesarCipher(15);

    public static void main(String args[]) throws IOException {

        System.out.println("Encrypt \"YELL LOUD!\"");
        String encrypt = cs.encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!");
        System.out.println(encrypt);

        System.out.println("\n" + "Encrypt all file");
        String message = readFile("CaesarCipher/caesar.txt", StandardCharsets.UTF_8);
        String forDecrypt = encrypt(message, 23);
        System.out.println(forDecrypt);

        System.out.println("\n" + "Decript \n  " + decrypt(forDecrypt));

        System.out.println("\n" + "Encrypt double key");
        String forDoubleKey = "Just a test string with lots of eeeeeeeeeeeeeeeees";
        System.out.println(encrypt(forDoubleKey, 2, 23));

        System.out.println("\n" + "Decript Two keys \n  " + decryptTwoKeys(forDoubleKey));


         }

    private static int getKey(String s) {
      int[] freq = countLetters(s);
       int maxIndex = getMaxIndex(freq);
        int dkey = maxIndex - 4;
        if (maxIndex<4) dkey = 26 - (4-maxIndex);
        return 26 - dkey;

    }
    private static String decryptTwoKeys(String encrypted) {
        String first = halfOfString(encrypted, 0);
        String second = halfOfString(encrypted, 1);
        int key1 = getKey(first);
        int key2 = getKey(second);
        return encrypt(encrypted, key1, key2);
    }

    private static String halfOfString(String message, int start) {
        StringBuilder result = new StringBuilder();

        if (start == 0) {
            for(int i=0; i<message.length(); i++) {
                if ((i % 2) == 0) result.append(message.charAt(i));
            }
        }
        else
        if (start == 1) {
            for(int i=0; i<message.length(); i++) {
                if ((i % 2) != 0) result.append(message.charAt(i));
            }
        }
        return result.toString();
    }

    private static String decrypt(String input) {
       int[] freqs = countLetters(input);

       int maxIndex = getMaxIndex(freqs);

       int dkey = maxIndex - 4;
       if (maxIndex<4) dkey = 26 - (4-maxIndex);
       return encrypt(input, 26 - dkey);

    }

    private static int[] countLetters(String message) {
        int[] freqs = new int[26];
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for(int i=0; i<message.length(); i++) {
            char ch = Character.toLowerCase(message.charAt(i));
            int index = alphabet.indexOf(ch);
            if (index != -1)
                freqs[index] += 1;
        }
        return freqs;
    }

    private static int getMaxIndex(int[] freqs) {
     int max = 0;
    for(int i=0; i<freqs.length; i++){
        if (freqs[i] > freqs[max])
            max = i;
    }
    return max;
    }

    //for 1 key
    private static String encrypt(String input, int key) {
        CaesarCipher csNew = new CaesarCipher(key);
        return csNew.encrypt(input);
    }

    //for double key
    private static String encrypt(String input, int key1, int key2) {
        String alphabetUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetLower = "abcdefghijklmnopqrstuvwxyz";
        String salphabetUpper1 = alphabetUpper.substring(key1) + alphabetUpper.substring(0,key1);
        String salphabetLower1 = alphabetLower.substring(key1) + alphabetLower.substring(0,key1);
        String salphabetUpper2 = alphabetUpper.substring(key2) + alphabetUpper.substring(0,key2);
        String salphabetLower2 = alphabetLower.substring(key2) + alphabetLower.substring(0,key2);

        StringBuilder encrypt = new StringBuilder(input);

        for (int i=0; i<encrypt.length(); i++) {
            char curr = encrypt.charAt(i);
            int index = alphabetUpper.indexOf(curr);
           //odd or even, for key1 or key2
            if ((i%2) != 0) {

                if (index != -1) {
                    char newchar = salphabetUpper1.charAt(index);
                    encrypt.setCharAt(i, newchar);
                } else {
                    index = alphabetLower.indexOf(curr);
                    if (index != -1) {
                        char newchar = salphabetLower1.charAt(index);
                        encrypt.setCharAt(i, newchar);
                    }
                }
            }
            else {

                if (index != -1) {
                    char newchar = salphabetUpper2.charAt(index);
                    encrypt.setCharAt(i, newchar);
                } else {
                    index = alphabetLower.indexOf(curr);
                    if (index != -1) {
                        char newchar = salphabetLower2.charAt(index);
                        encrypt.setCharAt(i, newchar);
                    }
                }
            }
        }

        return encrypt.toString();
    }

    //read from file and put all in string
   private static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
    }



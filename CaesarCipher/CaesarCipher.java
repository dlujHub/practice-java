package CaesarCipher;

public class CaesarCipher {
    private String alphabetUpper, alphabetLower, salphabetUpper, salphabetLower;

    CaesarCipher(int key) {
       alphabetUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
       alphabetLower = "abcdefghijklmnopqrstuvwxyz";
         salphabetUpper = alphabetUpper.substring(key) + alphabetUpper.substring(0,key);
       salphabetLower = alphabetLower.substring(key) + alphabetLower.substring(0,key);

    }

    public String encrypt(String input) {
        StringBuilder encrypt = new StringBuilder(input);
        for (int i = 0; i < encrypt.length(); i++) {
            char curr = encrypt.charAt(i);
            int index = alphabetUpper.indexOf(curr);
            if (index != -1) {
                char newchar = salphabetUpper.charAt(index);
                encrypt.setCharAt(i, newchar);
            } else {
                index = alphabetLower.indexOf(curr);
                if (index != -1) {
                    char newchar = salphabetLower.charAt(index);
                    encrypt.setCharAt(i, newchar);
                }
            }
        }

        return encrypt.toString();
    }
}

package MarkovRandomTextGenerator;

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    protected int order;
    AbstractMarkovModel(int n) {
        myRandom = new Random();
        order = n;
    }

    AbstractMarkovModel() {
        myRandom = new Random();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s) {
        myText = s.trim();
    }
 
    abstract public String getRandomText(int numChars);

    protected ArrayList<String> getFollows(String key) {
        ArrayList<String> answer = new ArrayList<>();
        int pos = 0;
        while(pos <  myText.length()) {
            int start = myText.indexOf(key, pos);
            if (start == -1 || start + key.length() >= myText.length()-1) {break; }
            String next = myText.substring(start + key.length(), start + key.length() +1);
            answer.add(next);
            pos = start + key.length();
        }
        return answer;
    }
}

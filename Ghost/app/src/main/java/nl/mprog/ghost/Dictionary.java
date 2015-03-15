package nl.mprog.ghost;

import android.content.Context;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;


public class Dictionary {

    public HashSet dictionary = new HashSet();

    Dictionary(Context ctx, int language){
        InputStream english = ctx.getResources().openRawResource(language);

        InputStreamReader inputReader = new InputStreamReader(english);
        BufferedReader buffReader = new BufferedReader(inputReader);
        String line;

        try {
            while ((line = buffReader.readLine()) != null) dictionary.add(line);
            /*System.out.println(line);*/
        } catch (IOException e) {
        }
    }

    public boolean checkInDictionary(String wordToCheck) {
        return dictionary.contains(wordToCheck);
    }

    public boolean hasWordsStartingWith(String wordToCheck) {
        Iterator lines = dictionary.iterator();
        while (lines.hasNext()){
            String currentWord = lines.next().toString();
            if (currentWord.startsWith(wordToCheck)) {
                return true;
            }
            else lines.remove();
        }
        return false;
    }

    public boolean hasOtherWordContaining(String wordToCheck) {
        Iterator lines = dictionary.iterator();
        while (lines.hasNext()) {
            String currentWord = lines.next().toString();
            if (currentWord.startsWith(wordToCheck) && currentWord.trim().length() > 3) {
                return true;
            }
        }
        return false;
    }

}


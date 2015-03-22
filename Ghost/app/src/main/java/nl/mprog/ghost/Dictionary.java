package nl.mprog.ghost;

import android.content.Context;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;


public class Dictionary {

    public HashSet<String> dictionary = new HashSet<>();

    Dictionary(Context ctx, String language){

        int languageId;
        if (language.equals("English")) {
            languageId = R.raw.english;
        }
        else {
            languageId = R.raw.dutch;
        }

        InputStream textFile = ctx.getResources().openRawResource(languageId);

        InputStreamReader inputReader = new InputStreamReader(textFile);
        BufferedReader buffReader = new BufferedReader(inputReader);
        String line;

        try {
            while ((line = buffReader.readLine()) != null) dictionary.add(line);
        } catch (IOException ignored) {
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
        for (Object aDictionary : dictionary) {
            String currentWord = aDictionary.toString();
            if (currentWord.startsWith(wordToCheck) && currentWord.trim().length() > 3) {
                return true;
            }
        }
        return false;
    }

}


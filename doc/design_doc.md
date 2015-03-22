# Design document - Ghost Game
## Cyrille Jones 10838635

The goal of this project is to implement the game of Ghost which is a two player wordgame for Android.

## Procedure and design decisions
	
	The application consists of a 4 objects created using model classes and 6 activities where interaction with the users take place.
	The first object created is the dictionary which, itself, contains some public methods that represent the functionality of a real dictionary
	such as searching for a word or the beginning of a word. This dictionary is then used in the Game which is also a model class. Here, all the 
	rules of the game of Ghost are represented as methods of the Game class, details of these methods are found in the following sections. 
	Because Ghost is a two player wordgame, the third class created is the Player class which represents a normal player with a name and a score.
	The last class created is the HighScore class which also contains a few methods to add a player to the high score list or update a score if 
	the player is already in the list. Again more on these methods in the Model classes section.

	Combining these object classes with the activities gives the user the ability to interact with the application and accomplish tasks provided 
	by this game. 

	On the SettingAcitivity where the users are asked for their names and the language of the dictionary they want to play with, the names entered 
	in the corresponding text boxes are then saved in a list so that the user doesn't have to retype his name everytime he plays. In this activity, 
	once all the data is entered, it is then sent to the GamePlayActivity where the game is played. In this acitivity, the methods of the classes 
	described above are used here to interact with the players and the state of the game such as changing player's turn or knowing when the game is
	finished, who the winner is, add his score to the high score list, update the high score or save it in the SharedPreferences.


## Names of controllers

	1. HomeActivity: Contains 3 buttons:
		* Play button: opens the SettingActivity.
		* Rules button: opens RulesActivity.
		* Scores button: displays the high scores, opens ScoresActivity.

	2. SettingActivity: activity where the name of the two players are entered and the the choice of a dictionary english or dutch. It contains:
		* Two edit text views with two spinners, the spinners displays the names of the players that have already played and possible new players.
		* A spinner for the dictionary's language: english or dutch.
		* Play button: opens GamePlayActivity to start the game.

	3. RulesActivity: Here are the rules displayed and how to play. It contains:
		* Text view with rules.
		* Play button: opens GamePlayActivity too.

	4. GamePlayActivity: The game is played on this activity. It contains:
		* Text view with name of current player.
		* Text view with the word fragment.
		* Edit text view for the player's input letter.
		* Submit button: Once pressed, methods of game class, dictionary class and high score class are called to decide whether someone has won or not
		and whether to update the high score list or not.

	5. WinnerActivity: congratulates the winner and displays the reason why he won. It contains also:
		* Text view with the name of the winner.
		* Text view with the reason why he wins.
		* Scores button: opens the ScoresActivity and displays the high score list.

	6. ScoresActivity: 
		* A numbered list of the highest scores with the name of the players.
		* New game button: opens SettingsActivity to restart a game.
		* Home button: opens HomeActivity.
	

## State variables

	* player1: variable of type Player with a name and a score attribute.
	* player2: variable of type Player with a name and a score attribute.
	* highScoreList: variable of type ArrayList<Player> containing players with the best high scores.
	* currentPlayer: variable of type int with a 1 if player 1 is playing and 2 if player 2 is playing.
	* dictionary: variable of type Dictionary with a language attribute (English or Dutch).
	* usersList: variable of type ArrayList<String> containing all the names of the players that have played Ghost.


## Model classes and public methods

	1. Dictionary

		* Constructor: Dictionary(Context ctx, String language)
			* Takes as argument the context in which the dictionary will be used and the language of it as a string. 
			* Returns a dictionary object with the required language.

		* public boolean checkInDictionary(String wordToCheck)
			* Takes the word to check as argument and uses the build-in function contains(String s) to check.
			* Returns true if the word is in it and false if it is not.

		* public boolean hasWordsStartingWith(String wordToCheck)
			* Takes the word to check as argument and uses the build-in function startsWith(String s) to check if a word starts with certain characters.
			* Returns true if it does and false if it does not.

		* public boolean hasOtherWordContaining(String wordToCheck)
			* Takes the word to check as argument and also uses buid-in function startsWith(String s) to check whether there is more than one word containing word to check.
			* Returns true if there are other word and false if there aren't any.

	
	2. Player

		* Constructor: Player(String name, int score)
			* Takes as argument a string for the name of the player and an int for the score of the player.
			* Returns a Player object with a name and a score.

		* public int getScore()
			* method that returns the score of the player as an int.

		* public void setScore(int score)
			* Takes as argument an int (a score) and sets the score of the player to that value.

		* public String getName()
			* method that returns the name of the player as a string.

		* public void setName(String name)
			* Takes as argument a string (a name) and sets the name of the player to that value.


	3. Game

		* Constructor: public Game(Context ctx, Player player1, Player player2, String language)
			* Takes as argument the context in which the game is played, but also two players and a string for the language of the dictionary.
			* Returns a Game object with two players and the type of dictionary that will be used.

		* public String guessLetter(String userInput, String wordFragment)
			* Takes as argument two strings, first the user's input letter and the current word fragment.
			* Returns a string, namely the word to be checked in lower case using the function toLowerCase().

		* public String hasWon(String wordToCheck) 
			* Takes as argument a string which is the word to check.
			* Uses methods of Dictionary class (checkInDictionary, hasWordsStartingWith and hasOtherWordsStartingWith)
			* Returns the string "WORD" if the word fragment is a word, a string "NO_WORD" if it is not a word and "NOT_WON" if the word is not a word yet or 
			if the word is a word but can still become another word.

		* public Player gameWinner(String loser)
			* Takes as argument the name of the player who loses as a string.
			* Returns a Player, namely the winner.


	4. HighScore

		* Constructor: HighScore(Context ctx)
			* Takes as argument the context in which the High Score will be used.
			* Also initiates an ArrayList of players and get the SharedPreferences, so the data that is stored and saved from previous games.

		* public ArrayList<Player> getHighScoreList()
			* Initiates a Map of strings to get all the player's names and scores from SharedPreferences and add the players to the array list.
			* Sorts the list using the build-in function Collections.sort with a comparator in order to sort the list on the player's scores in descending order.
			* Returns a sorted list of players that have the best high scores.

		* public boolean allReadyInHighScore(Player winner, ArrayList<Player> highScoreList)
			* Takes as argument a Player, namely the winner of the game and an array list of players.
			* Uses the build-in function equals() to check if the winner's name already is in the list.
			* Returns true if it is already in the list and false otherwise.

		* public void addToHighScore(Player player, ArrayList<Player> highScoreList)
			* Takes as argument a Player and an array list of players (the highscore lsit)>
			* Uses the build-in functions get(int index) and set(int index, Player player) to add the player in the highscore list if his score is higher than the score
			of the player in 5th position.

		* public ArrayList<Player> updateHighScorePlayer(Player winner, ArrayList<Player> highScoreList)
			* Takes as argument a Player, namely the winner, an ArrayList of players (highscore list).
			* Returns an ArrayList of players with the updated score of the winner that already is in the list.

		* public void saveHighScoreList(ArrayList<Player> list)
			* Takes as argument an ArrayList of players.
			* Uses the editor of the SharedPreferences to save the score of each player with as key the name of the player (editor.putInt(key, value)).

		* public ArrayList<String> getNames(ArrayList<Player> list)
			* Takes as argument an ArrayList of players and returns the name of the players in that list as an ArrayList of strings.

		* public ArrayList<Integer> getScores(ArrayList<Player> list)
			* * Takes as argument an ArrayList of players and returns the score of the players in that list as an ArrayList of integers.


## Platform-specific controls

	* spinnerPlayer1 on Settings screen: 
		* Edit text view with a spinner on its right side. The spinner is updated and saved in SharedPreferences each time a new player's name is entered.

	* spinnerPlayer2 on Settings screen
		* * Edit text view with the same spinner as the first one on its right side. The spinner is updated and saved in SharedPreferences each time a 
		new player's name is entered.

	* spinnerDictionary on Settings screen
		* A spinner with two string items in it: "English" and "Dutch".

	* A Option menu on Game screen: 'menu_game_play'
		* A menu with as item a string "Settings" in order to go back to the setting screen and change the names of the players and the language of the dictionary.


## APIs and frameworks/imports

	* The programming language used to build this application is Java for the functionality and XML for the layout and style.
	* The program used to build the project is Android Studio.

	* import java.io.BufferedReader: used to read data from inputStreamReader;
	* import java.io.IOException: throws exceptions on input errors;
	* import java.io.InputStream: used to temporary store data from a raw text file (dictionaries);
	* import java.io.InputStreamReader: used to read data from inputStream;
	
	* import android.app.Activity;

	* import android.content.Context: used to get the context of the application where objects can be used;
	* import android.content.Intent: used to send informations between activities;
	* import android.content.SharedPreferences: used to store and save data even if application is killed;

	* import android.os.Bundle;

	* The widgets and views listed below are used in this project:

		* import android.view.Menu;
		* import android.view.MenuInflater;
		* import android.view.MenuItem;
		* import android.view.View;
		* import android.widget.EditText;
		* import android.widget.TextView;
		* import android.widget.ListView;
		* import android.widget.Spinner;
		* import android.widget.Button
		* import android.widget.ArrayAdapter;
		* import static android.widget.Toast.*;
		* import static android.widget.Toast.makeText;

	* import android.text.TextWatcher: used to warn user for errors while changing text in an edit text view;
	
	* Ways to store data as a set, hashset, maps or array lists:
	
		* import java.util.HashSet;
		* import java.util.Set;
		* import java.util.Map;
		* import java.util.ArrayList;

	* import java.util.Iterator: used to iterate over a set or array list;
	* import java.util.Collections: used to sort lists in ascending or descending order;
	* import java.util.Comparator: compares two object's specific parameters;
	* import java.util.Arrays: used to convert arrays into lists;	


## UI sketches

![My image](Cyrille2912.github.com/AppStudio/doc/home_screen.png)
![My image](Cyrille2912.github.com/AppStudio/doc/settings_screen.png)
![My image](Cyrille2912.github.com/AppStudio/doc/game_play_screen.png)
![My image](Cyrille2912.github.com/AppStudio/doc/winner_screen.png)
![My image](Cyrille2912.github.com/AppStudio/doc/high_scores.png)
![My image](Cyrille2912.github.com/AppStudio/doc/rules_screen.png)





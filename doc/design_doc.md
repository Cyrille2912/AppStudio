# Design document - Ghost Game

## Names of controllers
	* HomeScreen
	* SettingsScreen
	* RulesScreen
	* GamePlayer1
	* GamePlayer2
	* WinnerScreen
	* HighScoreScreen
	

## State variables
	* Player1
	* Player2
	* NameSpinnerList
	* InputLetter
	* WordFragment
	* ScorePlayer1
	* ScorePlayer2
	* HighScores
	* ReasonForWinningText
  * DictionaryState

## Platform-specific controls
	* Editable TextView with spinner with for class name: 'PlayerNameSpinner'
	* Spinner for dictionary entry: 'DictionarySpinner'
	* A Option menu on Gamescreen: 'OptionGameMenu'

## Public methods/Actions
	* ChangePlayerScreen(): takes no argument and doesn't return anything, it just changes the screen between the players.
	* AddLetterToWordFragment(): takes a character as argument and returns a string. 
	* CheckWordInDictionary(): takes a string as argument and returns true or false as return type (boolean expression).
	* UpDateHighScore(): takes a string as argument (winner's name) and doesn't return anything, it adds a point to the winner in the high score list.
	* UpDateSpinnerList(): takes a string as argument (new player name).
	* ChangeLanguage(): doesn't take any arguments and doesn't return anything.
	* ChangePlayerName(): takes a string as argument and returns a string.

## UI sketches

![My image](Cyrille2912.github.com/AppStudio/doc/IMG_3486.JPG)
![My image](Cyrille2912.github.com/AppStudio/doc/IMG_3488.JPG)
![My image](Cyrille2912.github.com/AppStudio/doc/IMG_3489.JPG)
![My image](Cyrille2912.github.com/AppStudio/doc/IMG_3490.JPG)
![My image](Cyrille2912.github.com/AppStudio/doc/IMG_3491.JPG)
![My image](Cyrille2912.github.com/AppStudio/doc/IMG_3493.JPG)

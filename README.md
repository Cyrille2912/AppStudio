# AppStudio

# The Ghost Game App for Android

The goal of this project is to implement the game of Ghost which is a two player wordgame for Android.

## Features

* Application follows the context and appearance provided by its platform, Android 5.
* Application starts with the home page where three buttons are accessible. 
  * The first button PLAY is used to play and go to the game's settings.
    * Once the user presses play on the first page, it redirects him to a second activity:
      * On this activity, the user is asked to enter the name of the two people playing and,
      * If they played the game before, a spinner is available where the user enters the names with a list of previous used names.
      * It is also possible to choose the language they want to use too, so English or Dutch.
      * Once the user has entered the names and the language, a button PLAY at the bottom of the screen is available to use.
        * When pressing PLAY button, a new activity starts where the players can play the game of Ghost:
          * From this point, the app will switch between two identic activities but each one used for a different player:
          * On these activities, the following elements will be displayed in a linear layout:
            * Current player's name at the top
            * The current word fragment 
            * An edit textview for the player to enter his letter. At this point, the app will ensure that the players enter 
            a valid single alphabetical character (case-insensitively) and ignore/warn if it isn't a valid character.
            * Once a valid letter is entered and the player pressed the button SUBMIT, the app will switch to the other activity 
            (with same layout but different player name) by showing a toast to warn the player that it switches players.
            and ask for the second player to enter a letter. The current word fragment will be updated when switching between players.
            * On these two activities, there is also a menu button available that is activated when clicking on the device's menu icon.
            There will be two options to the menu which are Restart round and settings. The first one restarts the previous activity
            and the second one opens the setting page which will be the same activity where players can change their names or language.
          * Once a player wins, a toast will be generated with the name of the winner and then a new activity will start, namely the
          one that shows the high scores.
          * The same is applied when a player loses.
  * The second button RULES to see the rules of the game and how to play it.
    * When RULES button is pressed, a new activity is started:
      * On it, a header named rules and a textview beneath it with the rules of the game and how to play it.
      * A scrollview is provided here in case the text is longer than the height of the layout.
  * The third button SCORES to see the scores of the game.
    * When SCORES button is pressed, another activity opens:
      * On it, a header High scores and,
      * The first 5 names that have won displayed in a numbered list with their score on the right.
      * Beneath this list, there is a button called New Game that opens the same activity as the button PLAY on the home page.

## UI sketches

![My image](Cyrille2912.github.com/AppStudio/doc/IMG_3470.JPG)
![My image](Cyrille2912.github.com/AppStudio/doc/IMG_3471.JPG)
![My image](Cyrille2912.github.com/AppStudio/doc/IMG_3472.JPG)
![My image](Cyrille2912.github.com/AppStudio/doc/IMG_3475.JPG)
![My image](Cyrille2912.github.com/AppStudio/doc/IMG_3474.JPG)

## Internal libraries

* android.widget.Spinner
* android.widget.TextView
* android.app.Activity
* android.os.Bundle
* android.view.View
* android.widget.Toast
* android.widget.Button
* android.widget.EditText
* android.widget.ScrollView
* android.view.Menu



  

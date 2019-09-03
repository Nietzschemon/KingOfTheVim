<h1>King of the Vim</h1>
Download here:

<h2>What:s</h2>

<h4> What is it?</h4>
It is a game based on Vim. It is meant both to teach people new to Vim aswell as to sharpen the skills of people that already are vim-literate. 
<div> <i>So it basically a tool of propaganda in the never ending holy war of the text-editors.</i> </div>

<h4> What does it contain?</h4>

* **Game-modes:** Several game-modes for learning and becoming better at vim. 
* **Tracking:** Score, time and step-tracking. 
* **Level Editor:** Make your own levels. It is just an other vim-mode!

<h4> What moves and modes does it contain? </h4>
Most of the basic moves that are hard for a beginner to master and for veterans not to get sloppy with. 
<div> <i>More will be added as the game evolves.</i></div>

* **Moves**
    * char (hjkl)
    * word (bwe)
    * WORD (BWE)
    * line (^0$)
    - find-move. (fF)   
    
* **Modes**
    * Replace (R, r)
    * delete (d)
    
* **Iteration**
    * Everything mentioned except f-move.
    
<h4> "What can I do to help out?"</h4>
All contributions are apprisiated. Make levels in the editor or give me some code to implement more vim-moves and -modes. 
<div>A project board will be up soon! </div>

<h2>How:s</h2>

<h4> "How do I play?"</h4>

1. Download the zip and unzip the whole thing to where you want to store it. 
2. Start "KingOfVim.jar" inside the folder. 
3. Press enter when the menu screen has appeared. 

<h4> How do I enter and exit the level editor?</h4>

1. Go to the main menu
2. Press F5 to enter the editor and F5 again to exit

<h4> How do I use the level editor?</h4>
The editor is used just like vim with an extra mode - called buildMode - and some editor-specific F-keys. 

<div>A level is built by modifing letters with buildMode so certain events are triggerd when the cursor hits them in the game. These triggers are called "LetterTypes" and are the back-bone of the game engine.</div>


* **Important notes**
    * Use **replace-mode** to enter your own text as insert-mode still dosnt work properly
    * **Save often**, as there is as of yet no undue-button.

<h4> Build mode</h4>
Build mode is entered from normal mode with TAB and exited with ESC. 
Char-moves work in this mode, but to do word-moves shift needs to be held down. This holds true for other moves as well.
      
<h5> Mode specific keys </h5>
      Pressing the same key again cycles through LetterTypes.
      
      w - white, white_green 
      r - red  
      b - black 
      y - yellow
      e - empty (clear cell)
      
<h5> Editor specific keys</h5>
  
      F1 - saves the current matrix to a new save 
      F1 + shift - overwrites the current save with the current matrix
      F3 - cycles backgroundtext
      F4 - sets cursor spawning point for the level
      F5 - back to main menu
      F7 - load previous save
      F8 - load next save
      F10 - opens dialog for setting level mecanics (gravity, win-condition etc.)
      F11 - gravity in editor (switch)
      F12 - testMode (switch)

<h2> Where:s</h2>

<h4> Where are my stats?</h4>
As soon as you start playing a .csv-file is created in your game folder. (In game stats and high-scores are coming very soon).

<h4> Where are my levels?</h4>
They are in yourPath/gameFolder/levels/builder/

<h4> Where is my favorite move?</h4>
Make a poll request, and I shall try to add it. If you want to write it yourself feel free. I will add it. 
<div>Priority is given to what ever adds most to the game for the given moment.</div>

# King of the Vim

## What:s 

#### What is it?
It is a game based on Vim. It is meant both to teach people new to Vim aswell as to sharpen the skills of people that already are vim-literate. <div> _So it basically a tool of propaganda in the never ending holy war of the text-editors._ </div>

#### What does it contain?
* **Game-modes:** Several game-modes for learning and becoming better at vim. 
* **Tracking:** Score, time and step-tracking. 
* **Level Editor:** Make your own levels. It is just an other vim-mode!

#### What moves and modes does it contain? 
Most of the basic moves that are hard for a beginner to master and for veterans not to get sloppy with. <div> _More will be added as the game evolves._</div>

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
    
#### "What can I do to help out?"
All contributions are apprisiated. Make levels in the editor or give me some code to implement more vim-moves and -modes. 
<div>A project board will be up soon! </div>

## How:s

#### "How do I play?"
1. Download the zip and unzip the whole thing to where you want to store it. 
2. Start "KingOfVim.jar" inside the folder. 
3. Press enter when the menu screen has appeared. 

#### How do I enter and exit the level editor?
1. Go to the main menu
2. Press F5 to enter the editor and F5 again to exit

#### How do I use the level editor?
The editor is used just like vim with an extra mode - called buildMode - and some editor-specific F-keys. 
<div>A level is built by modifing letters with buildMode so certain events are triggerd when the cursor hits them in the game.
These triggers are called "LetterTypes" and are the back-bone of the game engine.  </div>


* **Important notes**
    * Use **replace-mode** to enter your own text as insert-mode still dosnt work properly
    * **Save often**, as there is as of yet no undue-button.

#### Build mode
Build mode is entered from normal mode with TAB and exited with ESC. 
Char-moves work in this mode, but to do word-moves shift needs to be held down. This holds true for other moves as well.
      
##### Mode specific keys 
      Pressing the same key again cycles through LetterTypes.
      
      w - white, white_green 
      r - red  
      b - black 
      y - yellow
      e - empty (clear cell)
      
##### Editor specific keys
  
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

## Where:s

#### Where are my stats?
As soon as you start playing a .csv-file is created in your game folder. (In game stats and high-scores are coming very soon).

#### Where are my levels?
They are in yourPath/gameFolder/levels/builder/

#### Where is my favorite move?
Make a poll request, and I shall try to add it. If you want to write it yourself feel free. I will add it. 
<div>Priority is given to what ever adds most to the game for the given moment.</div>

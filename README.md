
# CHALLENGE - CLASSIC MINIGAMES


The "Challenge" project is a little project who is made by some students
of the UCL (Universite Catholique de Louvain) in Belgium. We began this 
project to train us in the long way of the java programmation. 
So the project was basically created for fun and learning.

Currently, the project contains four differents games.
First one is a Hangman Game which is fully functionnal with one and 
two players modes. A french dictionnary is currently present for single
player mode.
Second is a HighLow game. The principe is really simple, you need to find
a number automatically taked by the computer (or by a friend).
Third is a Mastermind game. With 3 difficulty mode, rediscover the joy
of this classic game !
Fourth is a connect 4 game with 1 and 2 players mode too, like the others.
However the project is still in development and we will add some other games.

We are currently in developpement of a graphical user interface (GUI) to make
things right. The console version is still active thought and the user have actually
the possibility, at the start of the program, to chose between GUI and console.

As other features, we have a profile user system that can saves the score and
preference into two files in the saves repertories.

We have also a soundplayer that can play musics of ".wav" format.
It is possible for the user to add his own musics in wav format into the
"sound" folder, in the "res" repertorie. To make it good, it is also
necessary to add the filename of the music into the file "mus.ls".

You can help if you want but remember that we work on this project only
for fun, don't take it too seriously.


## TECHNICAL DETAILS

PROJECT TITLE: Challenge - Classics games

PURPOSE OF PROJECT: Create little games for fun :D

VERSION or DATE: 1.0 gamma

LANGUAGE : FRENCH - ENGLISH (still in translating)

LAUNCH CONDITION : The game DON'T have a gui for a all the feature (graphical user interface).
                   For now, you must open it in a terminal
                   
To run this program, you need **Java 7** or more installed on your computer
                   
#### List of contributors

* THE GREAT INQUISITOR: vinsifroid
* THE FINDER OF CLUES: Bivisi
* THE MASTER OF THE DICTIONNARY: melvinmajor
* OCCASIONNAL CONTRIBUTOR : Lazyboy007
* THE MUSE : OrvalInspiration

## HOW TO START THIS PROJECT

#### USER INSTRUCTIONS
1. Download all files of the project.
   Begin by downloading all the files of the project. 
   You have 2 options :
    - Download the most recent source code. 
      It contains the most advanced features of the games but 
      there is a risk of bugs
    - Download the source code from one of the few releases of the program. 
      They are considered as "stable version" 
      and they contains (most likely) not any bugs
2. Unzip the Archive in the folder of your choice
   tip : in the versions **After** the release 1.0 gamma, you need to create yourself
        a folder named "saves" in the current folder. 
        We gonna try to fix this in the future.
3. Compile the source on your computer.
   To do that, use the command : javac "filename"
     * Windows Users : open a cmd windows (execute -> cmd) and go to
                    the installation folder with "cd "pathname" " command.
     * Linux Users : open the terminal with ctrl+t and tape the command
                  `$javac <...>`
                  with the <...> that is the file to compile
                  
    **Compilate all the .java files, NOT the others !**
  
## HOW TO 'INSTALL' THE PROGRAM

It is not possible to 'install' the program. If you chose the version v1.0 gamma,
there still no possibilities to install properly the program.
If you use now the last versions of the source and if you have jdk7/ANT installed,
you can yourself use the command :
```shell
  $ ant
```
in you favourite command prompt/shell (depends of your Operating System)
It will automatically compile your sources et create a executable jar that
you can use with
```shell
  $ java -jar Challenge.jar
```
or use the shell script (if are on a UNIX-like system) launch.sh

## LIST OF AVAILABLE GAMES

Here is the list of all current available games:
* Pendu (= Hangman).
* Plus ou moins (= Highlow).
* MasterMind
* Puissance 4 (Connect 4)

## LIST OF FEATURES

There is a few important features that are currently implemented in the project

- A profile system manager
Every user can create a 'profile' that save every score they do in the games and
save every parametre like the favourite language, the number of player... 
and so forth

- A sound player
There is a sound player in the program that can play .wav files listed
in a text file.

- A Graphical User Interface (GUI) - Still in dev
We are actually developping a graphical interface for the program. But there still
need to be implemented in various places of the program

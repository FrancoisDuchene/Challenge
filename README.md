
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
The musics are customizable by the user, for more information read below.

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

* THE GREAT INQUISITOR: FrancoisDuchene
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
      NB : After the delta version, two compressed archive are able
           a .jar excutable who is normally excutable by the user
           and a regular archive
           Choose the regular archive if you just want to contribute to the project and
           the jar either way.
2. Unzip the Archive in the folder of your choice
    
    NB : If at the precedent step, you chose the .jar, you will just have to decompress yourself two folders
         from the jar to be able to use the app, the folder named *saves* and *res*
  
3. For 'install' the program

**Don't use these methods if you dowloaded the .jar, it's already done for use**

- Use ANT directly
It is not possible to 'install' the program. If you chose the version v1.0 delta,
there still no possibilities to install properly the program.
If you use now the last versions of the source and if you have jdk7/ANT (from the Apache foundation project) installed,
you can yourself use the command :
```shell
  $ ant
```
- Use the script install.sh
For UNIX-Type system users, a script wrote in Bash is already done, you can use it with the command below (`ctrl+T`)
```shell
  $ bash install.sh
```
It will create you .jar excutable archive

#### TO LAUNCH THE PROGRAM

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

#### HOW TO ADD MUSICS TO THE SOUND PLAYER

The music player is customizable in the sens that the user can, if he want, add his own musicsto the "playlist". 
As the SoundPlayer play music in a random order, the music chooses by the user can be played at anytimes.

The file format of the musics is _.wav **only**_

######Instructions :
- Copy-paste your music in the folder res/sound/ `cd res/sound/`
- Copy-paste the name of the music **Without the extension** 
  | ex: "yourMusic" and **Not** "yourMusic.wav"
- Go to the folder res/data/ `cd ..` and `cd data`
- open 'mus.ls' with an editor, paste to a new line of the file   _Be careful to not let a carriage letter between the last      song and the end of the file

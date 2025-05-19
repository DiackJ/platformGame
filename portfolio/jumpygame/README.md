# Platform Game

## Table of Contents

- [Project description](#description)

- [Why I built it](#why-i-built-it)

- [How to run it locally](#how-to-run)

- [Tech stack and features](#stack--features)

- [What I learned by building this project](#what-I-learned)

- [Next steps for further development](#next-steps)

## Description:

This project is a simple GUI-based game that allows players to move a little pink sprite throughout 3 different levels.
Each level varies in difficulty (e.g. easy, medium, difficult) and the goal of the player is to go through each level collecting
as many coins as they can. 

## Why I Built It:

I wanted to try doing something that was a little different than my usual API development, so after being inspired by a fellow
developer's journey, I decided a simple platform game could be fun to build. After designing the 3 levels I wanted to include,
I began researching tools and libraries and began building this fun project.

## How To Run:

**1. Clone the repository** 
>git clone https://github.com.my-username/jumpygame.git
> 
**2. Compile and Run program** 

- navigate to
  >\jumpygame\src\main\java\org\jumpygame
- run the command 
  >javac BeamThread.java EndPanel.java GameFrame.java LevelOnePanel.java LevelThreePanel.java LevelTwoPanel.java Main.java Sprite.java StartPanel.java
- navigate back to the root directory 
  >\jumpygame
- run the program
  >java -cp src/main/java com.jumpygame.Main 
  > 
 - or if using a tool like Intellij, navigate to the *Main* file and run using the play symbol. 


## Stack & Features:

**Tech Stack**
- Java
- Java Swing Library

**Why Swing**

I chose to use Java's built-in Swing library for its easy accessibility, requiring no external dependencies, and for its user-friendly
syntax that I personally didn't find in other options like JavaFX for example. 

**Features**

This application includes a few fun features such as: 

- A fully functioning GUI for game play.
- Three distinct levels ranging in difficulty (easy, medium, hard).
- A sprite for the user to control and move throughout the game.
- Point system that gets updated based on the number of coins collected.

## What I Learned:

By building this project I learned a lot of new things including: 

- Learned how to use **Java's Swing** library to build a GUI application. This includes how to build frames, panels, and components such 
  as the sprite and platforms.
- Learned how to add **key presses** as user input so the user can move the sprite using arrow keys.
- Learned how to implement **gravity and velocity** so the sprite can properly jump and fall.
- Learned how to implement **collision detection** so that the sprite bumps into walls and is able to land and move on the 
  different platforms.
- Implemented **threading** for the animation that moves the pillar up and down on the final level.

## Next Steps

Some additional features and fixes that I want to implement include:

- Add a few more levels with harder courses to increase user engagement and play time.
- Fix the end screen so the user can properly replay or exit.
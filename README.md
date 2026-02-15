# Kniffel (Yahtzee) - Java Swing Application

![Java](https://img.shields.io/badge/Java-17%2B-orange?style=for-the-badge&logo=openjdk)
![GUI](https://img.shields.io/badge/GUI-Swing-blue?style=for-the-badge)
![Architecture](https://img.shields.io/badge/Architecture-OOP-green?style=for-the-badge)

## About The Project

This project is a fully functional implementation of the popular dice game **Kniffel (Yahtzee)**, built with Java.

It demonstrates strong **Object-Oriented Programming (OOP)** principles by separating game logic, rule validation, and user interface. The project features a dual-interface approach: the core logic powers both a classic **Console Application** and a graphical **Desktop GUI** built with Java Swing.

### Key Features
* **Graphical User Interface (GUI):** Interactive window with buttons and visual dice representation using `javax.swing`.
* **Complete Ruleset:** Implements all standard Kniffel rules (Full House, Straights, Kniffel, Bonus calculations).
* **Rule Engine:** A dedicated static utility class (`KniffelRules`) handles complex score validation independently of the UI.
* **State Management:** Holds logic for re-rolling specific dice and tracking game rounds.

---

## Architecture

The code is organized into a modular package structure in `src/kniffel/`.

```text
src/kniffel/
├── Dice.java           # Model: Manages the state of the 5 dice (values, hold status).
├── Scoreboard.java     # Model: Tracks scores for all 13 categories (Upper/Lower section).
├── KniffelRules.java   # Logic: Static Utility class to calculate points (e.g., checks for Full House).
├── KniffelGame.java    # Controller (Console): Runs the text-based version of the game.
└── KniffelVisual.java  # View/Controller (GUI): Runs the Swing-based graphical version.
```

---

## Technical Breakdown

* Encapsulation: The Dice class encapsulates the random number generation and the "hold" mechanism, protecting the internal array state.

* Static Logic: KniffelRules contains purely functional static methods (isFullHouse, calculateSum), making the logic testable and reusable for both the Console and GUI versions.

* Event Handling: The GUI utilizes ActionListener inner classes to handle user inputs like rolling dice or selecting categories.

---

## How to Run

Prerequisites

  * Java Development Kit (JDK) 17 or higher installed.

Installation & Execution

  1. Clone the repository

  ```bash
  git clone [https://github.com/mlrlouis/Kniffel.git](https://github.com/mlrlouis/Kniffel.git)
  ```
      
  2. Navigate to the project directory

  ```bash
  cd Kniffel
  ```

  3. Compile the source code
  
  ```bash
  javac -d bin src/kniffel/*.java
  ```
    
  6. Run the Game

      * To run the GUI Version (Recommended):
        ```bash
        java -cp bin kniffel.KniffelVisual
        ```

      * To run the Console Version:
        ```bash
        java -cp bin kniffel.KniffelGame
        ```

---

## Controls (GUI)

  1. Roll Dice: Click to roll. You have 3 rolls per round.

  2. Hold Dice: Click "Würfel behalten" and enter the numbers (1-5) of the dice you want to keep (e.g., "1 4 5").

  3. Select Category: Choose a scoring category from the dropdown menu and click "Kategorie auswählen" to lock in your score.

<p align="right">(<a href="#top">back to top</a>)</p>

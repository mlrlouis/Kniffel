package kniffel;

import java.util.Scanner;


public class KniffelGame {
	
    private final Dice dice; // Klasse zur Verwaltung der Würfel
    private final Scoreboard scoreboard; // Klasse zur Verwaltung der Punktetafel
    private final Scanner scanner; // Eingabescanner

    // Konstruktor initialisiert die Hauptkomponenten des Spiels
    public KniffelGame() {
        dice = new Dice(5); // 5 Würfel werden verwendet
        scoreboard = new Scoreboard();
        scanner = new Scanner(System.in);
    }

    /**
     * Methode, die den gesamten Spielablauf steuert.
     */
    public void playGame() {
        System.out.println("Willkommen bei Kniffel!");
        System.out.println("Spielregeln: Du hast 13 Runden. Jede Runde kannst du bis zu 3 Mal würfeln.");
        System.out.println("Wähle am Ende eine Kategorie, um Punkte zu sammeln.\n");

        for (int round = 1; round <= 13; round++) {
            System.out.println("=== Runde " + round + " ===");

            dice.reset(); // Zurücksetzen der Würfel vor jeder Runde

            for (int roll = 1; roll <= 3; roll++) {
                dice.rollDice(); // Würfeln der nicht festgehaltenen Würfel
                dice.displayDice(); // Anzeige der aktuellen Würfelwerte

                if (roll < 3) { // Spieler kann Würfel festhalten, wenn es noch weitere Würfe gibt
                    System.out.println("Welche Würfel möchtest du behalten? Gib die Nummern ein (z.B. '1 3 4') oder drücke Enter, um alle neu zu würfeln.");
                    String input = scanner.nextLine();
                    dice.holdDice(input); // Verarbeitung der Eingabe für das Festhalten der Würfel
                }
            }

            System.out.println("Endgültige Würfelkombination: ");
            dice.displayDice();

            System.out.println("\nWähle eine Kategorie zum Eintragen der Punkte:");
            scoreboard.displayAvailableCategories(); // Anzeige der verfügbaren Kategorien
            String choice = scanner.nextLine();

            int score = calculateScore(dice.getValues(), choice); // Punktberechnung für die gewählte Kategorie
            scoreboard.updateScore(choice, score); // Aktualisieren der Punktetafel

            System.out.println("Deine bisherige Punktzahl: " + scoreboard.getTotalScore());
        }

        System.out.println("\nSpiel beendet!");
        System.out.println("Gesamtpunktzahl: " + scoreboard.getTotalScore());
    }

    /**
     * Methode zur Punktberechnung basierend auf der Würfelkombination und der Kategorie.
     *
     * @param dice     Array der aktuellen Würfelwerte
     * @param category Gewählte Kategorie
     * @return Berechnete Punkte
     */
    private int calculateScore(int[] dice, String category) {
        switch (category.toLowerCase()) {
            case "1er":
                return KniffelRules.sumOfNumber(dice, 1);
            case "2er":
                return KniffelRules.sumOfNumber(dice, 2);
            case "3er":
                return KniffelRules.sumOfNumber(dice, 3);
            case "4er":
                return KniffelRules.sumOfNumber(dice, 4);
            case "5er":
                return KniffelRules.sumOfNumber(dice, 5);
            case "6er":
                return KniffelRules.sumOfNumber(dice, 6);
            case "dreierpasch":
                return KniffelRules.hasXOfKind(dice, 3) ? KniffelRules.sumAllDigits(dice) : 0;
            case "viererpasch":
                return KniffelRules.hasXOfKind(dice, 4) ? KniffelRules.sumAllDigits(dice) : 0;
            case "full house":
                return KniffelRules.isFullHouse(dice) ? 25 : 0;
            case "kleine straße":
                return KniffelRules.isSmallStraight(dice) ? 30 : 0;
            case "große straße":
                return KniffelRules.isLargeStraight(dice) ? 40 : 0;
            case "kniffel":
                return KniffelRules.isKniffel(dice) ? 50 : 0;
            case "chance":
                return KniffelRules.sumAllDigits(dice);
            default:
                System.out.println("Ungültige Kategorie. Es werden 0 Punkte eingetragen!");
                return 0;
        }
    }

    public static void main(String[] args) {
        KniffelGame game = new KniffelGame();
        game.playGame(); // Start des Spiels
    }
}
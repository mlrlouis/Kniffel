package kniffel;

import java.util.Random;

public class Dice {
	
    private final int[] values; // Aktuelle Würfelwerte
    private final boolean[] holds; // Status der festgehaltenen Würfel
    private final Random random; // Zufallsgenerator für Würfelwürfe

    public Dice(int numberOfDice) {
        values = new int[numberOfDice];
        holds = new boolean[numberOfDice];
        random = new Random();
    }

    /**
     * Setzt den Status aller Würfel auf "nicht gehalten" zurück.
     */
    public void reset() {
        for (int i = 0; i < holds.length; i++) {
            holds[i] = false;
        }
    }

    /*
     * Würfelt alle Würfel, die nicht festgehalten sind.
     */
    public void rollDice() {
        for (int i = 0; i < values.length; i++) {
            if (!holds[i]) {
                values[i] = random.nextInt(6) + 1; // Werte zwischen 1 und 6
            }
        }
    }

    /**
     * Verarbeitet die Eingabe des Spielers und setzt die festgehaltenen Würfel entsprechend.
     *
     * @param input Eingabe des Spielers (z. B. "1 3 4")
     */
    public void holdDice(String input) {
        reset(); // Alle Würfel freigeben, um neue Auswahl zu ermöglichen
        if (input.isEmpty()) {
            return; // Alle Würfel werden behalten
        }

        String[] parts = input.split(" ");
        for (String part : parts) {
            try {
                int index = Integer.parseInt(part) - 1; // Spieler gibt 1-basierten Index an
                if (index >= 0 && index < values.length) {
                    holds[index] = true; // Würfel wird festgehalten
                }
            } catch (NumberFormatException ignore) {
                // Ignoriere ungültige Eingaben
            }
        }
    }


    /**
     * Gibt die aktuellen Werte der Würfel aus.
     */
    public void displayDice() {
        for (int i = 0; i < values.length; i++) {
            System.out.println("Würfel " + (i + 1) + ": " + values[i]);
        }
    }

    public int[] getValues() {
        return values;
    }
}
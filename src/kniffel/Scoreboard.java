package kniffel;

public class Scoreboard {
    private final String[] categories = {
            "1er", "2er", "3er", "4er", "5er", "6er",
            "Dreierpasch", "Viererpasch", "Full House",
            "Kleine Straße", "Große Straße", "Kniffel", "Chance"};
    
    private final int[] scores; // Speichert die Punkte für jede Kategorie

    public Scoreboard() {
        scores = new int[categories.length];
        for (int i = 0; i < scores.length; i++) {
            scores[i] = -1; // -1 bedeutet, dass Kategorie noch nicht belegt wurde
        }
    }

    /**
     * Zeigt die verfügbaren Kategorien an.
     */
    public void displayAvailableCategories() {
        System.out.println("Verfügbare Kategorien: ");
        for (int i = 0; i < categories.length; i++) {
            if (scores[i] == -1) { // Nur nicht belegte Kategorien anzeigen
                System.out.println((i + 1) + ". " + categories[i]);
            }
        }
    }

    /**
     * Aktualisiert die Punktzahl für eine Kategorie.
     *
     * @param category Kategorie, die belegt werden soll
     * @param score    Punkte für diese Kategorie
     */
    public void updateScore(String category, int score) {
        int index = getCategoryIndex(category);
        if (index != -1 && scores[index] == -1) {
            scores[index] = score;
            System.out.println("Kategorie " + categories[index] + " wurde mit " + score + " Punkten belegt");
        } else {
            System.out.println("Ungültige Kategorie oder bereits belegt");
        }
    }

    public int getTotalScore() {
        int total = 0;
        for (int score : scores) {
            if (score != -1) { // Nur belegte Kategorien zählen
                total += score;
            }
        }
        return total;
    }

    private int getCategoryIndex(String category) {
        for (int i = 0; i < categories.length; i++) {
            if (categories[i].equalsIgnoreCase(category)) {
                return i;
            }
        }
        return -1; // Kategorie nicht gefunden
    }
    
    public String[] getCategories() {
        return categories;
    }
}
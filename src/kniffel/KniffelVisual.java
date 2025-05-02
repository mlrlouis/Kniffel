package kniffel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KniffelVisual extends JFrame {
    private final Dice dice;
    private final Scoreboard scoreboard;
    private final JLabel[] diceLabels;
    private final JComboBox<String> categoryComboBox;
    private final JTextArea messageArea;
    private int rollsLeft = 3;

    public KniffelVisual() {
        dice = new Dice(5); // 5 Würfel
        scoreboard = new Scoreboard();

        // Fensterkonfiguration
        setTitle("Kniffel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(600, 400);

        // Obere Anzeige: Würfel
        JPanel dicePanel = new JPanel();
        dicePanel.setLayout(new GridLayout(1, 5));
        diceLabels = new JLabel[5];
        for (int i = 0; i < 5; i++) {
            diceLabels[i] = new JLabel("🎲", SwingConstants.CENTER);
            diceLabels[i].setFont(new Font("Arial", Font.PLAIN, 36));
            dicePanel.add(diceLabels[i]);
        }
        add(dicePanel, BorderLayout.NORTH);

        // Mittlere Anzeige: Nachrichten und Aktionen
        messageArea = new JTextArea("Willkommen bei Kniffel!\nDrücke \"Würfeln\", um zu starten.");
        messageArea.setEditable(false);
        add(new JScrollPane(messageArea), BorderLayout.CENTER);

        // Untere Anzeige: Buttons und Auswahl
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        JButton rollButton = new JButton("Würfeln");
        rollButton.addActionListener(new RollDiceAction());
        controlPanel.add(rollButton);

        categoryComboBox = new JComboBox<>(scoreboard.getCategories());
        controlPanel.add(categoryComboBox);

        JButton selectCategoryButton = new JButton("Kategorie auswählen");
        selectCategoryButton.addActionListener(new SelectCategoryAction());
        controlPanel.add(selectCategoryButton);

        JButton holdButton = new JButton("Würfel behalten");
        holdButton.addActionListener(new HoldDiceAction());
        controlPanel.add(holdButton);

        add(controlPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Aktion für den "Würfeln"-Button
    private class RollDiceAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (rollsLeft > 0) {
                dice.rollDice();
                rollsLeft--;
                updateDiceDisplay();
                messageArea.append("\nDu hast " + rollsLeft + " Würfe übrig.");
            } else {
                messageArea.append("\nKeine Würfe mehr in dieser Runde! Wähle eine Kategorie.");
            }
        }
    }

    // Aktion für den "Kategorie Festlegen"-Button
    private class SelectCategoryAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedCategory = (String) categoryComboBox.getSelectedItem();
            if (selectedCategory != null) {
                int score = calculateScore(dice.getValues(), selectedCategory);
                scoreboard.updateScore(selectedCategory, score);
                messageArea.append("\nKategorie \"" + selectedCategory + "\" wurde festgelegt mit " + score + " Punkten.");
                resetRound();
            }
        }
    }

    // Aktion für den "Würfel Behalten"-Button
    private class HoldDiceAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = JOptionPane.showInputDialog("Gib die Nummern der Würfel ein, die du behalten möchtest (z.B. '1 3 4').");
            if (input != null) {
                dice.holdDice(input); // Halte die Würfel basierend auf der Eingabe
                updateDiceDisplay(); // Aktualisiere die Anzeige der Würfel
                messageArea.append("\nWürfel behalten: " + input);
            }
        }
    }

    // Methode zur Aktualisierung der Würfelanzeige
    private void updateDiceDisplay() {
        int[] values = dice.getValues();
        for (int i = 0; i < values.length; i++) {
            diceLabels[i].setText(String.valueOf(values[i]));
        }
    }

    // Methode zur Berechnung der Punkte
    private int calculateScore(int[] diceValues, String category) {
        switch (category.toLowerCase()) {
            case "1er":
                return KniffelRules.sumOfNumber(diceValues, 1);
            case "2er":
                return KniffelRules.sumOfNumber(diceValues, 2);
            case "3er":
                return KniffelRules.sumOfNumber(diceValues, 3);
            case "4er":
                return KniffelRules.sumOfNumber(diceValues, 4);
            case "5er":
                return KniffelRules.sumOfNumber(diceValues, 5);
            case "6er":
                return KniffelRules.sumOfNumber(diceValues, 6);
            case "dreierpasch":
                return KniffelRules.hasXOfKind(diceValues, 3) ? KniffelRules.sumAllDigits(diceValues) : 0;
            case "viererpasch":
                return KniffelRules.hasXOfKind(diceValues, 4) ? KniffelRules.sumAllDigits(diceValues) : 0;
            case "full house":
                return KniffelRules.isFullHouse(diceValues) ? 25 : 0;
            case "kleine straße":
                return KniffelRules.isSmallStraight(diceValues) ? 30 : 0;
            case "große straße":
                return KniffelRules.isLargeStraight(diceValues) ? 40 : 0;
            case "kniffel":
                return KniffelRules.isKniffel(diceValues) ? 50 : 0;
            case "chance":
                return KniffelRules.sumAllDigits(diceValues);
            default:
                messageArea.append("\nUngültige Kategorie. Es werden 0 Punkte eingetragen.");
                return 0;
        }
    }

    // Methode zum Zurücksetzen der Runde
    private void resetRound() {
        rollsLeft = 3;
        dice.reset();
        updateDiceDisplay();
        messageArea.append("\nNeue Runde gestartet!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(KniffelVisual::new);
    }
}

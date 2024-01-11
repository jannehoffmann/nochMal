package nochMal.model;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;
import java.io.File;
import java.util.Scanner;

/**
 * Diese Klasse stellt das Model von NochMal dar.
 * Sie beinhaltet die komplette Logik des Spiels.
 */
public class Model {
    private Field[][] fieldsOne;
    private Field[][] fieldsTwo;
    private Column[] columnsOne;
    private Column[] columnsTwo;
    private int pointsPlayerOne;
    private int pointsPlayerTwo;
    private GameState gameState;
    private Color[] colorDices;
    private int[] numberDices;
    private int selectedColorDice;
    private int selectedNumberDice;
    private int remainingMoves;
    private ArrayList<Field> fieldsOfThisMove;
    private boolean invalidField;

    /**
     * Der Konstruktor für die Klasse Model.
     * Initialisiert die Spielfelder und Würfel für ein neues Spiel.
     *
     * - `fieldsOne` und `fieldsTwo` sind zweidimensionale Arrays, die die Spielfelder für zwei Spieler darstellen.
     * - `columnsOne` und `columnsTwo` sind Arrays, die die Spalten der Spielfelder repräsentieren.
     * - `colorDices` ist ein Array für die Farbwürfel.
     * - `numberDices` ist ein Array für die Nummernwürfel.
     * - `fieldsOfThisMove` ist eine Liste, die die Felder des aktuellen Zuges speichert.
     * - `selectedColorDice` und `selectedNumberDice` werden initialisiert, um anzuzeigen, dass noch kein Würfel ausgewählt wurde.
     * - `invalidField` wird auf false gesetzt, was bedeutet, dass aktuell kein ungültiges Feld markiert ist.
     * - `startNewGame()` wird aufgerufen, um das Spiel zu starten.
     */
    public Model() {
        this.fieldsOne = new Field[15][7];
        this.fieldsTwo = new Field[15][7];
        this.columnsOne = new Column[15];
        this.columnsTwo = new Column[15];
        this.colorDices = new Color[3];
        this.numberDices = new int[3];
        this.fieldsOfThisMove = new ArrayList<Field>();
        this.selectedColorDice = -1;
        this.selectedNumberDice = -1;
        this.invalidField = false;
        startNewGame();
    }

    private void startNewGame() {
        //erzeuge Thread, der Spielfelder aus Textdatei einliest und Spalten erzeugt
        Thread t = new Thread(() -> {
            try {
                File file = new File("fields.txt");
                Scanner scanner = new Scanner(file);
                int color;
                for(int i = 0; i < 15; i++) {
                    for(int j = 0; j < 7; j++) {
                        color = scanner.nextInt();
                        switch(color) {
                            case 1:
                                fieldsOne[i][j] = new Field(Color.GREEN, i, j);
                                fieldsTwo[i][j] = new Field(Color.GREEN, i, j);
                                break;
                            case 2:
                                fieldsOne[i][j] = new Field(Color.YELLOW, i, j);
                                fieldsTwo[i][j] = new Field(Color.YELLOW, i, j);
                                break;
                            case 3:
                                fieldsOne[i][j] = new Field(Color.BLUE, i, j);
                                fieldsTwo[i][j] = new Field(Color.BLUE, i, j);
                                break;
                            case 4:
                                fieldsOne[i][j] = new Field(Color.RED, i, j);
                                fieldsTwo[i][j] = new Field(Color.RED, i, j);
                                break;
                            case 5:
                                fieldsOne[i][j] = new Field(Color.ORANGE, i, j);
                                fieldsTwo[i][j] = new Field(Color.ORANGE, i, j);
                                break;
                        }
                    }
                    scanner.nextLine();
                    //erstelle die Spalten mit Punkten
                    switch (i) {
                        case 0, 14:
                            columnsOne[i] = new Column(i, 5, 3);
                            columnsTwo[i] = new Column(i, 5, 3);
                            break;
                        case 1, 2, 3, 11, 12, 13:
                            columnsOne[i] = new Column(i, 3, 2);
                            columnsTwo[i] = new Column(i, 3, 2);
                            break;
                        case 4, 5, 6, 8, 9, 10:
                            columnsOne[i] = new Column(i, 2, 1);
                            columnsTwo[i] = new Column(i, 2, 1);
                            break;
                        case 7:
                            columnsOne[i] = new Column(i, 1, 0);
                            columnsTwo[i] = new Column(i, 1, 0);
                            break;
                    }
                }
                scanner.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        //starte Thread
        t.start();

        //fülle Würfel mit initialen Werten
        Arrays.fill(colorDices, Color.WHITE);
        Arrays.fill(numberDices, 0);

        //starte Spiel
        gameState = GameState.ONEROLLTHEDICE;
        System.out.println("New Game started");
    }

    /**
     * Würfelt die Farb- und Nummernwürfel für das Spiel.
     *
     * Für jeden Farbwürfel in `colorDices` wird eine Zufallszahl zwischen 1 und 5 generiert,
     * die einer bestimmten Farbe entspricht (1 für Grün, 2 für Gelb, usw.).
     * Anschließend wird für jeden Nummernwürfel in `numberDices` eine Zufallszahl zwischen 1 und 5 gewürfelt.
     *
     * Die Ergebnisse des Würfelns werden auf der Konsole ausgegeben.
     */
    public void rollTheDice() {
        for(int i = 0; i < colorDices.length; i++) {
            Random random = new Random();
            int randomNumber = random.nextInt(5) + 1;
            switch(randomNumber) {
                case 1:
                    colorDices[i] = Color.GREEN;
                    break;
                case 2:
                    colorDices[i] = Color.YELLOW;
                    break;
                case 3:
                    colorDices[i] = Color.BLUE;
                    break;
                case 4:
                    colorDices[i] = Color.RED;
                    break;
                case 5:
                    colorDices[i] = Color.ORANGE;
                    break;
            }
        }

        for(int i = 0; i < numberDices.length; i++) {
            Random random = new Random();
            numberDices[i] = random.nextInt(5) + 1;
        }

        System.out.println("Roll the Dice:\nColorDices:");
        for(int i = 0; i < colorDices.length; i++) {
            System.out.println(i + ": " + colorDices[i]);
        }

        System.out.println("NumberDices:");
        for(int i = 0; i < numberDices.length; i++) {
            System.out.println(i + ": " + numberDices[i]);
        }
    }

    /**
     * Wählt einen Farbwürfel basierend auf dem übergebenen Index aus.
     *
     * @param dice Der Index des auszuwählenden Farbwürfels, sollte zwischen 0 und 2 liegen.
     * @return true, wenn die Auswahl erfolgreich war, false, wenn der Index ungültig ist.
     * Wenn ein gültiger Würfel ausgewählt wird, wird `selectedColorDice` auf den Index des Würfels gesetzt,
     * und `invalidField` wird auf false gesetzt. Bei einer ungültigen Eingabe wird eine Fehlermeldung
     * ausgegeben und `invalidField` wird auf true gesetzt.
     */
    public boolean selectAColorDice(int dice) {
        if(0 <= dice && dice <= 2) {
            selectedColorDice = dice;
            System.out.println("Color: " + selectedColorDice + " selected");
            invalidField = false;
            return true;
        } else {
            System.out.println("Ungültige Eingabe");
            invalidField = true;
            return false;
        }
    }

    /**
     * Wählt einen Nummernwürfel basierend auf dem übergebenen Index aus.
     *
     * @param dice Der Index des auszuwählenden Nummernwürfels, sollte zwischen 0 und 2 liegen.
     * @return true, wenn die Auswahl erfolgreich war, false, wenn der Index ungültig ist.
     * Wenn ein gültiger Würfel ausgewählt wird, wird `selectedNumberDice` auf den Index des Würfels gesetzt,
     * `remainingMoves` auf den Wert des Würfels aktualisiert, und `invalidField` wird auf false gesetzt.
     * Bei einer ungültigen Eingabe wird eine Fehlermeldung ausgegeben und `invalidField` wird auf true gesetzt.
     */
    public boolean selectANumberDice(int dice) {
        if(0 <= dice && dice <= 2) {
            selectedNumberDice = dice;
            remainingMoves = numberDices[dice];
            System.out.println("Number: " + selectedNumberDice + " selected");
            invalidField = false;
            return true;
        } else {
            System.out.println("Ungültige Eingabe");
            invalidField = true;
            return false;
        }
    }

    /**
     * Wählt ein Feld basierend auf der angegebenen Spalte und Zeile aus.
     *
     * @param column Die Spaltennummer des auszuwählenden Feldes.
     * @param row Die Zeilennummer des auszuwählenden Feldes.
     * @param fields Ein zweidimensionales Array von Feld-Objekten, die das Spielfeld von Spieler 1 oder Spieler 2 darstellen.
     * @return true, wenn das Feld erfolgreich ausgewählt wurde, false, wenn die Auswahl ungültig ist.
     *
     * Die Methode überprüft zunächst, ob die angegebenen Spalten- und Zeilennummern innerhalb der Grenzen des Spielfelds liegen.
     * Dann wird überprüft, ob die Farbe des Feldes mit der Farbe des ausgewählten Farbwürfels übereinstimmt.
     *
     * Für das erste Feld eines Zuges:
     * - Das Feld muss in Spalte 7 liegen oder muss an ein bereits belegtes Feld angrenzen.
     *
     * Für nachfolgende Felder eines Zuges:
     * - Das Feld muss direkt an ein zuvor im aktuellen Zug ausgewähltes Feld angrenzen.
     *
     * Wenn das Feld die Bedingungen erfüllt, wird es als belegt markiert, zur Liste der Felder des aktuellen Zuges hinzugefügt,
     * und die Anzahl der verbleibenden Züge wird reduziert.
     * Bei einer ungültigen Auswahl wird eine Fehlermeldung ausgegeben und `invalidField` wird auf true gesetzt.
     */
    public boolean selectAField(int column, int row, Field[][] fields) {
        //liegt ausgewähltes Feld auf Spielfeld
        if(0 <= column && column <= 15 && 0 <= row && row <= 7) {
            //passt die Farbe
            if (fields[column][row].getColor().equals(colorDices[selectedColorDice])) {
                //erstes Feld eines Zuges
                if (fieldsOfThisMove.isEmpty()) {
                    //prüft, dass es kein Randfeld ist
                    if (0 < column && column < 14 && 0 < row && row < 6) {
                        //Liegt das Feld auf Spalte H oder ist es benachbart zu bereits belegtem Feld
                        if ((column == 7 || fields[column - 1][row].isOccupied() || fields[column][row - 1].isOccupied() || fields[column + 1][row].isOccupied() || fields[column][row + 1].isOccupied()) && !fields[column][row].isOccupied()) {
                            fields[column][row].setOccupied(true);
                            fieldsOfThisMove.add(fields[column][row]);
                            remainingMoves--;
                            System.out.println("Field[" + column + "][" + row + "] selected");
                            invalidField = false;
                            return true;
                        }
                        //wenn Randfeld, dann belege es, wenn noch nicht belegt
                    } else if(!fields[column][row].isOccupied()) {
                        fields[column][row].setOccupied(true);
                        fieldsOfThisMove.add(fields[column][row]);
                        remainingMoves--;
                        System.out.println("Field[" + column + "][" + row + "] selected");
                        invalidField = false;
                        return true;
                    }
                } else {
                    //ab zweitem Feld eines Zuges
                    for (Field field : fieldsOfThisMove) {
                        //Ist Feld benachbart zu in diesem Zug bereits belegtem Feld
                        if (((field.getColumn() == column - 1 && field.getRow() == row) || (field.getColumn() == column && field.getRow() == row - 1) || (field.getColumn() == column + 1 && field.getRow() == row) || (field.getColumn() == column && field.getRow() == row + 1)) && !fields[column][row].isOccupied()) {
                            fields[column][row].setOccupied(true);
                            fieldsOfThisMove.add(fields[column][row]);
                            remainingMoves--;
                            System.out.println("Field[" + column + "][" + row + "] selected");
                            invalidField = false;
                            return true;
                        }
                    }
                }
            }
        }
        //Wenn Feld ungültig ist
        System.out.println("Ungültige Eingabe");
        invalidField = true;
        return false;
    }

    /**
     * Überprüft nach einem Zug, ob Spalten vollständig besetzt sind und aktualisiert den Status der Spalten.
     *
     * @param fields Ein zweidimensionales Array von Feld-Objekten, die das Spielfeld darstellen.
     * @param columnsMe Array der Spalten-Objekte für den aktuellen Spieler.
     * @param columnsOther Array der Spalten-Objekte für den gegnerischen Spieler.
     * @return true, wenn das Spiel beendet ist (mindestens 7 Spalten voll), sonst false.
     *
     * Die Methode durchläuft jede Spalte und überprüft, ob alle Felder in dieser Spalte besetzt sind.
     * Wenn eine Spalte vollständig besetzt ist und noch nicht als vom aktuellen Spieler besetzt markiert wurde,
     * wird sie als vom aktuellen Spieler besetzt markiert und die entsprechende Spalte des Gegners als von anderen besetzt.
     *
     * Anschließend wird die Anzahl der vollständig besetzten Spalten des aktuellen Spielers gezählt.
     * Wenn 7 oder mehr Spalten vollständig besetzt sind, wird das Spiel als beendet betrachtet,
     * die Punkte werden berechnet und das Spielende wird zusammen mit den Punktzahlen der Spieler auf der Konsole ausgegeben.
     */
    public boolean checkColumnsAfterMove(Field[][] fields, Column[] columnsMe, Column[] columnsOther) {
        int fullColumns = 0;
        boolean isOccupiedByMe;

        //prüft Spalte für Spalte, ob diese voll ist
        for(int i = 0; i < 15; i++) {
            isOccupiedByMe = true;
            for(int j = 0; j < 7; j++) {
                if(!fields[i][j].isOccupied()) {
                    isOccupiedByMe = false;
                    break;
                }
            }
            //wenn Spalte voll ist und noch nicht als voll gekennzeichnet wurde, mach dies
            if(isOccupiedByMe && !columnsMe[i].isOccupiedByMe()) {
                columnsMe[i].setOccupiedByMe();
                columnsOther[i].setOccupiedByOther();
                System.out.println("Column[" + i + "] is occupied");
            }
        }

        //berechne die Anzahl voller Spalten
        for(Column c : columnsMe) {
            if (c.isOccupiedByMe()) {
                fullColumns++;
            }
        }

        //Wenn ein Spieler 7 Spalten voll hat => Spielende
        if(fullColumns >= 7) {
            calculatePoints();
            System.out.println("Game over\nPoints Player 1: " + pointsPlayerOne + "; Points Player 2: " + pointsPlayerTwo);
            return true;
        } else {
            return false;
        }
    }

    private void calculatePoints() {
        for (Column column : columnsOne) {
            if(column.isFirstOne()) {
                pointsPlayerOne += column.getPointsFirst();
            } else if(column.isOccupiedByMe()) {
                pointsPlayerOne += column.getPointsSecond();
            }
        }
        for (Column column : columnsTwo) {
            if(column.isFirstOne()) {
                pointsPlayerTwo += column.getPointsFirst();
            } else if(column.isOccupiedByMe()) {
                pointsPlayerTwo += column.getPointsSecond();
            }
        }
    }

    /**
     * Gibt die Spielfelder von Spieler 1 zurück.
     * @return Spielfeld 1
     */
    public Field[][] getFieldsOne() {
        return fieldsOne;
    }

    /**
     * Gibt die Spielfelder von Spieler 2 zurück.
     * @return Spielfeld 2
     */
    public Field[][] getFieldsTwo() {
        return fieldsTwo;
    }

    /**
     * Gibt den Index des ausgewählten Farbwürfels zurück.
     * @return Index des ausgewählten Farbwürfels
     */
    public int getSelectedColorDice() {
        return selectedColorDice;
    }

    /**
     * Gibt den Index des ausgewählten Nummernwürfels zurück.
     * @return Index des ausgewählten Nummernwürfels
     */
    public int getSelectedNumberDice() {
        return selectedNumberDice;
    }

    /**
     * Gibt die Farbwürfel zurück.
     * @return Farbwürfel
     */
    public Color[] getColorDices() {
        return colorDices;
    }

    /**
     * Gibt die Nummernwürfel zurück.
     * @return Nummernwürfel
     */
    public int[] getNumberDices() {
        return numberDices;
    }

    /**
     * Gibt die verbleibenden Felder eines Zuges zurück.
     * @return Verbleibenden Felder eines Zuges
     */
    public int getRemainingMoves() {
        return remainingMoves;
    }

    /**
     * Gibt den aktuellen Spielstatus zurück.
     * @return aktueller Spielstatus
     */
    public GameState getGameState() {
        return gameState;
    }

    /**
     * Gibt die Punkte von Spieler 1 zurück.
     * @return Punkte von Spieler 1
     */
    public int getPointsPlayerOne() {
        return pointsPlayerOne;
    }

    /**
     * Gibt die Punkte von Spieler 2 zurück.
     * @return Punkte von Spieler 2
     */
    public int getPointsPlayerTwo() {
        return pointsPlayerTwo;
    }

    /**
     * Gibt die Felder des aktuellen Zuges zurück.
     * @return Felder des aktuellen Zuges
     */
    public ArrayList<Field> getFieldsOfThisMove() {
        return fieldsOfThisMove;
    }

    /**
     * Gibt die Spalten von Spieler 1 zurück.
     * @return Spalten von Spieler 1
     */
    public Column[] getColumnsOne() {
        return columnsOne;
    }

    /**
     * Gibt die Spalten von Spieler 2 zurück.
     * @return Spalten von Spieler 2
     */
    public Column[] getColumnsTwo() {
        return columnsTwo;
    }

    /**
     * Gibt zurück, ob ausgewähltes Feld gültig ist.
     * @return Wahrheitswert, ob ausgewähltes Feld gültig ist
     */
    public boolean isInvalidField() {
        return invalidField;
    }

    /**
     * Setzt den Spielstatus
     * @param gameState Spielstatus
     */
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    /**
     * Wählt Farbwürfel aus.
     * @param selectedColorDice Gewählter Farbwürfel
     */
    public void setSelectedColorDice(int selectedColorDice) {
        this.selectedColorDice = selectedColorDice;
    }

    /**
     * Wählt Nummernwürfel aus.
     * @param selectedNumberDice Gewählter Nummernwürfel
     */
    public void setSelectedNumberDice(int selectedNumberDice) {
        this.selectedNumberDice = selectedNumberDice;
    }

    /**
     * Setzt, ob ausgewähltes Feld gültig ist.
     * @param invalidField Wahrheitswert, ob ausgewähltes Feld gültig ist
     */
    public void setInvalidField(boolean invalidField) {
        this.invalidField = invalidField;
    }
}
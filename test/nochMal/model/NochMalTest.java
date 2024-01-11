package nochMal.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NochMalTest {
    @Test
    void ShouldStartNewGame() {
        boolean b = true;
        Model model = new Model();
        //damit Spielfeld vollständig geladen ist, warte 1 sec
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Field[][] fieldsOne = model.getFieldsOne();
        Field[][] fieldsTwo = model.getFieldsTwo();
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 7; j++) {
                //prüfe, dass kein Feld mehr null ist
                if (fieldsOne[i][j] == null || fieldsTwo[i][j] == null) {
                    b = false;
                }
            }
        }
        assertTrue(b);
    }

    @Test
    void ShouldRollTheDices() {
        boolean b = true;
        Model model = new Model();
        //damit Spielfeld vollständig geladen ist, warte 1 sec
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.rollTheDice();
        model.rollTheDice();
        model.rollTheDice();
        model.rollTheDice();
        model.rollTheDice();
        model.rollTheDice();
        Color[] colorDices = model.getColorDices();
        int[] numberDices = model.getNumberDices();
        for (int i = 0; i < 3; i++) {
            //prüfe, dass kein Würfel mehr weiß bzw. 0 ist
            if (colorDices[i] == Color.WHITE || numberDices[i] == 0) {
                b = false;
            }
        }
        assertTrue(b);
    }

    @Test
    void ShouldSelectAColorDice() {
        boolean b = true;
        Model model = new Model();
        //damit Spielfeld vollständig geladen ist, warte 1 sec
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Teste ungültige Eingabe
        model.selectAColorDice(-1);

        model.selectAColorDice(1);
        //prüfe, dass ein Farbwürfel ausgewählt wurde
        b = model.getSelectedColorDice() == -1;
        assertFalse(b);
    }

    @Test
    void ShouldSelectANumberDice() {
        boolean b = true;
        Model model = new Model();
        //damit Spielfeld vollständig geladen ist, warte 1 sec
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Teste ungültige Eingabe
        model.selectANumberDice(-1);

        model.selectANumberDice(1);
        //prüfe, dass ein Zahlenwürfel ausgewählt wurde
        b = model.getSelectedNumberDice() == -1;
        assertFalse(b);
    }

    @Test
    void ShouldSelectAField() {
        boolean b = false;
        Model model = new Model();
        //damit Spielfeld vollständig geladen ist, warte 1 sec
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Field[][] fields = model.getFieldsOne();

        //Würfel so lange bis Blau und 2 gewürfelt wurde
        do {
            model.rollTheDice();
            model.selectAColorDice(0);
            model.selectANumberDice(0);
        } while (!(model.getColorDices()[model.getSelectedColorDice()].equals(Color.BLUE) && model.getNumberDices()[model.getSelectedNumberDice()] == 2));

        model.setGameState(GameState.ONESELECTFIELD);
        //Teste ungültiges Feld
        model.selectAField(7, 0, fields);
        //Wähle erstes Feld
        model.selectAField(7, 3, fields);

        //prüfe, ob ein Feld markiert wurde
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 7; j++) {
                if (fields[i][j].isOccupied()) {
                    b = true;
                }
            }
        }
        //b ist jetzt true

        //Wähle zweites Feld
        model.selectAField(6, 3, fields);
        //prüfe, ob zwei Felder markiert sind
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 7; j++) {
                if (fields[i][j].isOccupied()) {
                    b = !b;
                }
            }
        }
        //b ist jetzt true

        //ersten Zug beenden
        model.getFieldsOfThisMove().clear();

        //Wähle erstes Feld
        model.selectAField(6, 4, fields);
        //prüfe, ob drei Feler markiert sind
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 7; j++) {
                if (fields[i][j].isOccupied()) {
                    b = !b;
                }
            }
        }
        //b ist jetzt false

        //Wähle zweites Feld
        model.selectAField(7, 4, fields);
        //prüfe, ob vier Felder markiert sind
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 7; j++) {
                if (fields[i][j].isOccupied()) {
                    b = !b;
                }
            }
        }
        //b ist jetzt false

        //zweiten Zug beenden
        model.getFieldsOfThisMove().clear();

        //Wähle ein Randfeld
        model.selectAField(8, 0, fields);
        //prüfe, ob fünf Felder markiert sind
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 7; j++) {
                if (fields[i][j].isOccupied()) {
                    b = !b;
                }
            }
        }
        //b ist jetzt true

        assertTrue(b);
    }

    @Test
    void ShouldCheckColumnsAndCalcPoints() {
        Model model = new Model();
        //damit Spielfeld vollständig geladen ist, warte 1 sec
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Field[][] fieldsOne = model.getFieldsOne();
        Field[][] fieldsTwo = model.getFieldsTwo();
        Column[] columnsOne = model.getColumnsOne();
        Column[] columnsTwo = model.getColumnsTwo();

        //Spieler 2 Spalte 7 füllen
        //Würfel so lange bis Grün und 1 gewürfelt wurde
        do {
            model.rollTheDice();
            model.selectAColorDice(0);
            model.selectANumberDice(0);
        } while (!(model.getColorDices()[model.getSelectedColorDice()].equals(Color.GREEN) && model.getNumberDices()[model.getSelectedNumberDice()] == 1));
        model.setGameState(GameState.TWOSELECTFIELD);
        model.selectAField(7, 0, fieldsTwo);
        model.getFieldsOfThisMove().clear();
        model.checkColumnsAfterMove(fieldsTwo, columnsTwo, columnsOne);

        //Würfel so lange bis Orange und 1 gewürfelt wurde
        do {
            model.rollTheDice();
            model.selectAColorDice(0);
            model.selectANumberDice(0);
        } while (!(model.getColorDices()[model.getSelectedColorDice()].equals(Color.ORANGE) && model.getNumberDices()[model.getSelectedNumberDice()] == 1));
        model.selectAField(7, 1, fieldsTwo);
        model.getFieldsOfThisMove().clear();
        model.checkColumnsAfterMove(fieldsTwo, columnsTwo, columnsOne);

        //Würfel so lange bis Rot und 1 gewürfelt wurde
        do {
            model.rollTheDice();
            model.selectAColorDice(0);
            model.selectANumberDice(0);
        } while (!(model.getColorDices()[model.getSelectedColorDice()].equals(Color.RED) && model.getNumberDices()[model.getSelectedNumberDice()] == 1));
        model.selectAField(7, 2, fieldsTwo);
        model.getFieldsOfThisMove().clear();
        model.checkColumnsAfterMove(fieldsTwo, columnsTwo, columnsOne);

        //Würfel so lange bis Blau und 2 gewürfelt wurde
        do {
            model.rollTheDice();
            model.selectAColorDice(0);
            model.selectANumberDice(0);
        } while (!(model.getColorDices()[model.getSelectedColorDice()].equals(Color.BLUE) && model.getNumberDices()[model.getSelectedNumberDice()] == 2));
        model.selectAField(7, 3, fieldsTwo);
        model.selectAField(7, 4, fieldsTwo);
        model.getFieldsOfThisMove().clear();
        model.checkColumnsAfterMove(fieldsTwo, columnsTwo, columnsOne);

        //Würfel so lange bis Gelb und 2 gewürfelt wurde
        do {
            model.rollTheDice();
            model.selectAColorDice(0);
            model.selectANumberDice(0);
        } while (!(model.getColorDices()[model.getSelectedColorDice()].equals(Color.YELLOW) && model.getNumberDices()[model.getSelectedNumberDice()] == 2));
        model.selectAField(7, 5, fieldsTwo);
        model.selectAField(7, 6, fieldsTwo);
        model.getFieldsOfThisMove().clear();
        model.checkColumnsAfterMove(fieldsTwo, columnsTwo, columnsOne);

        //Spieler 1 Spalte 7 füllen
        //Würfel so lange bis Grün und 1 gewürfelt wurde
        do {
            model.rollTheDice();
            model.selectAColorDice(0);
            model.selectANumberDice(0);
        } while (!(model.getColorDices()[model.getSelectedColorDice()].equals(Color.GREEN) && model.getNumberDices()[model.getSelectedNumberDice()] == 1));
        model.setGameState(GameState.ONESELECTFIELD);
        model.selectAField(7, 0, fieldsOne);
        model.getFieldsOfThisMove().clear();
        model.checkColumnsAfterMove(fieldsOne, columnsOne, columnsTwo);

        //Würfel so lange bis Orange und 1 gewürfelt wurde
        do {
            model.rollTheDice();
            model.selectAColorDice(0);
            model.selectANumberDice(0);
        } while (!(model.getColorDices()[model.getSelectedColorDice()].equals(Color.ORANGE) && model.getNumberDices()[model.getSelectedNumberDice()] == 1));
        model.selectAField(7, 1, fieldsOne);
        model.getFieldsOfThisMove().clear();
        model.checkColumnsAfterMove(fieldsOne, columnsOne, columnsTwo);

        //Würfel so lange bis Rot und 1 gewürfelt wurde
        do {
            model.rollTheDice();
            model.selectAColorDice(0);
            model.selectANumberDice(0);
        } while (!(model.getColorDices()[model.getSelectedColorDice()].equals(Color.RED) && model.getNumberDices()[model.getSelectedNumberDice()] == 1));
        model.selectAField(7, 2, fieldsOne);
        model.getFieldsOfThisMove().clear();
        model.checkColumnsAfterMove(fieldsOne, columnsOne, columnsTwo);

        //Würfel so lange bis Blau und 2 gewürfelt wurde
        do {
            model.rollTheDice();
            model.selectAColorDice(0);
            model.selectANumberDice(0);
        } while (!(model.getColorDices()[model.getSelectedColorDice()].equals(Color.BLUE) && model.getNumberDices()[model.getSelectedNumberDice()] == 2));
        model.selectAField(7, 3, fieldsOne);
        model.selectAField(7, 4, fieldsOne);
        model.getFieldsOfThisMove().clear();
        model.checkColumnsAfterMove(fieldsOne, columnsOne, columnsTwo);

        //Würfel so lange bis Gelb und 2 gewürfelt wurde
        do {
            model.rollTheDice();
            model.selectAColorDice(0);
            model.selectANumberDice(0);
        } while (!(model.getColorDices()[model.getSelectedColorDice()].equals(Color.YELLOW) && model.getNumberDices()[model.getSelectedNumberDice()] == 2));
        model.selectAField(7, 5, fieldsOne);
        model.selectAField(7, 6, fieldsOne);
        model.getFieldsOfThisMove().clear();
        model.checkColumnsAfterMove(fieldsOne, columnsOne, columnsTwo);

        //Spieler 1 Spalte 8 füllen
        //Würfel so lange bis Blau und 1 gewürfelt wurde
        do {
            model.rollTheDice();
            model.selectAColorDice(0);
            model.selectANumberDice(0);
        } while (!(model.getColorDices()[model.getSelectedColorDice()].equals(Color.BLUE) && model.getNumberDices()[model.getSelectedNumberDice()] == 1));
        model.selectAField(8, 0, fieldsOne);
        model.getFieldsOfThisMove().clear();
        model.checkColumnsAfterMove(fieldsOne, columnsOne, columnsTwo);

        //Würfel so lange bis Rot und 2 gewürfelt wurde
        do {
            model.rollTheDice();
            model.selectAColorDice(0);
            model.selectANumberDice(0);
        } while (!(model.getColorDices()[model.getSelectedColorDice()].equals(Color.RED) && model.getNumberDices()[model.getSelectedNumberDice()] == 2));
        model.selectAField(8, 1, fieldsOne);
        model.selectAField(8, 2, fieldsOne);
        model.getFieldsOfThisMove().clear();
        model.checkColumnsAfterMove(fieldsOne, columnsOne, columnsTwo);

        //Würfel so lange bis Grün und 1 gewürfelt wurde
        do {
            model.rollTheDice();
            model.selectAColorDice(0);
            model.selectANumberDice(0);
        } while (!(model.getColorDices()[model.getSelectedColorDice()].equals(Color.GREEN) && model.getNumberDices()[model.getSelectedNumberDice()] == 1));
        model.selectAField(8, 3, fieldsOne);
        model.getFieldsOfThisMove().clear();
        model.checkColumnsAfterMove(fieldsOne, columnsOne, columnsTwo);

        //Würfel so lange bis Orange und 1 gewürfelt wurde
        do {
            model.rollTheDice();
            model.selectAColorDice(0);
            model.selectANumberDice(0);
        } while (!(model.getColorDices()[model.getSelectedColorDice()].equals(Color.ORANGE) && model.getNumberDices()[model.getSelectedNumberDice()] == 1));
        model.selectAField(8, 4, fieldsOne);
        model.getFieldsOfThisMove().clear();
        model.checkColumnsAfterMove(fieldsOne, columnsOne, columnsTwo);

        //Würfel so lange bis Gelb und 2 gewürfelt wurde
        do {
            model.rollTheDice();
            model.selectAColorDice(0);
            model.selectANumberDice(0);
        } while (!(model.getColorDices()[model.getSelectedColorDice()].equals(Color.YELLOW) && model.getNumberDices()[model.getSelectedNumberDice()] == 2));
        model.selectAField(8, 5, fieldsOne);
        model.selectAField(8, 6, fieldsOne);
        model.getFieldsOfThisMove().clear();
        model.checkColumnsAfterMove(fieldsOne, columnsOne, columnsTwo);

        //Spieler 2 Spalte 8 füllen
        //Würfel so lange bis Blau und 1 gewürfelt wurde
        do {
            model.rollTheDice();
            model.selectAColorDice(0);
            model.selectANumberDice(0);
        } while (!(model.getColorDices()[model.getSelectedColorDice()].equals(Color.BLUE) && model.getNumberDices()[model.getSelectedNumberDice()] == 1));
        model.setGameState(GameState.TWOSELECTFIELD);
        model.selectAField(8, 0, fieldsTwo);
        model.getFieldsOfThisMove().clear();
        model.checkColumnsAfterMove(fieldsTwo, columnsTwo, columnsOne);

        //Würfel so lange bis Rot und 2 gewürfelt wurde
        do {
            model.rollTheDice();
            model.selectAColorDice(0);
            model.selectANumberDice(0);
        } while (!(model.getColorDices()[model.getSelectedColorDice()].equals(Color.RED) && model.getNumberDices()[model.getSelectedNumberDice()] == 2));
        model.selectAField(8, 1, fieldsTwo);
        model.selectAField(8, 2, fieldsTwo);
        model.getFieldsOfThisMove().clear();
        model.checkColumnsAfterMove(fieldsTwo, columnsTwo, columnsOne);

        //Würfel so lange bis Grün und 1 gewürfelt wurde
        do {
            model.rollTheDice();
            model.selectAColorDice(0);
            model.selectANumberDice(0);
        } while (!(model.getColorDices()[model.getSelectedColorDice()].equals(Color.GREEN) && model.getNumberDices()[model.getSelectedNumberDice()] == 1));
        model.selectAField(8, 3, fieldsTwo);
        model.getFieldsOfThisMove().clear();
        model.checkColumnsAfterMove(fieldsTwo, columnsTwo, columnsOne);

        //Würfel so lange bis Orange und 1 gewürfelt wurde
        do {
            model.rollTheDice();
            model.selectAColorDice(0);
            model.selectANumberDice(0);
        } while (!(model.getColorDices()[model.getSelectedColorDice()].equals(Color.ORANGE) && model.getNumberDices()[model.getSelectedNumberDice()] == 2));
        model.selectAField(8, 4, fieldsTwo);
        model.getFieldsOfThisMove().clear();
        model.checkColumnsAfterMove(fieldsTwo, columnsTwo, columnsOne);

        //Würfel so lange bis Gelb und 2 gewürfelt wurde
        do {
            model.rollTheDice();
            model.selectAColorDice(0);
            model.selectANumberDice(0);
        } while (!(model.getColorDices()[model.getSelectedColorDice()].equals(Color.YELLOW) && model.getNumberDices()[model.getSelectedNumberDice()] == 2));
        model.selectAField(8, 5, fieldsTwo);
        model.selectAField(8, 6, fieldsTwo);
        model.getFieldsOfThisMove().clear();
        model.checkColumnsAfterMove(fieldsTwo, columnsTwo, columnsOne);

        //Spieler 1 Spalte 9 füllen
        //Würfel so lange bis Blau und 2 gewürfelt wurde
        do {
            model.rollTheDice();
            model.selectAColorDice(0);
            model.selectANumberDice(0);
        } while (!(model.getColorDices()[model.getSelectedColorDice()].equals(Color.BLUE) && model.getNumberDices()[model.getSelectedNumberDice()] == 2));
        model.setGameState(GameState.ONESELECTFIELD);
        model.selectAField(9, 0, fieldsOne);
        model.selectAField(9, 1, fieldsOne);
        model.getFieldsOfThisMove().clear();
        model.checkColumnsAfterMove(fieldsOne, columnsOne, columnsTwo);

        //Würfel so lange bis Rot und 1 gewürfelt wurde
        do {
            model.rollTheDice();
            model.selectAColorDice(0);
            model.selectANumberDice(0);
        } while (!(model.getColorDices()[model.getSelectedColorDice()].equals(Color.RED) && model.getNumberDices()[model.getSelectedNumberDice()] == 1));
        model.selectAField(9, 2, fieldsOne);
        model.getFieldsOfThisMove().clear();
        model.checkColumnsAfterMove(fieldsOne, columnsOne, columnsTwo);

        //Würfel so lange bis Grün und 1 gewürfelt wurde
        do {
            model.rollTheDice();
            model.selectAColorDice(0);
            model.selectANumberDice(0);
        } while (!(model.getColorDices()[model.getSelectedColorDice()].equals(Color.GREEN) && model.getNumberDices()[model.getSelectedNumberDice()] == 1));
        model.selectAField(9, 3, fieldsOne);
        model.getFieldsOfThisMove().clear();
        model.checkColumnsAfterMove(fieldsOne, columnsOne, columnsTwo);

        //Würfel so lange bis Orange und 2 gewürfelt wurde
        do {
            model.rollTheDice();
            model.selectAColorDice(0);
            model.selectANumberDice(0);
        } while (!(model.getColorDices()[model.getSelectedColorDice()].equals(Color.ORANGE) && model.getNumberDices()[model.getSelectedNumberDice()] == 2));
        model.selectAField(9, 4, fieldsOne);
        model.selectAField(9, 5, fieldsOne);
        model.getFieldsOfThisMove().clear();
        model.checkColumnsAfterMove(fieldsOne, columnsOne, columnsTwo);

        //Würfel so lange bis Gelb und 1 gewürfelt wurde
        do {
            model.rollTheDice();
            model.selectAColorDice(0);
            model.selectANumberDice(0);
        } while (!(model.getColorDices()[model.getSelectedColorDice()].equals(Color.YELLOW) && model.getNumberDices()[model.getSelectedNumberDice()] == 1));
        model.selectAField(9, 6, fieldsOne);
        model.getFieldsOfThisMove().clear();
        model.checkColumnsAfterMove(fieldsOne, columnsOne, columnsTwo);

        //Spieler 1 Spalte 10 füllen
        //Würfel so lange bis Blau und 2 gewürfelt wurde
        do {
            model.rollTheDice();
            model.selectAColorDice(0);
            model.selectANumberDice(0);
        } while (!(model.getColorDices()[model.getSelectedColorDice()].equals(Color.BLUE) && model.getNumberDices()[model.getSelectedNumberDice()] == 2));
        model.selectAField(10, 0, fieldsOne);
        model.selectAField(10, 1, fieldsOne);
        model.getFieldsOfThisMove().clear();
        model.checkColumnsAfterMove(fieldsOne, columnsOne, columnsTwo);

        //Würfel so lange bis Gelb und 2 gewürfelt wurde
        do {
            model.rollTheDice();
            model.selectAColorDice(0);
            model.selectANumberDice(0);
        } while (!(model.getColorDices()[model.getSelectedColorDice()].equals(Color.YELLOW) && model.getNumberDices()[model.getSelectedNumberDice()] == 2));
        model.selectAField(10, 2, fieldsOne);
        model.selectAField(10, 3, fieldsOne);
        model.getFieldsOfThisMove().clear();
        model.checkColumnsAfterMove(fieldsOne, columnsOne, columnsTwo);

        //Würfel so lange bis Orange und 1 gewürfelt wurde
        do {
            model.rollTheDice();
            model.selectAColorDice(0);
            model.selectANumberDice(0);
        } while (!(model.getColorDices()[model.getSelectedColorDice()].equals(Color.ORANGE) && model.getNumberDices()[model.getSelectedNumberDice()] == 1));
        model.selectAField(10, 4, fieldsOne);
        model.getFieldsOfThisMove().clear();
        model.checkColumnsAfterMove(fieldsOne, columnsOne, columnsTwo);

        //Würfel so lange bis Rot und 1 gewürfelt wurde
        do {
            model.rollTheDice();
            model.selectAColorDice(0);
            model.selectANumberDice(0);
        } while (!(model.getColorDices()[model.getSelectedColorDice()].equals(Color.RED) && model.getNumberDices()[model.getSelectedNumberDice()] == 1));
        model.selectAField(10, 5, fieldsOne);
        model.getFieldsOfThisMove().clear();
        model.checkColumnsAfterMove(fieldsOne, columnsOne, columnsTwo);

        //Würfel so lange bis Grün und 1 gewürfelt wurde
        do {
            model.rollTheDice();
            model.selectAColorDice(0);
            model.selectANumberDice(0);
        } while (!(model.getColorDices()[model.getSelectedColorDice()].equals(Color.GREEN) && model.getNumberDices()[model.getSelectedNumberDice()] == 1));
        model.selectAField(10, 6, fieldsOne);
        model.getFieldsOfThisMove().clear();
        model.checkColumnsAfterMove(fieldsOne, columnsOne, columnsTwo);

        //Spieler 1 Spalte 11 füllen
        //Würfel so lange bis Orange und 2 gewürfelt wurde
        do {
            model.rollTheDice();
            model.selectAColorDice(0);
            model.selectANumberDice(0);
        } while (!(model.getColorDices()[model.getSelectedColorDice()].equals(Color.ORANGE) && model.getNumberDices()[model.getSelectedNumberDice()] == 2));
        model.selectAField(11, 0, fieldsOne);
        model.selectAField(11, 1, fieldsOne);
        model.getFieldsOfThisMove().clear();
        model.checkColumnsAfterMove(fieldsOne, columnsOne, columnsTwo);

        //Würfel so lange bis Gelb und 2 gewürfelt wurde
        do {
            model.rollTheDice();
            model.selectAColorDice(0);
            model.selectANumberDice(0);
        } while (!(model.getColorDices()[model.getSelectedColorDice()].equals(Color.YELLOW) && model.getNumberDices()[model.getSelectedNumberDice()] == 2));
        model.selectAField(11, 2, fieldsOne);
        model.selectAField(11, 3, fieldsOne);
        model.getFieldsOfThisMove().clear();
        model.checkColumnsAfterMove(fieldsOne, columnsOne, columnsTwo);

        //Würfel so lange bis Rot und 1 gewürfelt wurde
        do {
            model.rollTheDice();
            model.selectAColorDice(0);
            model.selectANumberDice(0);
        } while (!(model.getColorDices()[model.getSelectedColorDice()].equals(Color.RED) && model.getNumberDices()[model.getSelectedNumberDice()] == 1));
        model.selectAField(11, 4, fieldsOne);
        model.getFieldsOfThisMove().clear();
        model.checkColumnsAfterMove(fieldsOne, columnsOne, columnsTwo);

        //Würfel so lange bis Blau und 1 gewürfelt wurde
        do {
            model.rollTheDice();
            model.selectAColorDice(0);
            model.selectANumberDice(0);
        } while (!(model.getColorDices()[model.getSelectedColorDice()].equals(Color.BLUE) && model.getNumberDices()[model.getSelectedNumberDice()] == 1));
        model.selectAField(11, 5, fieldsOne);
        model.getFieldsOfThisMove().clear();
        model.checkColumnsAfterMove(fieldsOne, columnsOne, columnsTwo);

        //Würfel so lange bis Grün und 1 gewürfelt wurde
        do {
            model.rollTheDice();
            model.selectAColorDice(0);
            model.selectANumberDice(0);
        } while (!(model.getColorDices()[model.getSelectedColorDice()].equals(Color.GREEN) && model.getNumberDices()[model.getSelectedNumberDice()] == 1));
        model.selectAField(11, 6, fieldsOne);
        model.getFieldsOfThisMove().clear();
        model.checkColumnsAfterMove(fieldsOne, columnsOne, columnsTwo);

        //Spieler 1 Spalte 12 füllen
        //Würfel so lange bis Gelb und 1 gewürfelt wurde
        do {
            model.rollTheDice();
            model.selectAColorDice(0);
            model.selectANumberDice(0);
        } while (!(model.getColorDices()[model.getSelectedColorDice()].equals(Color.YELLOW) && model.getNumberDices()[model.getSelectedNumberDice()] == 1));
        model.selectAField(12, 0, fieldsOne);
        model.getFieldsOfThisMove().clear();
        model.checkColumnsAfterMove(fieldsOne, columnsOne, columnsTwo);

        //Würfel so lange bis Orange und 3 gewürfelt wurde
        do {
            model.rollTheDice();
            model.selectAColorDice(0);
            model.selectANumberDice(0);
        } while (!(model.getColorDices()[model.getSelectedColorDice()].equals(Color.ORANGE) && model.getNumberDices()[model.getSelectedNumberDice()] == 3));
        model.selectAField(12, 1, fieldsOne);
        model.selectAField(12, 2, fieldsOne);
        model.selectAField(12, 3, fieldsOne);
        model.getFieldsOfThisMove().clear();
        model.checkColumnsAfterMove(fieldsOne, columnsOne, columnsTwo);

        //Würfel so lange bis Rot und 1 gewürfelt wurde
        do {
            model.rollTheDice();
            model.selectAColorDice(0);
            model.selectANumberDice(0);
        } while (!(model.getColorDices()[model.getSelectedColorDice()].equals(Color.RED) && model.getNumberDices()[model.getSelectedNumberDice()] == 1));
        model.selectAField(12, 4, fieldsOne);
        model.getFieldsOfThisMove().clear();
        model.checkColumnsAfterMove(fieldsOne, columnsOne, columnsTwo);

        //Würfel so lange bis Blau und 1 gewürfelt wurde
        do {
            model.rollTheDice();
            model.selectAColorDice(0);
            model.selectANumberDice(0);
        } while (!(model.getColorDices()[model.getSelectedColorDice()].equals(Color.BLUE) && model.getNumberDices()[model.getSelectedNumberDice()] == 1));
        model.selectAField(12, 5, fieldsOne);
        model.getFieldsOfThisMove().clear();
        model.checkColumnsAfterMove(fieldsOne, columnsOne, columnsTwo);

        //Würfel so lange bis Grün und 1 gewürfelt wurde
        do {
            model.rollTheDice();
            model.selectAColorDice(0);
            model.selectANumberDice(0);
        } while (!(model.getColorDices()[model.getSelectedColorDice()].equals(Color.GREEN) && model.getNumberDices()[model.getSelectedNumberDice()] == 1));
        model.selectAField(12, 6, fieldsOne);
        model.getFieldsOfThisMove().clear();
        model.checkColumnsAfterMove(fieldsOne, columnsOne, columnsTwo);

        //Spieler 1 Spalte 13 füllen
        //Würfel so lange bis Gelb und 1 gewürfelt wurde
        do {
            model.rollTheDice();
            model.selectAColorDice(0);
            model.selectANumberDice(0);
        } while (!(model.getColorDices()[model.getSelectedColorDice()].equals(Color.YELLOW) && model.getNumberDices()[model.getSelectedNumberDice()] == 1));
        model.selectAField(13, 0, fieldsOne);
        model.getFieldsOfThisMove().clear();
        model.checkColumnsAfterMove(fieldsOne, columnsOne, columnsTwo);

        //Würfel so lange bis Grün und 2 gewürfelt wurde
        do {
            model.rollTheDice();
            model.selectAColorDice(0);
            model.selectANumberDice(0);
        } while (!(model.getColorDices()[model.getSelectedColorDice()].equals(Color.GREEN) && model.getNumberDices()[model.getSelectedNumberDice()] == 2));
        model.selectAField(13, 1, fieldsOne);
        model.selectAField(13, 2, fieldsOne);
        model.getFieldsOfThisMove().clear();
        model.checkColumnsAfterMove(fieldsOne, columnsOne, columnsTwo);

        //Würfel so lange bis Rot und 2 gewürfelt wurde
        do {
            model.rollTheDice();
            model.selectAColorDice(0);
            model.selectANumberDice(0);
        } while (!(model.getColorDices()[model.getSelectedColorDice()].equals(Color.RED) && model.getNumberDices()[model.getSelectedNumberDice()] == 2));
        model.selectAField(13, 3, fieldsOne);
        model.selectAField(13, 4, fieldsOne);
        model.getFieldsOfThisMove().clear();
        model.checkColumnsAfterMove(fieldsOne, columnsOne, columnsTwo);

        //Würfel so lange bis Blau und 1 gewürfelt wurde
        do {
            model.rollTheDice();
            model.selectAColorDice(0);
            model.selectANumberDice(0);
        } while (!(model.getColorDices()[model.getSelectedColorDice()].equals(Color.BLUE) && model.getNumberDices()[model.getSelectedNumberDice()] == 1));
        model.selectAField(13, 5, fieldsOne);
        model.getFieldsOfThisMove().clear();
        model.checkColumnsAfterMove(fieldsOne, columnsOne, columnsTwo);

        //Würfel so lange bis Orange und 1 gewürfelt wurde
        do {
            model.rollTheDice();
            model.selectAColorDice(0);
            model.selectANumberDice(0);
        } while (!(model.getColorDices()[model.getSelectedColorDice()].equals(Color.ORANGE) && model.getNumberDices()[model.getSelectedNumberDice()] == 1));
        model.selectAField(13, 6, fieldsOne);
        model.getFieldsOfThisMove().clear();
        model.checkColumnsAfterMove(fieldsOne, columnsOne, columnsTwo);

        assertTrue(model.getPointsPlayerOne() == 15 && model.getPointsPlayerTwo() == 2);
    }
}
package nochMal.view;

import nochMal.controller.IController;
import nochMal.model.Color;
import nochMal.model.Column;
import nochMal.model.Field;
import nochMal.model.GameState;
import processing.core.PApplet;

/**
 * Diese Klasse stellt den View von NochMal dar.
 * Sie beinhaltet die komplette GUI, welche mittels Processing realisiert wird.
 */
public class View extends PApplet implements IView {
    private IController controller;

    /**
     * main-Methode, die main-Methode von PApplet startet.
     * @param args args
     */
    public static void main(String[] args) {
        PApplet.main(View.class);
    }

    /**
     * settings-Methode, welche Einstellungen des Sketches vornimmt
     */
    @Override
    public void settings() {
        setSize(1650, 650);
        pixelDensity(2);
    }

    /**
     * setup-Methode, die initiale Einstellungen vornimmt.
     */
    @Override
    public void setup() {
        textAlign(CENTER, CENTER);
    }

    /**
     * draw-Methode, welche in Endlosschleife läuft und den Sketch zeichnet.
     */
    @Override
    public void draw() {
        controller.nextFrame();
    }

    /**
     * Zeichnet das komplette Spielfeld.
     *
     * @param colorDiceOne Farbwürfel 1
     * @param colorDiceTwo Farbwürfel 2
     * @param colorDiceThree Farbwürfel 3
     * @param selectedColorDice Index des ausgewählten Farbwürfels
     * @param numberDiceOne Nummernwürfel 1
     * @param numberDiceTwo Nummernwürfel 2
     * @param numberDiceThree Nummernwürfel 3
     * @param selectedNumberDice Index des ausgewählten Nummernwürfels
     * @param fieldsOne Spielfelder von Spieler 1
     * @param fieldsTwo Spielfelder von Spieler 2
     * @param ColumnsOne Spalten von Spieler 1
     * @param ColumnsTwo Spalten von Spieler 2
     * @param gameState aktueller Spielzustand
     * @param invalidField Wahrheitswert, ob ausgewähltes Feld gültig ist
     */
    public void drawGame(Color colorDiceOne, Color colorDiceTwo, Color colorDiceThree, int selectedColorDice, int numberDiceOne, int numberDiceTwo, int numberDiceThree, int selectedNumberDice, Field[][] fieldsOne, Field[][] fieldsTwo, Column[] ColumnsOne, Column[] ColumnsTwo, GameState gameState, boolean invalidField) {
        background(255);
        int k;
        int x;
        int y;

        //zeichne Farbwürfel
        switch (colorDiceOne) {
            case GREEN:
                fill(102, 205, 0);
                break;
            case YELLOW:
                fill(255, 215, 0);
                break;
            case BLUE:
                fill(79, 148, 205);
                break;
            case RED:
                fill(238, 44, 44);
                break;
            case ORANGE:
                fill(255, 140, 0);
                break;
            case WHITE:
                fill(255);
                break;
        }
        square(10, 10, 50);

        //markiere, falls ausgewählt
        if(selectedColorDice == 0) {
            circle(35, 35, 25);
        }

        switch (colorDiceTwo) {
            case GREEN:
                fill(102, 205, 0);
                break;
            case YELLOW:
                fill(255, 215, 0);
                break;
            case BLUE:
                fill(79, 148, 205);
                break;
            case RED:
                fill(238, 44, 44);
                break;
            case ORANGE:
                fill(255, 140, 0);
                break;
            case WHITE:
                fill(255);
                break;
        }
        square(10, 70, 50);

        //markiere, falls ausgewählt
        if(selectedColorDice == 1) {
            circle(35, 95, 25);
        }

        switch (colorDiceThree) {
            case GREEN:
                fill(102, 205, 0);
                break;
            case YELLOW:
                fill(255, 215, 0);
                break;
            case BLUE:
                fill(79, 148, 205);
                break;
            case RED:
                fill(238, 44, 44);
                break;
            case ORANGE:
                fill(255, 140, 0);
                break;
            case WHITE:
                fill(255);
                break;
        }
        square(10, 130, 50);

        //markiere, falls ausgewählt
        if(selectedColorDice == 2) {
            circle(35, 155, 25);
        }

        //zeichne Zahlenwürfel
        fill(255);
        square(10, 190, 50);
        square(10, 250, 50);
        square(10, 310, 50);

        //markiere ausgewählten Würfel
        switch(selectedNumberDice) {
            case 0:
                circle(35, 215, 25);
                break;
            case 1:
                circle(35, 275, 25);
                break;
            case 2:
                circle(35, 335, 25);
                break;
        }

        //zeichne Zahlen in Zahlenwürfel
        textSize(24);
        fill(0);
        text(numberDiceOne, 35, 215);
        text(numberDiceTwo, 35, 275);
        text(numberDiceThree, 35, 335);

        //zeichne Buchstaben 1
        fill(255);
        for (x = 100; x <= 800; x = x + 50) {
            square(x, 10, 50);
        }
        fill(0);
        text("A", 125, 35);
        text("B", 175, 35);
        text("C", 225, 35);
        text("D", 275, 35);
        text("E", 325, 35);
        text("F", 375, 35);
        text("G", 425, 35);
        text("H", 475, 35);
        text("I", 525, 35);
        text("J", 575, 35);
        text("K", 625, 35);
        text("L", 675, 35);
        text("M", 725, 35);
        text("N", 775, 35);
        text("O", 825, 35);

        //zeichne Spielfeld 1
        x = 100;
        y = 60;
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 7; j++) {
                if(fieldsOne[i][j] == null) {
                    fill(255);
                } else {
                    switch (fieldsOne[i][j].getColor()) {
                        case GREEN:
                            fill(102, 205, 0);
                            break;
                        case YELLOW:
                            fill(255, 215, 0);
                            break;
                        case BLUE:
                            fill(79, 148, 205);
                            break;
                        case RED:
                            fill(238, 44, 44);
                            break;
                        case ORANGE:
                            fill(255, 140, 0);
                            break;
                    }
                }
                square(x, y, 50);
                if(fieldsOne[i][j] != null && fieldsOne[i][j].isOccupied()) {
                    circle(x + 25, y + 25, 25);
                }
                y += 50;
            }
            x += 50;
            y = 60;
        }

        //zeichne Punktangaben 1
        k = 0;
        for (x = 100; x <= 800; x = x + 50) {
            fill(255);
            square(x, 410, 50);
            square(x, 460, 50);
            if(ColumnsOne[k] != null && ColumnsOne[k].isFirstOne()) {
                circle(x + 25, 410 + 25, 25);
            } else {
                if (ColumnsOne[k] != null && ColumnsOne[k].isOccupiedByMe()) {
                    circle(x + 25, 460 + 25, 25);
                }
                if (ColumnsOne[k] != null && ColumnsOne[k].isOccupiedByOther()) {
                    line(x, 410 + 25, x + 50, 410 + 25);
                }
            }
            fill(0);
            text(ColumnsOne[k].getPointsFirst(), x + 25, 410 + 25);
            text(ColumnsOne[k].getPointsSecond(), x + 25, 460 + 25);
            k++;
        }

        //zeichne Spieleranzeige 1
        if(gameState.equals(GameState.ONEROLLTHEDICE) || gameState.equals(GameState.ONESELECTCOLOR) || gameState.equals(GameState.ONESELECTNUMBER) || gameState.equals(GameState.ONESELECTFIELD)) {
            fill(255, 0, 0);
        } else {
            fill(255);
        }
        rect(375, 530, 200, 100);
        fill(0);
        text("Spieler 1", 475, 580);

        //zeichne Buchstaben 2
        fill(255);
        for (x = 890; x <= 1590; x = x + 50) {
            square(x, 10, 50);
        }
        fill(0);
        text("A", 915, 35);
        text("B", 965, 35);
        text("C", 1015, 35);
        text("D", 1065, 35);
        text("E", 1115, 35);
        text("F", 1165, 35);
        text("G", 1215, 35);
        text("H", 1265, 35);
        text("I", 1315, 35);
        text("J", 1365, 35);
        text("K", 1415, 35);
        text("L", 1465, 35);
        text("M", 1515, 35);
        text("N", 1565, 35);
        text("O", 1615, 35);

        //zeichne Spielfeld 2
        x = 890;
        y = 60;
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 7; j++) {
                if(fieldsTwo[i][j] == null) {
                    fill(255);
                } else {
                    switch (fieldsTwo[i][j].getColor()) {
                        case GREEN:
                            fill(102, 205, 0);
                            break;
                        case YELLOW:
                            fill(255, 215, 0);
                            break;
                        case BLUE:
                            fill(79, 148, 205);
                            break;
                        case RED:
                            fill(238, 44, 44);
                            break;
                        case ORANGE:
                            fill(255, 140, 0);
                            break;
                    }
                }
                square(x, y, 50);
                if(fieldsTwo[i][j] != null && fieldsTwo[i][j].isOccupied()) {
                    circle(x + 25, y + 25, 25);
                }
                y += 50;
            }
            x += 50;
            y = 60;
        }

        //zeichne Punktangaben 2
        k = 0;
        for (x = 890; x <= 1590; x = x + 50) {
            fill(255);
            square(x, 410, 50);
            square(x, 460, 50);
            if(ColumnsTwo[k] != null && ColumnsTwo[k].isFirstOne()) {
                circle(x + 25, 410 + 25, 25);
            } else {
                if (ColumnsTwo[k] != null && ColumnsTwo[k].isOccupiedByMe()) {
                    circle(x + 25, 460 + 25, 25);
                }
                if (ColumnsTwo[k] != null && ColumnsTwo[k].isOccupiedByOther()) {
                    line(x, 410 + 25, x + 50, 410 + 25);
                }
            }
            fill(0);
            text(ColumnsTwo[k].getPointsFirst(), x + 25, 410 + 25);
            text(ColumnsTwo[k].getPointsSecond(), x + 25, 460 + 25);
            k++;
        }

        //zeichne Spieleranzeige 2
        if(gameState.equals(GameState.TWOROLLTHEDICE) || gameState.equals(GameState.TWOSELECTCOLOR) || gameState.equals(GameState.TWOSELECTNUMBER) || gameState.equals(GameState.TWOSELECTFIELD)) {
            fill(255, 0, 0);
        } else {
            fill(255);
        }
        rect(1165, 530, 200, 100);
        fill(0);
        text("Spieler 2", 1265, 580);

        //zeichne Kann-Nicht-Button
        fill(255);
        rect(10, 530, 200, 100);
        fill(0);
        text("Ich kann nicht", 110, 580);

        //zeichne Spielstatus
        fill(255);
        rect(770, 530, 200, 100);
        fill(0);
        String state = "Error";
        switch(gameState) {
            case ONEROLLTHEDICE, TWOROLLTHEDICE:
                state = "Würfeln";
                break;
            case ONESELECTCOLOR, TWOSELECTCOLOR:
                state = "Wähle Farbe";
                break;
            case ONESELECTNUMBER, TWOSELECTNUMBER:
                state = "Wähle Augenzahl";
                break;
            case ONESELECTFIELD, TWOSELECTFIELD:
                state = "Wähle Feld";
                break;
        }
        text(state, 870, 580);

        if(invalidField) {
            fill(255, 0, 0);
            text("Ungültige Eingabe", 870, 550);
        }
    }

    /**
     * Zeichnet den Endbildschirm des Spiels.
     *
     * @param pointsPlayerOne Punkte von Spieler 1
     * @param pointsPlayerTwo Punkte von Spieler 2
     */
    public void drawEnd(int pointsPlayerOne, int pointsPlayerTwo) {
        background(255);
        textSize(32);

        if(pointsPlayerOne > pointsPlayerTwo) {
            fill(255, 0, 0);
            rect(285, 225, 400, 200);
            fill(0);
            text("Spieler 1: " + pointsPlayerOne + " Punkte", 485, 325);
            text("Gewinner:", 485, 200);
            fill(255);
            rect(970, 225, 400, 200);
            fill(0);
            text("Spieler 2: " + pointsPlayerTwo + " Punkte", 1170, 325);
        } else {
            fill(255);
            rect(285, 225, 400, 200);
            fill(0);
            text("Spieler 1: " + pointsPlayerOne + " Punkte", 485, 325);
            fill(255, 0, 0);
            rect(970, 225, 400, 200);
            fill(0);
            text("Spieler 2: " + pointsPlayerTwo + " Punkte", 1170, 325);
            text("Gewinner:", 1170, 200);
        }
    }

    /**
     * Wird aufgerufen, wenn Spieler mit der Maus klickt.
     * Eingabe wird direkt an Controller weitergeleitet
     */
    @Override
    public void mouseClicked() {
        controller.userInput(mouseX, mouseY);
    }

    /**
     * Setzt den Controller.
     * @param controller Controller
     */
    public void setController(IController controller) {
        this.controller = controller;
    }
}

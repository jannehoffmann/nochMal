package nochMal.controller;

import nochMal.model.Field;
import nochMal.model.GameState;
import nochMal.model.Model;
import nochMal.view.IView;

/**
 * Diese Klasse stellt den Controller von NochMal dar.
 * Sie verbindet die Logik und die UI des Spiels.
 */
public class Controller implements IController {
    private Model model;
    private IView view;

    /**
     * Aktualisiert das Spiel und die Darstellung im nächsten Frame.
     *
     * Abhängig vom aktuellen Spielzustand wird entweder das Ende des Spiels oder der aktuelle Spielstand gezeichnet.
     * Wenn das Spiel beendet ist (GameState.END), wird das Endspiel-Szenario mit den Punkten beider Spieler gezeichnet.
     * Andernfalls wird der aktuelle Spielstand mit allen relevanten Spielinformationen gezeichnet.
     */
    public void nextFrame() {
        if(model.getGameState().equals(GameState.END)) {
            view.drawEnd(model.getPointsPlayerOne(), model.getPointsPlayerTwo());
        } else {
            view.drawGame(model.getColorDices()[0], model.getColorDices()[1], model.getColorDices()[2], model.getSelectedColorDice(), model.getNumberDices()[0], model.getNumberDices()[1], model.getNumberDices()[2], model.getSelectedNumberDice(), model.getFieldsOne(), model.getFieldsTwo(), model.getColumnsOne(), model.getColumnsTwo(), model.getGameState(), model.isInvalidField());
        }
    }

    /**
     * Verarbeitet die Mauseingaben und ruft je nach Spielzustand die entsprechenden Methoden des Models auf.
     *
     * @param mouseX X-Position der Mauseingabe
     * @param mouseY Y-Position der Mauseingabe
     */
    public void userInput(int mouseX, int mouseY) {
        int selectedColorDice = -1;
        int selectedNumberDice = -1;
        int column = -1;
        int row = -1;

        switch (model.getGameState()) {
            //Spieler 1 würfelt
            case ONEROLLTHEDICE:
                if((0 <= mouseX && mouseX <= 99) && (0 <= mouseY && mouseY <= 399)) {
                    model.rollTheDice();
                    model.setGameState(GameState.ONESELECTCOLOR);
                    model.setInvalidField(false);
                } else {
                    model.setInvalidField(true);
                }
                break;
            //Spieler 1 wählt Farbe
            case ONESELECTCOLOR:
                if((10 <= mouseX && mouseX <= 60) && (10 <= mouseY && mouseY <= 60)) {
                    selectedColorDice = 0;
                } else if((10 <= mouseX && mouseX <= 60) && (70 <= mouseY && mouseY <= 120)) {
                    selectedColorDice = 1;
                } else if((10 <= mouseX && mouseX <= 60) && (130 <= mouseY && mouseY <= 180)) {
                    selectedColorDice = 2;
                }

                //Kann-Nicht-Button
                if(10 <= mouseX && mouseX <= 210 && 530 <= mouseY && mouseY <= 630) {
                    model.setGameState(GameState.TWOROLLTHEDICE);
                    model.setSelectedColorDice(-1);
                    model.setSelectedNumberDice(-1);
                    model.setInvalidField(false);
                    break;
                }

                //Wenn Würfel ausgewählt, weiter im Spiel
                if(model.selectAColorDice(selectedColorDice)) {
                    model.setGameState(GameState.ONESELECTNUMBER);
                }
                break;
            //Spieler 1 wählt Nummer
            case ONESELECTNUMBER:
                if((10 <= mouseX && mouseX <= 60) && (190 <= mouseY && mouseY <= 240)) {
                    selectedNumberDice = 0;
                } else if((10 <= mouseX && mouseX <= 60) && (250 <= mouseY && mouseY <= 300)) {
                    selectedNumberDice = 1;
                } else if((10 <= mouseX && mouseX <= 60) && (310 <= mouseY && mouseY <= 360)) {
                    selectedNumberDice = 2;
                }

                //Kann-Nicht-Button
                if(10 <= mouseX && mouseX <= 210 && 530 <= mouseY && mouseY <= 630) {
                    model.setGameState(GameState.TWOROLLTHEDICE);
                    model.setSelectedColorDice(-1);
                    model.setSelectedNumberDice(-1);
                    model.setInvalidField(false);
                    break;
                }

                //Wenn Würfel ausgewählt, weiter im Spiel
                if(model.selectANumberDice(selectedNumberDice)) {
                    model.setGameState(GameState.ONESELECTFIELD);
                }
                break;
            //Spieler 1 wählt Feld
            case ONESELECTFIELD:
                //Finde Spalte des ausgewählten Feldes
                if(100 <= mouseX && mouseX <= 149) {
                    column = 0;
                } else if(150 <= mouseX && mouseX <= 199) {
                    column = 1;
                } else if(200 <= mouseX && mouseX <= 249) {
                    column = 2;
                } else if(250 <= mouseX && mouseX <= 299) {
                    column = 3;
                } else if(300 <= mouseX && mouseX <= 349) {
                    column = 4;
                } else if(350 <= mouseX && mouseX <= 399) {
                    column = 5;
                } else if(400 <= mouseX && mouseX <= 449) {
                    column = 6;
                } else if(450 <= mouseX && mouseX <= 499) {
                    column = 7;
                } else if(500 <= mouseX && mouseX <= 549) {
                    column = 8;
                } else if(550 <= mouseX && mouseX <= 599) {
                    column = 9;
                } else if(600 <= mouseX && mouseX <= 649) {
                    column = 10;
                } else if(650 <= mouseX && mouseX <= 699) {
                    column = 11;
                } else if(700 <= mouseX && mouseX <= 749) {
                    column = 12;
                } else if(750 <= mouseX && mouseX <= 799) {
                    column = 13;
                } else if(800 <= mouseX && mouseX <= 849) {
                    column = 14;
                }

                //Finde Zeile des ausgewählten Feldes
                if(60 <= mouseY && mouseY <= 109) {
                    row = 0;
                } else if(110 <= mouseY && mouseY <= 159) {
                    row = 1;
                } else if(160 <= mouseY && mouseY <= 209) {
                    row = 2;
                } else if(210 <= mouseY && mouseY <= 259) {
                    row = 3;
                } else if(260 <= mouseY && mouseY <= 309) {
                    row = 4;
                } else if(310 <= mouseY && mouseY <= 359) {
                    row = 5;
                } else if(360 <= mouseY && mouseY <= 409) {
                    row = 6;
                }

                //Kann-Nicht-Button
                if(10 <= mouseX && mouseX <= 210 && 530 <= mouseY && mouseY <= 630) {
                    model.setGameState(GameState.TWOROLLTHEDICE);
                    for(Field field : model.getFieldsOfThisMove()) {
                        model.getFieldsOne()[field.getColumn()][field.getRow()].setOccupied(false);
                    }
                    model.getFieldsOfThisMove().clear();
                    model.setSelectedColorDice(-1);
                    model.setSelectedNumberDice(-1);
                    model.setInvalidField(false);
                    break;
                }

                model.selectAField(column, row, model.getFieldsOne());

                //regelt, dass soviele Felder ausgewählt werden, wie gewürfelt wurden
                if(model.getRemainingMoves() == 0) {
                    if(model.checkColumnsAfterMove(model.getFieldsOne(), model.getColumnsOne(), model.getColumnsTwo())) {
                        model.setGameState(GameState.END);
                    } else {
                        model.setGameState(GameState.TWOROLLTHEDICE);
                        model.getFieldsOfThisMove().clear();
                        model.setSelectedColorDice(-1);
                        model.setSelectedNumberDice(-1);
                    }
                }
                break;
            //Spieler 2 würfelt
            case TWOROLLTHEDICE:
                if((0 <= mouseX && mouseX <= 99) && (0 <= mouseY && mouseY <= 399)) {
                    model.rollTheDice();
                    model.setGameState(GameState.TWOSELECTCOLOR);
                } else {
                    model.setInvalidField(true);
                }
                break;
            //Spieler 2 wählt Farbe
            case TWOSELECTCOLOR:
                if((10 <= mouseX && mouseX <= 60) && (10 <= mouseY && mouseY <= 60)) {
                    selectedColorDice = 0;
                } else if((10 <= mouseX && mouseX <= 60) && (70 <= mouseY && mouseY <= 120)) {
                    selectedColorDice = 1;
                } else if((10 <= mouseX && mouseX <= 60) && (130 <= mouseY && mouseY <= 180)) {
                    selectedColorDice = 2;
                }

                //Kann-Nicht-Button
                if(10 <= mouseX && mouseX <= 210 && 530 <= mouseY && mouseY <= 630) {
                    model.setGameState(GameState.ONEROLLTHEDICE);
                    model.setSelectedColorDice(-1);
                    model.setSelectedNumberDice(-1);
                    model.setInvalidField(false);
                    break;
                }

                //Wenn Würfel ausgewählt, weiter im Spiel
                if(model.selectAColorDice(selectedColorDice)) {
                    model.setGameState(GameState.TWOSELECTNUMBER);
                }
                break;
            //Spieler 2 wählt Nummer
            case TWOSELECTNUMBER:
                if((10 <= mouseX && mouseX <= 60) && (190 <= mouseY && mouseY <= 240)) {
                    selectedNumberDice = 0;
                } else if((10 <= mouseX && mouseX <= 60) && (250 <= mouseY && mouseY <= 300)) {
                    selectedNumberDice = 1;
                } else if((10 <= mouseX && mouseX <= 60) && (310 <= mouseY && mouseY <= 360)) {
                    selectedNumberDice = 2;
                }

                //Kann-Nicht-Button
                if(10 <= mouseX && mouseX <= 210 && 530 <= mouseY && mouseY <= 630) {
                    model.setGameState(GameState.ONEROLLTHEDICE);
                    model.setSelectedColorDice(-1);
                    model.setSelectedNumberDice(-1);
                    model.setInvalidField(false);
                    break;
                }

                //Wenn Würfel ausgewählt, weiter im Spiel
                if(model.selectANumberDice(selectedNumberDice)) {
                    model.setGameState(GameState.TWOSELECTFIELD);
                }
                break;
            //Spieler 2 wählt Feld
            case TWOSELECTFIELD:
                //Finde Spalte des ausgewählten Feldes
                if(890 <= mouseX && mouseX <= 939) {
                    column = 0;
                } else if(940 <= mouseX && mouseX <= 989) {
                    column = 1;
                } else if(990 <= mouseX && mouseX <= 1039) {
                    column = 2;
                } else if(1040 <= mouseX && mouseX <= 1089) {
                    column = 3;
                } else if(1090 <= mouseX && mouseX <= 1139) {
                    column = 4;
                } else if(1140 <= mouseX && mouseX <= 1189) {
                    column = 5;
                } else if(1190 <= mouseX && mouseX <= 1239) {
                    column = 6;
                } else if(1240 <= mouseX && mouseX <= 1289) {
                    column = 7;
                } else if(1290 <= mouseX && mouseX <= 1339) {
                    column = 8;
                } else if(1340 <= mouseX && mouseX <= 1389) {
                    column = 9;
                } else if(1390 <= mouseX && mouseX <= 1439) {
                    column = 10;
                } else if(1440 <= mouseX && mouseX <= 1489) {
                    column = 11;
                } else if(1490 <= mouseX && mouseX <= 1539) {
                    column = 12;
                } else if(1540 <= mouseX && mouseX <= 1589) {
                    column = 13;
                } else if(1590 <= mouseX && mouseX <= 1639) {
                    column = 14;
                }

                //Finde Zeile des ausgewählten Feldes
                if(60 <= mouseY && mouseY <= 109) {
                    row = 0;
                } else if(110 <= mouseY && mouseY <= 159) {
                    row = 1;
                } else if(160 <= mouseY && mouseY <= 209) {
                    row = 2;
                } else if(210 <= mouseY && mouseY <= 259) {
                    row = 3;
                } else if(260 <= mouseY && mouseY <= 309) {
                    row = 4;
                } else if(310 <= mouseY && mouseY <= 359) {
                    row = 5;
                } else if(360 <= mouseY && mouseY <= 409) {
                    row = 6;
                }

                //Kann-Nicht-Button
                if(10 <= mouseX && mouseX <= 210 && 530 <= mouseY && mouseY <= 630) {
                    model.setGameState(GameState.ONEROLLTHEDICE);
                    for(Field field : model.getFieldsOfThisMove()) {
                        model.getFieldsTwo()[field.getColumn()][field.getRow()].setOccupied(false);
                    }
                    model.getFieldsOfThisMove().clear();
                    model.setSelectedColorDice(-1);
                    model.setSelectedNumberDice(-1);
                    model.setInvalidField(false);
                    break;
                }

                model.selectAField(column, row, model.getFieldsTwo());

                //Regelt, dass soviele Felder ausgewählt werden, wie gewürfelt wurden
                if(model.getRemainingMoves() == 0) {
                    if(model.checkColumnsAfterMove(model.getFieldsTwo(), model.getColumnsTwo(), model.getColumnsOne())) {
                        model.setGameState(GameState.END);
                    } else {
                        model.setGameState(GameState.ONEROLLTHEDICE);
                        model.getFieldsOfThisMove().clear();
                        model.setSelectedColorDice(-1);
                        model.setSelectedNumberDice(-1);
                    }
                }
                break;
        }
    }

    /**
     * Setzt das Model.
     * @param model Model
     */
    public void setModel(Model model) {
        this.model = model;
    }

    /**
     * Setzt den View
     * @param view View
     */
    public void setView(IView view) {
        this.view = view;
    }
}
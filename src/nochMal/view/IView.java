package nochMal.view;

import nochMal.model.Color;
import nochMal.model.Column;
import nochMal.model.Field;
import nochMal.model.GameState;

/**
 * Interface des Views
 */
public interface IView {
    void drawGame(Color colorDiceOne, Color colorDiceTwo, Color colorDiceThree, int selectedColorDice, int numberDiceOne, int numberDiceTwo, int numberDiceThree, int selectedNumberDice, Field[][] fieldsOne, Field[][] fieldsTwo, Column[] ColumnsOne, Column[] ColumnsTwo, GameState gameState, boolean invalidField);
    void drawEnd(int pointsPlayerOne, int pointsPlayerTwo);
}

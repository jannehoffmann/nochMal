package nochMal.model;

/**
 * Repräsentiert eine Spalte in einem Spiel, mit Punkten, Besetzungsstatus und weiteren Eigenschaften.
 * Beispiel:
 * var c = new Column(3, 2, 1);
 * boolean b = c.isOccupiedByMe();
 */
public class Column {
    private int number;
    private int pointsFirst;
    private int pointsSecond;
    private boolean occupiedByOther;
    private boolean occupiedByMe;
    private boolean firstOne;

    /**
     * Konstruktor, der eine neue Spalte mit gegebenen Parametern initialisiert.
     * Die Spalte ist initial weder vom Spieler noch von anderen besetzt.
     *
     * @param number Die Nummer der Spalte.
     * @param pointsFirst Die Punktzahl, wenn der Spieler die Spalte als Erster besetzt.
     * @param pointsSecond Die Punktzahl, wenn der Spieler die Spalte als Zweiter besetzt.
     */
    public Column(int number, int pointsFirst, int pointsSecond) {
        this.number = number;
        this.pointsFirst = pointsFirst;
        this.pointsSecond = pointsSecond;
        this.occupiedByOther = false;
        this.occupiedByMe = false;
        this.firstOne = false;
    }

    void setOccupiedByOther() {
        this.occupiedByOther = true;
    }

    void setOccupiedByMe() {
        this.occupiedByMe = true;
        if(!occupiedByOther) {
            firstOne = true;
        }
    }

    /**
     * Prüft, ob die Spalte von einem anderen Spieler besetzt ist.
     *
     * @return true, wenn die Spalte von einem anderen besetzt ist; sonst false.
     */
    public boolean isOccupiedByOther() {
        return occupiedByOther;
    }

    /**
     * Prüft, ob die Spalte vom Spieler besetzt ist.
     *
     * @return true, wenn die Spalte vom Spieler besetzt ist; sonst false.
     */
    public boolean isOccupiedByMe() {
        return occupiedByMe;
    }

    /**
     * Prüft, ob der Spieler die Spalte als Erster besetzt hat.
     *
     * @return true, wenn der Spieler die Spalte als Erster besetzt hat; sonst false.
     */
    public boolean isFirstOne() {
        return firstOne;
    }

    /**
     * Gibt die Punktzahl zurück, die der Spieler erhält, wenn er die Spalte als Erster besetzt.
     *
     * @return Die Punktzahl für die erste Besetzung der Spalte.
     */
    public int getPointsFirst() {
        return pointsFirst;
    }

    /**
     * Gibt die Punktzahl zurück, die der Spieler erhält, wenn er die Spalte als Zweiter besetzt.
     *
     * @return Die Punktzahl für die zweite Besetzung der Spalte.
     */
    public int getPointsSecond() {
        return pointsSecond;
    }

    /**
     * Gibt eine String-Repräsentation der Spalte zurück.
     *
     * @return Eine String-Repräsentation der Spalte, die Nummer, Punkte und Besetzungsstatus enthält.
     */
    @Override
    public String toString() {
        return "Column{" +
                "number=" + number +
                ", pointsFirst=" + pointsFirst +
                ", pointsSecond=" + pointsSecond +
                ", occupiedByOther=" + occupiedByOther +
                ", occupiedByMe=" + occupiedByMe +
                ", firstOne=" + firstOne +
                '}';
    }
}

package nochMal.model;

/**
 * Repräsentiert ein Feld auf einem Spielbrett mit einer Farbe, Spalte, Zeile und einem Besetzungsstatus.
 * Beispiel:
 * var f = new Field(Color.GREEN, 0, 1);
 * f.setOccupied(true);
 */
public class Field {
    private Color color;
    private int column;
    private int row;
    private boolean isOccupied;

    /**
     * Konstruktor, der ein neues Feld mit einer spezifischen Farbe, Spalte und Zeile initialisiert.
     * Das Feld ist initial nicht besetzt (isOccupied ist false).
     *
     * @param color  Die Farbe des Feldes.
     * @param column Die Spaltennummer des Feldes.
     * @param row    Die Zeilennummer des Feldes.
     */
    public Field(Color color, int column, int row) {
        this.color = color;
        this.column = column;
        this.row = row;
        this.isOccupied = false;
    }

    /**
     * Setzt den Besetzungsstatus des Feldes.
     *
     * @param b Der neue Besetzungsstatus des Feldes (true, wenn besetzt; sonst false).
     */
    public void setOccupied(boolean b) {
        isOccupied = b;
    }

    /**
     * Gibt die Farbe des Feldes zurück.
     *
     * @return Die Farbe des Feldes.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Gibt die Spaltennummer des Feldes zurück.
     *
     * @return Die Spaltennummer des Feldes.
     */
    public int getColumn() {
        return column;
    }

    /**
     * Gibt die Zeilennummer des Feldes zurück.
     *
     * @return Die Zeilennummer des Feldes.
     */
    public int getRow() {
        return row;
    }

    /**
     * Gibt den Besetzungsstatus des Feldes zurück.
     *
     * @return true, wenn das Feld besetzt ist; sonst false.
     */
    public boolean isOccupied() {
        return isOccupied;
    }

    /**
     * Gibt eine String-Repräsentation des Feldes zurück.
     *
     * @return Eine String-Repräsentation des Feldes, die Farbe, Spalte, Zeile und Besetzungsstatus enthält.
     */
    @Override
    public String toString() {
        return "Field{" +
                "color=" + color +
                ", column=" + column +
                ", row=" + row +
                ", isOccupied=" + isOccupied +
                '}';
    }
}

package nochMal;

import processing.core.PApplet;
import nochMal.model.Model;
import nochMal.controller.Controller;
import nochMal.view.View;

/**
 * Main-Klasse, welche M, V und C verbindet und Spiel startet.
 */
public class Main {
    /**
     * main-Methode, welche Spiel startet.
     * @param args args
     */
    public static void main(String[] args) {
        var model = new Model();
        var controller = new Controller();
        var view = new View();

        // Verbinde M, V und C
        controller.setModel(model);
        controller.setView(view);
        view.setController(controller);

        // Starte die Processing Anwendung
        PApplet.runSketch(new String[]{"View"}, view);
    }
}

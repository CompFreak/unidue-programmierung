/*
 * Created by Universität Duisburg-Essen.
 */
/*
 * Dieses Programm demonstriert die Verwendung der ledControl-Bibliothek.
 * Als erstes mu� der Controller erstellt werden, danach wird das Array mit den
 * Farbinformationen vom Controller angefordert. Dieses Array wird immer wieder
 * mit neuen Informationen beschrieben. Sobald ein Bild fertig ist, wird eine
 * Pause gemacht und dann ein Update an den LED-Streifen gesendet.
 */

package com.oppahansi.ws1415.uebungsblatt07.lightshow;

// ledControl is located inside the res/ledControl/ folder - add it to your project as library.

import ledControl.BoardController;

public class LightShowBasicExample {

  public static void main(String[] args) {

    // Anfordern des BoardControllers
    BoardController controller = BoardController.getBoardController();

    // alle LEDs auf ein schwaches weiss setzen.
    // mit x werden die Spalten (x-Koordinate) durchlaufen
    for (int x = 0; x < controller.getWidth(); x++) {
      // mit y werden die Reihen (y-Koordinate) durchlaufen
      for (int y = 0; y < controller.getHeight(); y++) {
        // Die Farbe der LED an der Koordinate (x, y) wird gesetzt.
        // Jede einzelne der drei Farbkomponenten ([rot, gruen, blau]
        // oder auch [RGB]) wird auf 30 gesetzt.
        controller.setColor(x, y, 30, 30, 30);
      }
    }

    // nachdem das Board die gewuenschten Farbinformationen enthaelt, werden
    // diese Informationen an die LEDs weitergegeben.
    controller.updateLedStripe();

    // den Punkt mit der Koordinate (4,5) rot faerben
    // Farbwert fuer rot setzen (Maximum = 127)
    // Farbwert fuer gruen setzen (Minimum = 0)
    // Farbwert fuer blau setzen (Minumum = 0)
    controller.setColor(5, 5, 127, 0, 0);

    // 3000 Millisekunden seit dem letzten LEDUpdate warten
    controller.sleep(3000);

    // Update der LEDs
    // Danach sollte inmitten der weiss leuchtenden LEDs ein strahlender
    // roter Punkt sichtbar sein
    controller.updateLedStripe();

    // den Punkt mit der Koordinate (4,5) orange-rot faerben
    // durch Beimischung von etwas gruen wird das rot zu orange
    // Farbwert fuer rot setzen (Maximum = 127)
    // Farbwert fuer gruen setzen (60)
    // Farbwert fuer blau setzen (Minumum = 0)
    controller.setColor(5, 5, 127, 60, 0);

    // 3000 Millisekunden seit dem letzten LEDUpdate warten
    controller.sleep(3000);

    // Update der LEDs
    // Der rote Punkt sollte nun orange erscheinen
    controller.updateLedStripe();

    // vier weitere LEDs rot faerben, so dass in der Mitte nun ein rotes
    // Kreuz mit orangem Zentrum sichtbar ist.
    controller.setColor(4, 5, 127, 0, 0);
    controller.setColor(6, 5, 127, 0, 0);
    controller.setColor(5, 4, 127, 0, 0);
    controller.setColor(5, 6, 127, 0, 0);

    // Warten bis 3 Sekunden verstrichen sind...
    controller.sleep(3000);
    // Update
    controller.updateLedStripe();

    // das Bild loeschen, bzw alle Farbwerte auf null setzen
    controller.resetColors();
    // wieder das rote Kreuz einfaerben.
    controller.setColor(4, 5, 127, 0, 0);
    controller.setColor(5, 5, 127, 60, 0);
    controller.setColor(6, 5, 127, 0, 0);
    controller.setColor(5, 4, 127, 0, 0);
    controller.setColor(5, 6, 127, 0, 0);

    // abwarten...
    controller.sleep(3000);
    // Nach diesem Update sollte das rote Kreuz vor diesmal dunklem Hinter-
    // grund sichtbar sein.
    controller.updateLedStripe();

    controller.sleep(3000);

    // Bild loeschen und mit dem folgenden Update die LEDs abschalten.
    controller.resetColors();
    controller.updateLedStripe();
  }

}

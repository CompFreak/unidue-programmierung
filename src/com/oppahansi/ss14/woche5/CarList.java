package com.oppahansi.ss14.woche5;

/*
 * In dieser Uebung werden weitere Konzepte in Verbindung mit Listen
 * eingefuehrt. Die CarList soll eine aehnliche Funktionalitaet wie die Queue
 * haben. Autos werden am Ende der Liste eingefuegt und nur am Beginn der Liste
 * entfernt. Im Gegensatz zur Queue sollen ausserdem Elemente an beliebigen
 * Positionen der Liste zurueckgegeben werden, ohne das die Elemente dabei
 * entfernt werden.
 * 
 * Autos sollen nicht direkt in die Liste eingefuegt werden, sondern mit Hilfe
 * der Klasse CarNode. Diese Klasse stellt ein Listenelement mit Vorgaenger,
 * Nachfolger und einem Objekt der Klasse Car dar.
 * 
 * Im Vergleich zu den Aufgaben Queue und Schlumpfliste verfuegt die Klasse
 * CarList zusaetzlich ueber zwei feste CarNodes start und end. Diese Elemente
 * werden nie geloescht und sind auch in einer leeren Liste vorhanden. Objekte
 * des Typs Car sollen diesen beiden speziellen CarNodes NIEMALS zugeordnet
 * werden. In einer leeren Liste verweisen die beiden Elemente, wie im
 * Konstruktor zu erkennen, aufeinander. Die Verwendung dieser beiden fixen
 * Listenelemente soll die Programmierung vereinfachen, da fuer die
 * Implementierung der Listenoperationen die Sonderfaelle (Leere Listen, Listen
 * mit nur einem Element) vereinfacht werden.
 * 
 * Ausserdem soll diese Liste doppelt verkettet sein, d.h. die CarNode-Elemente
 * verfuegen nicht nur ueber Nachfolger, sondern auch ueber Vorgaenger.
 * 
 * Eine leere CarList hat folgende Struktur:
 * start <--> end
 * 
 * Eine CarList mit zwei Element sollte so aussehen:
 * start <--> CarNode(1) <--> CarNode(2) <--> end
 */

/*
 * Codevorlage nicht mein Eigentum.
 * Codevorlage entstand an der Universität Duisburg-Essen am Lehrstuhl für Angewandte Informatik.
 * Benötigt für die Bearbeitung der CarList Aufgabe - Listen
 * Implementierung der Methoden von Oppa Hansi.
 */

public class CarList {

    /*
     * Diese beiden Elemente sollen immer in der Liste vorhanden sein und
     * waehrend der gesamten Lebenszeit der Liste nicht durch andere Objekte des
     * Typs CarNode ausgetauscht werden. Aus diesem Grund sind start und end
     * auch mit dem Schluesselwort final versehen. Der Inhalt der beiden
     * Elemente kann (und muss) trotzdem veraendert werden.
     */
    final CarNode start;
    final CarNode end;

    public CarList() {
        start = new CarNode();
        end = new CarNode();
        start.next = end;
        end.previous = start;
    }

	
	/*
     * BEGINN des zu bearbeitenden Codes
	 */

    /*
     * Eigene Hilfsmethode zur Bestimmung der "Länge" der Liste
     * geschrieben von oppahansi
     *
     * Hier bestimme ich anhand der ListenElemente wie lang die Liste ist
     */
    public int getSize() {
        if (start.equals(end)) {
            return 0;
        } else {
            CarNode newNode = start.getNext();
            int counter = 0;
            while (newNode != end) {
                counter++;
                newNode = newNode.getNext();
            }
            return counter;
        }
    }

    /*
     * Mittels dieser Methode soll ein neues Auto (Parameter newCar) am Ende der
     * Liste eingefuegt werden. Dazu muss ein neues Objekt der Klasse CarNode
     * erzeugt werden.
     *
     * Struktur der Liste vorher:
     * start <--> ... <--> end
     *
     * nachher:
     * start <--> ... <--> CarNode(newCar) <--> end
     */
    public void addCar(Car newCar) {
		/*
		 * Schauen, ob die Liste leer ist.
		 */
        if (start.getNext().equals(end)) {
            CarNode newCarNode = new CarNode(newCar, start, end);
            start.setNext(newCarNode);

            // Die zwei Zeilen kann man theoretisch weglassen.
            newCarNode.setPrevious(start);
            newCarNode.setNext(end);

            end.setPrevious(newCarNode);
		/*
		 * Falls die Liste nicht leer ist
		 */
        } else {
			/*
			 * Kleiner Hinweis: Nodes / CarNodes sind Elemente einer Liste - diese
			 * Elemente enthalten dann die Verweise previous und next
			 * Außerdem auch das Object Car. 
			 * 
			 * Hier also erstelle ich 2 Hilfsnodes newCarNode, dass ich einfügen werde
			 * und newNode das den aktuell letzten Element darstellt.
			 * Jetzt muss ich nur noch die Vorgaenger und Nachfolger umhaengen.
			 */
            CarNode newCarNode = new CarNode(newCar, end.getPrevious(), end);
            CarNode newNode = end.getPrevious();
            // Das aktuell letzte Element zeigt jetzt auf das neue newCarNode
            newNode.setNext(newCarNode);
            // Jetzt muss ich noch newNode als Vorgaenger von dem newCarNode setzten
            newCarNode.setPrevious(newNode);
            // Und den Nachfolger von newCarNode auf end setzen
            newCarNode.setNext(end);
            // Und Ende der liste auf das letzte Element setzen
            end.setPrevious(newCarNode);
        }
		
		/* 
		 * Genau hier liegt das Prinzip - Nachfolger und Vergaenger richtig
		 * umhaengen und setzen. 
		 */
    }


    /*
     * Mittels dieser Methode soll das vorderste Element aus der Liste zurueck-
     * gegeben und aus der Liste entfernt werden. Ist die Liste leer, soll null
     * zurueckgegeben werden.
     *
     * vorher:
     * start <--> CarNode(1) <--> CarNode(2) <--> ... <--> end
     *
     * nachher:
     * start <--> CarNode(2) <--> ... <--> end
     */
    public Car removeFirstCar() {
        // Fall 1: Liste ist leer
        if (this.getSize() == 0) {
            return null;

            // Fall 2: Liste hat genau 1 Element
        } else if (this.getSize() == 1) {
            // In diesem Fall kann man start direkt auf end
            // setzen
            CarNode newNode = start.getNext();
            start.setNext(end);
            end.setPrevious(start);
            // Und Car zurückgeben, welches in dem
            // Listenelement ist
            return newNode.getCar();
            // Fall 3: Liste hat mehr als nur 1 Element
        } else {
            // In disem Fall braucht ich ein weiteres Element, den Nachfolger
            // NewNode ist das erste Element und hat das Car-Element, dass
            // wir zurückgeben müssen
            CarNode newNode = start.getNext();
            // Nachfolger, der an die Stelle von newNode kommt
            CarNode next = newNode.getNext();
            // erste Stelle neu setzen
            start.setNext(next);
            // Vorgaenger von next auf start setzen - da es nun an der 1. Stelle ist
            next.setPrevious(start);
            // Und Car zurückgeben, welches in dem
            // Listenelement ist
            return newNode.getCar();
        }
    }


    /*
     * Diese Methode soll das Auto an der durch den Parameter position gegebenen
     * Stelle zurueckgeben, aber nicht aus der Liste entfernen. Das erste Auto
     * in der Liste soll mit der Position 1 identifiziert werden, nicht 0 wie
     * bei einem Array. Existiert das Element nicht, weil die Liste kuerzer ist
     * als es fuer den Parameter notwendig waere, soll die Rueckgabe null sein.
     *
     * start <--> CarNode(Ludolf-1000) <--> CarNode(Bonzokrat-CL) <--> end
     *
     * getCar(2) liefert das Auto Bonzokrat-CL
     */
    public Car getCar(int position) {
        if (position <= 0 || position > this.getSize()) {
            return null;
        } else {
            CarNode newNode = start.getNext();
            int counter = 1;
            while (!newNode.equals(end)) {
                if (position == counter) {
                    return newNode.getCar();
                }
                counter++;
                newNode = newNode.getNext();
            }
            return null;
        }
    }

    /*
     * Eigene Hilfsmethode getNodeAt(int pos)
     * Gibt eine CarNode zurück an der Stelle pos
     */
    public CarNode getCarNodeAt(int position) {
        if (position <= 0 || position > this.getSize()) {
            return null;

        } else {
            CarNode newNode = start.getNext();
            int counter = 1;
            while (!newNode.equals(end)) {
                if (position == counter) {
                    return newNode;
                }
                counter++;
                newNode = newNode.getNext();
            }
            return null;
        }
    }


    /*
     * Der Aufruf dieser Methode soll das Auto an der durch den Parameter
     * position gegebenen Stelle um einen Platz in der Liste vorruecken. Auch
     * hier gilt, dass das erste Auto an der Stelle 1 steht.
     * Verweist der Parameter bereits auf die erste Stelle oder auf eine nicht
     * in der Liste vorhandene Position, soll die Liste unveraendert bleiben.
     *
     * start <--> CarNode(1) <--> CarNode(2) <--> CarNode(3) <--> end
     *
     * prioritizeCar(3);
     *
     * start <--> CarNode(1) <--> CarNode(3) <--> CarNode(2) <--> end
     *
     */
    public void prioritizeCar(int position) {
        if (position <= 1 || position > this.getSize()) {

        } else if (position == 2) {
            CarNode node = start.getNext().getNext();
            CarNode previous = start.getNext();
            CarNode next = node.getNext();
            start.setNext(node);
            node.setPrevious(start);
            node.setNext(previous);
            previous.setPrevious(node);
            previous.setNext(next);
            next.setPrevious(previous);

        } else {
            CarNode node = start.getNext();

            for (int i = 1; i < position; i++) {
                node = node.getNext();
            }

            CarNode previous = node.getPrevious();
            CarNode next = node.getNext();
            CarNode preprevious = previous.getPrevious();

            preprevious.setNext(node);
            node.setPrevious(preprevious);
            node.setNext(previous);
            previous.setPrevious(node);
            previous.setNext(next);
            next.setPrevious(previous);

        }
    }


    /*
     * Mit dieser Methode sollen alle Autos einer Liste in der gleichen Reihen-
     * folge am Ende in Liste eingefuegt werden. Die als Parameter uebergebene
     * Liste soll danach leer sein.
     *
     * listA: start <--> CarNode(1A) <--> CarNode(2A) <--> end
     * listB: start <--> CarNode(1B) <--> CarNode(2B) <--> end
     *
     * listA.appendList(listB);
     *
     * listA: start <--> CarNode(1A) <--> CarNode(2A) <--> CarNode(1B) <-->
     * 					 CarNode(2B) <--> end
     * listB: start <--> end
     */
    public void appendList(CarList newList) {
        if (newList == null) {

        } else {
            for (int i = 1; i <= newList.getSize(); i++) {
                this.addCar(newList.getCarNodeAt(i).getCar());
            }
            newList.start.next = newList.end;
            newList.end.previous = newList.start;
        }
    }


    /*
     * Nach Aufruf dieser Methode sollen in der Liste nur noch beschaedigte
     * Autos vorhanden sein. Alle unbeschaedigten Autos muessen aus der Liste
     * entfernt werden.
     * Verwenden Sie die Methode Car.isDamaged() der Klasse Car um
     * herauszufinden, ob ein Auto beschaedigt ist.
     */
    public void filterIntactCars() {
        boolean first = true;

        CarNode node = start.getNext();

        while (!node.equals(end)) {
            if (first == true && node.getCar().isDamaged() == false) {
                start.setNext(node.getNext());
                node.getNext().setPrevious(start);
                first = false;

            } else if (node.getCar().isDamaged() == false && node.getNext().equals(end)) {
                CarNode previous = node.getPrevious();
                previous.setNext(end);
                end.setPrevious(previous);

            } else if (node.getCar().isDamaged() == false) {
                CarNode previous = node.getPrevious();
                CarNode next = node.getNext();
                previous.setNext(next);
                next.setPrevious(previous);
            }
            node = node.getNext();
            first = false;
        }
    }

	/*
	 * ENDE des zu bearbeitenden Codes
	 */


    /*
     * Verwenden Sie diese Methode um den Inhalt einer Autoliste auf der Konsole
     * auszugeben.
     */
    public static void printCarList(CarList list) {
        CarNode iter = list.start;
        int pos = 0;
        while (iter.next != list.end) {
            iter = iter.next;
            pos++;
            System.out.print("[" + pos + ": " + iter.car.getManufacturer() + " "
                    + iter.car.getModel() + "]");
            if (iter.next != list.end) {
                System.out.print(" <--> ");
            }
        }
        System.out.println();
    }


    /*
     * Hier kommt ihr Testcode hin.
     */
    public static void main(String[] args) {
        CarList list = new CarList();
        CarList list2 = new CarList();
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        Car car4 = new Car();
        Car car5 = new Car();
        Car car6 = new Car();
        Car car7 = new Car();
        Car car8 = new Car();
        car1.setManufacturer("erstes");
        car2.setManufacturer("zweites");
        car3.setManufacturer("drittes");
        car4.setManufacturer("viertes");
        car5.setManufacturer("fuenftes");
        car6.setManufacturer("sechstes");
        car7.setManufacturer("siebtes");
        car8.setManufacturer("achtes");
        list.addCar(car1);
        list.addCar(car2);
        list.addCar(car3);
        list.addCar(car4);
        list.addCar(car5);
        list.addCar(car6);
        list.addCar(car7);
        list.addCar(car8);
        list2.addCar(car6);
        list2.addCar(car7);
        list2.addCar(car8);
        System.out.println("8 Autos hinzugefuegt: ");
        printCarList(list);
        list.removeFirstCar();
        System.out.println();
        System.out.println("1. Auto entfernt: ");
        printCarList(list);
        System.out.println();
        System.out.println("5. Auto bevorzugen: ");
        list.prioritizeCar(3);
        printCarList(list);
        System.out.println();
        System.out.println("List 1");
        printCarList(list);
        System.out.println("List 2");
        printCarList(list2);
        System.out.println();
        System.out.println("Liste 2 and Liste anhaengen: ");
        list.appendList(list2);
        System.out.println();
        printCarList(list);
        printCarList(list2);

        System.out.println();

        list.getCar(2).crash();
        list.getCar(3).crash();
        list.getCar(1).repair();
        list.getCar(4).repair();
        list.getCar(5).repair();
        list.getCar(6).repair();
        list.getCar(7).repair();
        list.getCar(8).repair();
        list.getCar(9).repair();
        list.getCar(10).repair();


        list.filterIntactCars();
        System.out.println("Autos filtern - nun sind nur beschädigte Autos in der Liste");
        printCarList(list);

    }

}

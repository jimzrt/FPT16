# FPT16

Aufgabe 1

Model, View und Controller

Zunächst soll das Grundgerüst des Programms mithilfe des MVC-Architekturmusters implementiert werden.
Verwenden Sie dazu JavaFX. Durch das darin enthaltene Observer-Pattern und durch die lambda Ausdrücke,
die seit Java8 möglich sind, ist das MVC Muster in nur wenigen Zeilen Code realisierbar.

In der Übung werden wir auf alle nötigen Komponenten wie StringProperty, ListView, TableView, TableCo-
lumn, HBox, BorderPane, ToolBar, Button, TextField und Label eingehen.

a) Erzeugen Sie die Klassen Product, ProductList und Order, die die gleichnamigen Interfaces aus dem Paket
fpt.com implementieren.
Die Klasse Product soll als Repräsentation eines Produkts dienen und beinhaltet den Namen, die ID,
den Preis und die vorhandene Stückzahl eines Produkts.
Die Klasse Order enthält zu den Waren auch die bestellte Anzahl und deren Preis.
Bei den Klassen Order, ProductList kann es sich um eine Ableitung der Klasse java.util.ArrayList handeln,
die ausschließlich Waren enthält.
Achten Sie darauf bereits hier Property-Objekte zu erstellen, da Sie diese für die GUI benötigen und
sich dadurch viel Arbeit sparen (z.B. SimpleStringProperty).

b) Schreiben Sie die Klasse ModelShop, welche die Daten von der GUI hält.

Diese Klasse soll von ModiableObservableListBase ableiten und durch Nutzung eines Delegate (Product-
List) die nötigen Funktionen des Interfaces ProductList realisieren.

c) Die Klasse ViewShop dient als Benutzerschnittstelle für den Shop.

Es sollen die eingetragenen Produkte angezeigt werden.

d) Schreiben Sie die Klasse ControllerShop, welche für die Verarbeitung der Benutzereingaben des Händlers
zuständig sein soll. Diese Klasse bindet auch die nötigen Objekte aus dem ModelShop an die Elemente
der View.

e) Es soll möglich sein neue Produkte einzufügen und bestehende Produkte zu löschen. Erweitern Sie ihre
Klasse ViewShop um die dafür nötigen GUI-Elemente.
Implementieren Sie auÿerdem die nötige Funktionalität.
Prüfen Sie die zu machenden Eingaben so früh wie möglich, um Fehler zur Laufzeit zu vermeiden.
Defekte Programme bzw. Fehler bei der Abnahme führen zu Punktabzug.


Aufgabe 2


Bestellformular ViewCustomer

Schreiben Sie hier nur die grasche Oberäche ohne Funktionalität.
(Die Implementierung der Klasse ControllerCustomer wird in späteren Aufgaben folgen.)

a) Die Klasse ViewCustomer soll alle Produkte so anzeigen, dass der Name, der Preis und dessen Anzahl
sichtbar sind. Auÿerdem soll die Möglichkeit bestehen, eine Stückzahl für die Bestellung zu wählen.

b) Die Anzeige soll zudem eine Historie der getätigten Bestellungen anzeigen.


Aufgabe 3


Bauen Sie die Serialisierung an beliebiger Stelle in das Shop-System ein. Für die Auswahl der Strategie
könnte beispielsweise ein Menü oder ein Dialog - beim Schließen bzw. Öﬀnen des Programms - verwendet
werden. Sie haben bereits ein einfaches Produkt und eine dazugehörige Produktliste implementiert. Passen
Sie ihre Implementierung an die unten genannten Kriterien an, sofern diese denen noch nicht entsprechen.

a) Erstellen Sie die Klasse IDGenerator, die Ihnen immer eine gültige ID gibt.
Diese darf nicht mehrfach existieren.
Der Generator startet mit dem Wert 1 und generiert einen maximalen Wert von 999999.

b) Sorgen Sie dafür, dass jedes Produkt eine eindeutige ID bekommt.

c) Wird der Maximalwert überschritten, so muss eine Exception geworfen werden.
Schreiben Sie die Exception ID-Overﬂow und bauen Sie diese in ihrem Generator ein.

d) Ermöglichen Sie dem Anwender die Auswahl der Strategien sowie die Aktionen zum Laden/Speichern.


Aufgabe 4


Bei der Objektserialisierung ist zu beachten, dass nicht nur das eigentliche Objekt serialisiert wird, sondern
auch alle Objekte, die dieses Objekt referenziert. Der Fachausdruck dafür lautet ’Persistenz durch Erreich-
barkeit’. Ihre Warenliste sollte mindestens 5 Objekte enthalten.

a) Welche Kriterien muss eine Klasse erfüllen, damit Objekte dieser serialisiert werden können?

b) Implementieren sie das Interface java.io.Externalizable und die damit verbundenen Methoden in der
Klasse Product die in Blatt 1 Implementiert werden sollte.

c) Entwerfen Sie die Klasse BinaryStrategy, die das Interface fpt.com.SerializableStrategy implementiert.

d) Warum können Simple-Properties wie SimpleStringProperty nicht Serialisiert werden?

e) Serialisieren und deserialisieren Sie die Objekte in eine Datei products.ser.

f) Welche Probleme können entstehen, wenn die Klassen nach der Serialisierung verändert werden?
Provozieren Sie einen Fehler, der durch die Deserialisierung einer nach der Serialisierung geänderten
Klasse entsteht. Welche Möglichkeiten gibt es, um diesen Fehler zu verhindern?

g) Wie hängen serialVersionUID und die Klasse zusammen?

h) Wie kann man durch die Serialisierung ein Objekt kopieren?


Aufgabe 5


Nutzen Sie die XML-Serialisierung, um ﬂexibler in der Datenhaltung zu werden. Verwenden Sie dafür die in
Java vorhandenen Klassen java.beans.XMLEncoder bzw. java.beans.XMLDecoder.

a) Schreiben Sie die Klasse XMLStrategy, die das Interface fpt.com.SerializableStrategy implementiert.

b) Serialisieren und deserialisieren Sie die Objekte in eine Datei products.xml

c) Wie unterscheiden sich die Arten der Serialisierung?

d) Nennen Sie Nachteile bzw. Vorteile der Ihnen bekannten Serialisierungsmechanismen.


Aufgabe 6


XStream XML-Serialisierung 3-Punkte

Ihnen liegt nun folgendes XML Format vor.

<waren>
  <ware id="1470706">
    <name>test</name>
    <preis>3.00</preis>
    <anzahl>3</anzahl>
  </ware>
  <ware id="1470707">
    <name>test</name>
    <preis>3.00</preis>
    <anzahl>3</anzahl>
  </ware>
<waren>

Sie sollen das Format lesen und schreiben können. Nutzen Sie dazu XStream. Zur Erzeugung des XStream-
Objektes steht ihnen im Interface fpt.com.SerializableStrategy eine passende create Methode zur Verfügung.
Bitte denken Sie daran, alle Attribute der Objekte zu speichern.

a) Informieren Sie sich, wie Attribute erstellt werden und wie man Eigenschaften eines Objektes umbe-

nennen kann.

b) Informieren Sie sich, wie man ein Objekte konvertiert bzw. dekorieren kann.

c) Erstellen Sie die Klasse XStreamStrategy, die das Interface fpt.com.SerializableStrategy implementiert.

d) Die ID soll sechsstellig als Attribute im waren-Tag stehen. Wenn nötig mit führenden 0 auﬀüllen.

e) Der Preis ist mit zwei Nachkommastellen anzugeben und verwendet einen Punkt als Trenner.

f) Für alle Transformationen implementieren Sie den SingleValueConverter.
Auch Property-Objekte müssen transformiert werden.


Die neuen Properties (StringProperty, ...) sind nicht mehr standardmäßig Serialisierbar. Daher ist eine einfache binäre Serialisierung nicht mehr möglich.
Lösung: Der hier einfachste Weg ist Product mit dem Interface java.io.Externalizable anzupassen.

Verwendet man java.io.Externalizable ändert XStream sein verhalten. Durch
diese Anpassung ist es nicht mehr ohne weiteres möglich das oben genannte Format einzuhalten. Die
Klasse fpt.com.SerializableStrategy stellt daher eine Methode createXStream bereit, die das Verhalten
von XStream auf das alt bekannte umstellt.
Lösung: Ändern der Priorität der Converter, welche ein Objekt in XML Codieren.

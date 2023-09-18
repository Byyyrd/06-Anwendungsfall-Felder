package control;

import model.CompactDisc;

import java.util.Arrays;

/**
 * Created by Jean-Pierre on 21.10.2016.
 */
public class CDCollectionHandler {

    private CompactDisc[][] allCDs;

    /**
     * Die Anzahl an Platzgrößen gibt die Anzahl an CD-Ständern vor - hier 4.
     * Die CD-Ständer an sich sind so groß wie die jeweilige Platzgröße.
     *
     * @param amounts - Platzgrößen der einzelnen CD-Ständer.
     */
    public CDCollectionHandler(int[] amounts){
        //COMPLETE: 01 - Konstruktor fertigstellen.
        allCDs = new CompactDisc[4][];
        for(int i = 0; i < amounts.length;i++){
            allCDs[i] = new CompactDisc[amounts[i]];
        }
    }

    /**
     * Diese Methode dient dazu, eine neue CD zu instanziieren und sie an die vorgebene Stelle zu packen, sofern dort ein Platz frei ist.
     *
     * @param box - Index des gewählten CD-Ständers
     * @param place - Index des gewählten Platzes
     * @param artist - Künstername/Bandname
     * @param title - Albumtitel
     * @return - true, falls ein Platz frei war und die CD hinzugefügt werden konnte, sonst false.
     */
    public boolean addNewCD(int box, int place, String artist, String title){
        //COMPLETE: 02 - Hinzufügen einer CD
        if(allCDs[box][place] == null){
            allCDs[box][place] =  new CompactDisc(artist,title);
            return true;
        }
        return false;
    }

    /**
     * Diese Methode dient dazu, die Daten einer bestimmten Position im zweidimensionalem Array auszugeben.
     *
     * @param box - Index des gewählten CD-Ständers
     * @param place - Index des gewählten Platzes
     * @return - Entweder ein String-Array mit "Künstler" - "Titel" oder mit "Empty" - "Empty".
     */
    public String[] getInfo(int box, int place){
        //COMPLETE: 03 - Informationen zu einer bestimmen CD
        if(allCDs[box][place] != null){
            return new String[]{allCDs[box][place].getArtist(),allCDs[box][place] .getTitle() };
        }
        return new String[]{"Empty","Empty"};
    }

    /**
     * Diese Methode dient dem Entfernen einer CD.
     *
     * @param box - Index des gewählten CD-Ständers
     * @param place - Index des gewählten Platzes
     * @return - true, falls eine vorhandene CD entfernt wurde; false, falls keine CD zum Entfernen vorhanden war.
     */
    public boolean releaseCD(int box, int place){
        //COMPLETE: 04 - Entfernen einer bestimmten CD
        if(allCDs[box][place] != null){
            allCDs[box][place] = null;
            return true;
        }
        return false;
    }

    /**
     * Diese Methode dient dazu, die enthaltenen Daten aufzubereiten und als String-Array auszugeben.
     *
     * @param box - CD-Ständer, um den es sich handelt.
     * @return Ein Array, das abwechselnd den jeweiligen Künstler und den jeweiligen Albumtitel enthält. Leere Plätze werden mit "Empty" gefüllt.
     */
    public String[] getAllCDsFrom(int box){
        //COMPLETE: 05 - Vollständige Informationsausgabe aller CDs - Nach Fertigstellung im MainPanelHandler Zeile 165-167 entkommentieren
        String[] output = new String[allCDs[box].length * 2];
        for (int i = 0; i < output.length; i+=2) {
            output[i] = getInfo(box, i/2)[0];
            output[i+1] = getInfo(box,i/2)[1];
        }
        return output;
    }

    /**
     * Diese Methode dient dazu einen CD-Ständer zu komprimieren. Dabei rücken spätere CDs einfach auf. Die vorhandene Sortierung bleibt erhalten.
     *
     * @param box - Index des Gewählten CD-Ständers
     */
    public void pack(int box){
        //COMPLETE: 06 - Komprimieren eines CD-Ständers, von unten nach oben
        CompactDisc[] array = allCDs[box];
        /*int nullIndex = -1;
        for (int i = 0; i < array.length; i++) {
            if(array[i] == null && nullIndex == -1){
                nullIndex = i;
            }
            if(nullIndex != -1 && i > nullIndex){
                array[i - 1] = array[i];
                array[i] = null;
            }
        }*/
        for (int i = 1; i < array.length; i++) {
            if(allCDs[box][i - 1] == null){
                allCDs[box][i-1] = allCDs[box][i];
                allCDs[box][i] = null;
            }
        }

    }

    /**
     * Diese Methode dient dazu einen CD-Ständer zu sortieren nach Artist+Title. Gleichzeitig wird der CD-Ständer komprimiert.
     * Hierzu bitte nach der Methode "compareTo(String s)" für Java suchen.
     *
     * @param box - Gewählter CD-Ständer
     */
    public void sort(int box){
        //TODO: 07 - Sortieren eines CD-Ständers
    }
}

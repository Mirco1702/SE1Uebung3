package org.hbrs.se1.ws24.uebung3.control;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import org.hbrs.se1.ws24.uebung3.exceptions.PersistenceException;
import org.hbrs.se1.ws24.uebung3.exceptions.PersistenceException.ExceptionType;


public class PersistenceStrategyStream<E> implements PersistenceStrategy<E> {

    // URL of file, in which the objects are stored
    private String location = "objects.ser";

    // Backdoor method used only for testing purposes, if the location should be changed in a Unit-Test
    // Example: Location is a directory (Streams do not like directories, so try this out ;-)!
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    /**
     * Method for saving a list of Member-objects to a disk (HDD)
     */
    public void save(List<E> member) throws PersistenceException  {
        try (FileOutputStream fos = new FileOutputStream(location);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {

               // Schreiben der Liste in den Stream
               oos.writeObject(member);
               
           } catch (Exception e) { 
               throw new PersistenceException(ExceptionType.ConnectionNotAvailable, "Fehler beim Speichern der Member-Objekte: " + e.getMessage());
           }
       }
    

    @SuppressWarnings("unchecked")
	@Override
    /**
     * Method for loading a list of Member-objects from a disk (HDD)
     */
    public List<E> load() throws PersistenceException  {
        List<E> newListe = null; 

        try (FileInputStream fis = new FileInputStream(location);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            
            // Lesen des Objekts aus dem Stream
            Object obj = ois.readObject();
            
            // Überprüfen, ob das gelesene Objekt eine Liste ist
            if (obj instanceof List<?>) {
                newListe = (List<E>) obj; // Typumwandlung der Liste
            } else {
                throw new PersistenceException(ExceptionType.NoStrategyIsSet, "Gelesenes Objekt ist keine Liste.");
            }
        } catch (ClassNotFoundException e) {
            throw new PersistenceException(ExceptionType.ImplementationNotAvailable, "Klassendefinition nicht gefunden: " + e.getMessage());
        } catch (Exception e) { 
            throw new PersistenceException(ExceptionType.ConnectionNotAvailable, "Fehler beim Laden der Member-Objekte: " + e.getMessage());
        }

        return newListe; // Rückgabe der geladenen Liste
    }
}
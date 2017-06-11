package xfacteur;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.collections.ObservableList;

import xfacteur.model.Mailman;
import xfacteur.model.Shipment;

public class SerializationController {
	// inaccessible constructor
	protected SerializationController() {}

	// Output of Shipments
	public static void saveShipments(File file) {
		ObjectOutputStream oos = null;
		try {
			final FileOutputStream fileOut = new FileOutputStream(file);
			oos = new ObjectOutputStream(fileOut);
			oos.writeObject(ShipmentController.getItems());
			oos.flush();
		} catch (final IOException e) {
			System.out.println("I/O Exception while writing to file");
			e.printStackTrace();
		} finally {
			try {
				if (oos != null) {
					oos.flush();
					oos.close();
				}
			} catch (final IOException ex) {
				System.out.println("I/O Exception while writing to file");
				ex.printStackTrace();
			}
		}
	}

	// Output of Mailmen
	public static void saveMailmen(File file) {
		ObjectOutputStream oos = null;
		try {
			final FileOutputStream fileOut = new FileOutputStream(file);
			oos = new ObjectOutputStream(fileOut);
			oos.writeObject(MailmanController.getItems());
			oos.flush();
		} catch (final IOException e) {
			System.out.println("I/O Exception while writing to file");
			e.printStackTrace();
		} finally {
			try {
				if (oos != null) {
					oos.flush();
					oos.close();
				}
			} catch (final IOException ex) {
				System.out.println("I/O Exception while writing to file");
				ex.printStackTrace();
			}
		}
	}

	// Input of Shipments
	public static void openShipments(File file) {
		ObjectInputStream ois = null;
		try {
			final FileInputStream fileIn = new FileInputStream(file);
			ois = new ObjectInputStream(fileIn);
			ShipmentController.setItems((ObservableList<Shipment>) ois.readObject());
		} catch (final IOException e) {
			System.out.println("I/O Exception while reading file");
			e.printStackTrace();
		} catch (final ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Incorrect file format");
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}
			} catch (final IOException ex) {
				System.out.println("I/O Exception while reading file");
				ex.printStackTrace();
			}
		}

	}

	// Input of Mailmans
	public static void openMailmen(File file) {
		ObjectInputStream ois = null;
		try {
			final FileInputStream fileIn = new FileInputStream(file);
			ois = new ObjectInputStream(fileIn);
			MailmanController.setItems((ObservableList<Mailman>) ois.readObject());
		} catch (final IOException e) {
			e.printStackTrace();
			System.out.println("I/O Exception while reading file");
		} catch (final ClassNotFoundException e) {
			System.out.println("Incorrect file format");
			e.printStackTrace();
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}
			} catch (final IOException ex) {
				System.out.println("I/O Exception while reading file");
				ex.printStackTrace();
			}
		}
	}
}


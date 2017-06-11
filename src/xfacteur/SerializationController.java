package xfacteur;

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
	protected SerializationController() {
	}

	public static void serialization(String file1, String file2) {
		// Output of Shipments
		ObjectOutputStream oos = null;
		try {
			final FileOutputStream fileOut1 = new FileOutputStream(file1 + ".ship");
			oos = new ObjectOutputStream(fileOut1);
			oos.writeObject(ShipmentController.getItems());
			oos.flush();
		} catch (final java.io.IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (oos != null) {
					oos.flush();
					oos.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
		}

		// Output of Mailmans

		ObjectOutputStream oos2 = null;
		try {
			final FileOutputStream fileOut2 = new FileOutputStream(file2 + ".mail");
			oos2 = new ObjectOutputStream(fileOut2);
			for (Shipment s : ShipmentController.getItems()) {
				oos2.writeObject(s);
			}
			oos2.writeObject(MailmanController.getItems());
			oos2.flush();
		} catch (final java.io.IOException e) {
			System.out.println("I/O Exception while writing to file");
			e.printStackTrace();
		} finally {
			try {
				if (oos2 != null) {
					oos2.flush();
					oos2.close();
				}
			} catch (final IOException ex) {
				System.out.println("I/O Exception while writing to file");
				ex.printStackTrace();
			}
		}
	}

	public static void deserialization(String file1, String file2) {
		// Input of Shipments
		ObjectInputStream ois = null;
		try {
			final FileInputStream fileIn1 = new FileInputStream(file1);
			ois = new ObjectInputStream(fileIn1);
			ShipmentController.setItems((ObservableList<Shipment>) ois.readObject());
		} catch (final java.io.IOException e) {
			System.out.println("IO Exception while reading file");
			e.printStackTrace();
		} catch (final ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("File format is wrong");
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
		}

		// Input of Mailmans
		ObjectInputStream ois2 = null;
		try {
			final FileInputStream fileIn2 = new FileInputStream(file2);
			ois2 = new ObjectInputStream(fileIn2);
			MailmanController.setItems((ObservableList<Mailman>) ois2.readObject());
		} catch (final java.io.IOException e) {
			e.printStackTrace();
			System.out.println("IO Exception while reading file");
		} catch (final ClassNotFoundException e) {
			System.out.println("File format is wrong");
			e.printStackTrace();
		} finally {
			try {
				if (ois2 != null) {
					ois2.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}

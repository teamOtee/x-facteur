package xfacteur;

import javafx.collections.ObservableList;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javafx.collections.FXCollections;
import xfacteur.model.Mailman;
import xfacteur.model.Path;
import xfacteur.model.PathGenerator;
import xfacteur.model.Shipment;
import xfacteur.view.PathView;

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
			for (Shipment s : ShipmentController.getItems()) {
				oos.writeObject(s);
			}
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
			for (Mailman m : MailmanController.getItems()) {
				oos2.writeObject(m);
			}
			oos2.flush();
		} catch (final java.io.IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (oos2 != null) {
					oos2.flush();
					oos2.close();
				}
			} catch (final IOException ex) {
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
			ShipmentController.addItem((Shipment) ois.readObject());
		} catch (final java.io.IOException e) {
			e.printStackTrace();
		} catch (final ClassNotFoundException e) {
			e.printStackTrace();
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
			MailmanController.addItem((Mailman) ois2.readObject());
		} catch (final java.io.IOException e) {
			e.printStackTrace();
		} catch (final ClassNotFoundException e) {
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

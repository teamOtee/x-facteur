package xfacteur;

import xfacteur.view.ProxyModal;

public class ConfigController {
	//inaccessible constructor
	protected ConfigController() {}

	//attributes
	protected static ProxyModal proxyModal = new ProxyModal();
	protected static boolean useProxy = false;
	protected static String proxyHost = "";
	protected static String proxyPort = "";

	//methods
	public static void openProxyModal() {
		proxyModal.initValues(useProxy, proxyHost, proxyPort);
		proxyModal.showAndWait();
		useProxy = proxyModal.getUseProxyValue();
		proxyHost = proxyModal.getHostValue();
		proxyPort = proxyModal.getPortValue();
		if (useProxy) {
			System.setProperty("http.proxyHost", proxyHost);
			System.setProperty("https.proxyHost", proxyHost);
			System.setProperty("http.proxyPort", proxyPort);
			System.setProperty("https.proxyPort", proxyPort);
		} else {
			System.setProperty("http.proxyHost", "");
			System.setProperty("https.proxyHost", "");
			System.setProperty("http.proxyPort", "");
			System.setProperty("https.proxyPort", "");
		}
	}
}


package exceptions;

public class NoValidBrowserException extends Exception {

	public NoValidBrowserException(String browser) {
		super("Browser inválido: '" + browser + "'");
	}

}

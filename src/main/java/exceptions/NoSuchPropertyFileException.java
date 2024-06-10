package exceptions;

public class NoSuchPropertyFileException extends Exception {

	private String propFile;

	public NoSuchPropertyFileException(String fileName) {
		super("Archivo .properties no encontrado: '" + fileName + "'");
		this.propFile = fileName;
	}

}

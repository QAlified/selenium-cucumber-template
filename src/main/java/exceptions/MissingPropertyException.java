package exceptions;

public class MissingPropertyException extends Exception {

	private String propVal;

	public MissingPropertyException(String parName) {
		super("Parametro de configuracion no encontrado: '" + parName + "'");
		this.propVal = parName;
	}

}

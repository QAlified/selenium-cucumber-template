package config;

import java.io.File;

public class Config {
	
	private Config() {
		throw new IllegalStateException("Clase Config es una clase utilitaria, no debe ser instanciada");
	}
	//Scaling factor
	public static final float SCALING_FACTOR = 1.25f;
	//Project root directory
	private static final String PROJECT_DIR = System.getProperty("user.dir");
	
	//Screenshot root directory
	public static final String SCREENSHOT_PATH = PROJECT_DIR
			+ File.separator
			+ "screenshots"
			+ File.separator;
	
	//Properties Path
	public static final String PROPERTIES_PATH = PROJECT_DIR
			+ File.separator
			+ "properties"
			+ File.separator;
	
	//Files path
	public static final String FILES_PATH = PROJECT_DIR 
			+ File.separator 
			+ "files"
			+ File.separator;
	
	// driver path
	public static final String DRIVER_PATH = PROJECT_DIR 
			+ File.separator
			+ "src"
			+ File.separator
			+ "main"
			+ File.separator
			+ "java"
			+ File.separator
			+ "drivers"
			+ File.separator;

	public static final String DATAPROVIDER_PATH = PROJECT_DIR
			+ File.separator
			+ "dataProviders"
			+ File.separator;

}

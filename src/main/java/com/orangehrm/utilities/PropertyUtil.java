package com.orangehrm.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {

	private static Properties prop;

	public static void intializePropertyFile() throws IOException {
		File file = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\testdata\\Config.properties");

		FileInputStream fis = new FileInputStream(file);

		prop = new Properties();

		prop.load(fis);
	}

	public static  String readProperty(String keyName) {

		return prop.getProperty(keyName);
	}

}

package CommonLibs.Implementation;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class getConfigProperty {
	public static Properties props = null;

	public static String getProperty(String key) {
		if (props == null) {
			try {
				String filePath = (".\\src\\main\\resources\\Config.properties");
				FileReader reader = new FileReader(filePath);
				props = new Properties();
				try {
					props.load(reader);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return props.getProperty(key);
	}
}
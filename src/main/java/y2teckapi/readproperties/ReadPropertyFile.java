package y2teckapi.readproperties;//package y2teck.com.readproperties;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertyFile {
  public static Properties prop;
  public static String url;
  public Properties properties = new Properties();

  @Test
  public void qatest() throws IOException {

    // Load the properties file
    FileInputStream fileInputStream =
        new FileInputStream("src/main/java/y2teck/com/config/Conf.properties");
    properties.load(fileInputStream);
    fileInputStream.close();
    String password = properties.getProperty("browser");
     url = properties.getProperty("url");
    System.out.println("Username: " + password);
    System.out.println("URL: " + url);
  }
}

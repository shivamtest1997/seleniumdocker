package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static final Logger log= LoggerFactory.getLogger(Config.class);
    private static final String CONFIG_PROPERTIES="config/config.properties";
    private static Properties properties;
      public static void initialize()
    {
        //load config properties
        properties =loadProperties();

        //check for overRide
        //to get strings from config file
        for (String key:properties.stringPropertyNames())
        {
            if (System.getProperties().containsKey(key))
            {
                properties.setProperty(key,System.getProperty(key));
            }
        }
        //print
        log.info("Test properties");
        log.info("-----------------");
        for (String key:properties.stringPropertyNames())
        {
            log.info("{}={}",key,properties.getProperty(key));
        }

        }

    public static String getProperties(String key)
    {
        return properties.getProperty(key);
    }

    private static Properties loadProperties()
    {
        Properties properties=new Properties();
        try(InputStream stream=ResourceLoader.getResources(CONFIG_PROPERTIES))
        {
            properties.load(stream);
        }catch (Exception e)
        {
            log.info("unable to read properties file {}",CONFIG_PROPERTIES,e);
        }
        return properties;
    }
}

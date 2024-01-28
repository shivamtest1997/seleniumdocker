package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

//To read the test data from json file we have created this class using jackson databind library we will read the data
public class ResourceLoader {
    private static final Logger log= LoggerFactory.getLogger(ResourceLoader.class);

    public static InputStream getResources(String path) throws IOException {
        log.info("reading resources from location: {}",path);
        InputStream stream = ResourceLoader.class.getClassLoader().getResourceAsStream(path);
        if (Objects.nonNull(stream))
        {
            return stream;
        }
        return Files.newInputStream(Path.of(path));

}
}
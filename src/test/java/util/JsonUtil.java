package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pages.vendorportal.testDataModal.VendorPortalTestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.InputStream;

public class JsonUtil {

    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    public static VendorPortalTestData getTestData(String path){

        try (InputStream stream = ResourceLoader.getResources(path)) {
            return mapper.readValue(stream, VendorPortalTestData.class);
        } catch (
                Exception e) {
            log.error("unable to read test data {}", path, e);
        }
        return null;
    }
}

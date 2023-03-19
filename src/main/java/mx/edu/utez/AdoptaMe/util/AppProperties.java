package mx.edu.utez.AdoptaMe.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class AppProperties {
    @Autowired
    private Environment env;

    public String getImageSavePath() {
        return env.getProperty("imageSavePath");
    }
}

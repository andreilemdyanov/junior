package config;

import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.Properties;

/**
 * Class Settings.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 17.04.2018
 */
@Slf4j
public class Settings {

    private final Properties prs = new Properties();

    public void load(InputStream io) {
        try {
            this.prs.load(io);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public String getValue(String key) {
        return this.prs.getProperty(key);
    }
}

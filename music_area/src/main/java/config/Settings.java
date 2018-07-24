package config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

/**
 * Class Settings.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 24.07.2018
 */
public class Settings {
    private static final Logger LOG = LoggerFactory.getLogger(Settings.class);

    private final Properties prs = new Properties();

    public void load(InputStream io) {
        try {
            this.prs.load(io);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public String getValue(String key) {
        return this.prs.getProperty(key);
    }
}

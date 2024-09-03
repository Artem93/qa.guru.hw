package guru.qa.hw.config;

import org.aeonbits.owner.ConfigFactory;

public class ConfigDriver {

    private static final TestConfig testConfig = ConfigFactory.create(TestConfig.class, System.getProperties());

    public TestConfig getTestConfig() {
        return testConfig;
    }
}

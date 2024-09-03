package guru.qa.hw.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:local.properties",
        "classpath:${env}.properties"
})

public interface TestConfig extends Config {
    @Key("browser")
    @DefaultValue("chrome:100.0")
    String getBrowser();

    @Key("browserName")
    @DefaultValue("chrome")
    String getBrowserName();

    @Key("browserVersion")
    @DefaultValue("128.0")
    String getBrowserVersion();

    @Key("resolution")
    @DefaultValue("1920x1280")
    String getBrowserSize();

    @Key("remoteUrl")
    @DefaultValue("https://user1:1234@selenoid.autotests.cloud/wd/hub")
    String getRemoteUrl();

    @Key("isRemote")
    @DefaultValue("false")
    Boolean getIsRemote();
}

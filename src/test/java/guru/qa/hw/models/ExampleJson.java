package guru.qa.hw.models;

public class ExampleJson {
    private String name;
    private Integer age;
    private String secretIdentity;
    private ExampleJsonPowers powers;

    public ExampleJsonPowers getPowers() {
        return powers;
    }

    public String getSecretIdentity() {
        return secretIdentity;
    }

    public Integer getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
}

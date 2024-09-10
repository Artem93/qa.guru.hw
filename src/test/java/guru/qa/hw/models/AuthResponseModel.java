package guru.qa.hw.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AuthResponseModel {
    private String userId;
    private String expires;
    private String token;
}

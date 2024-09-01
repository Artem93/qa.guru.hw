package guru.qa.hw.models;

import lombok.Data;

@Data
public class AuthResponseModel {
    private String userId;
    private String expires;
    private String token;

    private String username;
    private String password;
    private String created_date;
    private String isActive;
}

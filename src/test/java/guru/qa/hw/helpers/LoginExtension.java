package guru.qa.hw.helpers;

import guru.qa.hw.api.AuthorizationApi;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class LoginExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {
        AuthorizationApi.setCookies();
    }
}

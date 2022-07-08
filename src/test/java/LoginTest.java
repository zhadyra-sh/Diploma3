
import io.qameta.allure.junit4.DisplayName;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class LoginTest {
    private final UsersApiClient usersApiClient = new UsersApiClient();

    @Before
    public void setUp() {
        browser = "chrome";
    }

    @After
    public void afterTest() {
        usersApiClient.deleteCreatedUsers();
    }

    public User getNewUser() {
        User user = User.getRandomUser();
        usersApiClient.register(user);
        usersApiClient.login(user);
        return user;
    }

    @Test
    @DisplayName("Login by clicking the \"Login\" button on the main page.")
    public void loginButtonTest() {
        open(ConstructorPage.PAGE_URL, ConstructorPage.class)
                .goToLoginPage();

        webdriver().shouldHave(url(LoginPage.PAGE_URL));
    }

    @Test
    @DisplayName("Login through the \"Personal Account\" button.")
    public void loginByUserCabinetTest() {
        open(ConstructorPage.PAGE_URL, ConstructorPage.class)
                .goToProfilePage();

        webdriver().shouldHave(url(LoginPage.PAGE_URL));
    }

    @Test
    @DisplayName("Login through the link in the registration form.")
    public void loginByRegistrationButtonTest() {
        open(RegistrationPage.PAGE_URL, RegistrationPage.class)
                .enterLinkClick();

        webdriver().shouldHave(url(LoginPage.PAGE_URL));
    }

    @Test
    @DisplayName("Login via password recovery.")
    public void loginByRestorePasswordButtonTest() {
        open(RestorePasswordPage.PAGE_URL, RestorePasswordPage.class)
                .enterLinkClick();

        webdriver().shouldHave(url(LoginPage.PAGE_URL));
    }

    @Test
    @DisplayName("Login new user")
    public void loginNewUserTest() {
        open(LoginPage.PAGE_URL, LoginPage.class)
                .fillLoginPage(getNewUser())
                .loginButtonClick();

        webdriver().shouldHave(url(ConstructorPage.PAGE_URL));
    }
}

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RegistrationPage {
    public static final String PAGE_URL = "https://stellarburgers.nomoreparties.site/register";

    // поле "Имя"
    @FindBy(how = How.XPATH, using = ".//fieldset[1]//input")
    private SelenideElement nameField;

    // поле "Email"
    @FindBy(how = How.XPATH, using = ".//fieldset[2]//input")
    private SelenideElement emailField;

    // поле "Пароль"
    @FindBy(how = How.XPATH, using = ".//input[@type='password']")
    private SelenideElement passwordField;

    @FindBy(xpath = ".//button[text()='Зарегистрироваться']")
    private SelenideElement registrationButton;

    @FindBy(xpath = ".//p[text()='Некорректный пароль']")
    private SelenideElement passwordError;

    @FindBy(xpath = ".//a[text()='Войти']")
    private SelenideElement enterLink;

    private SelenideElement getNameField(){
        return nameField;
    }

    private SelenideElement getEmailField(){
        return emailField;
    }

    private SelenideElement getPasswordField(){
        return passwordField;
    }

    @Step("Fill reg form")
    public RegistrationPage fillRegistrationForm(User user){
        getNameField().sendKeys(user.getName());
        getEmailField().sendKeys(user.getEmail());
        getPasswordField().sendKeys(user.getPassword());
        return this;
    }

    @Step("Accept Reg")
    public LoginPage confirmRegistration(){
        registrationButton.click();
        return Selenide.page(LoginPage.class);
    }

    @Step("Click by Entrance")
    public LoginPage enterLinkClick(){
        enterLink.click();
        return Selenide.page(LoginPage.class);
    }

    @Step("Getting error message.")
    public boolean isPasswordErrorVisible(){
        return passwordError.is(Condition.visible);
    }
}

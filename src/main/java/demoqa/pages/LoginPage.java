package demoqa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Utilities;
import java.util.List;

public class LoginPage extends Utilities {

    @FindBy(css = ".mr-sm-2.form-control")
    public List<WebElement> loginContainers;

    @FindBy(id = "login")
    public WebElement loginButton;

}

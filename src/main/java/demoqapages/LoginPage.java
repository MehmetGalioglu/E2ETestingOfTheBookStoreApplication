package demoqapages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Utilities;
import java.util.List;

public class LoginPage extends Utilities {

    @FindBy(css = ".col-md-9.col-sm-12")
    public List<WebElement> loginContainers;

}

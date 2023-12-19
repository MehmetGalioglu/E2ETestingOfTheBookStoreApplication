package demoqa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Utilities;
import java.util.List;

public class BookStorePage extends Utilities {

    @FindBy(css = ".element-group .btn.btn-light")
    public List<WebElement> elementGroupButtons;

}

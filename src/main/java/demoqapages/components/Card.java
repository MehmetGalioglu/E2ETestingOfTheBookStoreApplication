package demoqapages.components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Utilities;

public class Card extends Utilities {

    @FindBy(css = ".card-body")
    public WebElement cardBody;

    public WebElement getCardBody(){return cardBody;}

}

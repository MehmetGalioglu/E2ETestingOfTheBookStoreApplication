package demoqapages.components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Utilities;

public class ElementGroup extends Utilities {

    @FindBy(css = ".btn.btn-light")
    public WebElement elementGroupButton;

    public WebElement getElementGroupButton(){return elementGroupButton;}



}

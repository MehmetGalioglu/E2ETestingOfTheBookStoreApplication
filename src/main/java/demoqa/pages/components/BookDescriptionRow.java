package demoqa.pages.components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Utilities;

public class BookDescriptionRow extends Utilities {

    @FindBy(css = "div:first-of-type")
    public WebElement descriptionLabel;

    @FindBy(css = "div:last-of-type")
    public WebElement descriptionValue;
    public String getLabel(){return descriptionLabel.getText();}

    public String getValue(){return descriptionValue.getText();}



}

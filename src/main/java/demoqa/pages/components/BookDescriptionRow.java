package demoqa.pages.components;

import com.github.webdriverextensions.WebComponent;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Utilities;

public class BookDescriptionRow extends WebComponent {

    @FindBy(css = "div:first-of-type")
    public WebElement descriptionLabel;

    @FindBy(css = "div:last-of-type")
    public WebElement descriptionValue;

    public String getLabel(){return descriptionLabel.getText();}

    public String getValue(){return descriptionValue.getText();}

    @Getter
    public enum Specification{

        isbn("ISBN :"),

        title("Title :"),

        subTitle("Sub Title :"),

        author("Author :"),

        publisher("Publisher :"),

        pages("Total Pages :"),

        description("Description :"),

        website("Website :");

        String webKeyName;

        Specification(String webKeyName){

            this.webKeyName = webKeyName;
        }


    }



}

package demoqa.pages.components;

import com.github.webdriverextensions.WebComponent;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Utilities;
import java.util.List;

public class ProfileInventoryRow extends WebComponent {

    @FindBy(css = "[role=\"gridcell\"]")
    public List<WebElement> gridCells;

    @FindBy(css = "[role=\"gridcell\"] a")
    public WebElement bookLink;

    public List<WebElement> getGridCells(){return gridCells;}

    public WebElement getBookLink(){return bookLink;}



}

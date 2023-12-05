package test.java.com.demoqa;

import main.java.TestUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ElementsTest extends TestUtilities {



    @Test
    public void elementstest() {

        WaitUtility waitUtility = new WaitUtility(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        log.info("Starting Elements Test");

        String url = "https://demoqa.com/";
        driver.get(url);

        // Click on elements button and verify all the elements
        By elementsclick = By.cssSelector(".category-cards [class='card mt-4 top-card']:nth-of-type(1)");
        waitUtility.waitAndClick(elementsclick);

        String title = driver.findElement(By.cssSelector(".main-header")).getText();
        Assert.assertEquals(title, "Elements");

        String pleaseselect = driver.findElement(By.cssSelector(".col-12.mt-4.col-md-6")).getText();
        Assert.assertEquals(pleaseselect, "Please select an item from left to start practice.");

        WebElement textboxbutton = driver.findElement(By.id("item-0"));
        String textboxbuttonText = textboxbutton.getText();
        Assert.assertEquals(textboxbuttonText, "Text Box");

        WebElement checkboxbutton = driver.findElement(By.id("item-1"));
        String checkboxbuttonText = checkboxbutton.getText();
        Assert.assertEquals(checkboxbuttonText, "Check Box");

        WebElement radiobuttonbutton = driver.findElement(By.id("item-2"));
        String radiobuttonbuttonText = radiobuttonbutton.getText();
        Assert.assertEquals(radiobuttonbuttonText, "Radio Button");

        WebElement webtablebutton = driver.findElement(By.id("item-3"));
        String webtablebuttonText = webtablebutton.getText();
        Assert.assertEquals(webtablebuttonText, "Web Tables");

        WebElement buttonsbutton = driver.findElement(By.id("item-4"));
        String buttonsbuttonText = buttonsbutton.getText();
        Assert.assertEquals(buttonsbuttonText, "Buttons");

        WebElement linksbutton = driver.findElement(By.id("item-5"));
        String linksbuttonText = linksbutton.getText();
        Assert.assertEquals(linksbuttonText, "Links");

        WebElement brokenlinksbutton = driver.findElement(By.id("item-6"));
        String brokenlinksbuttonText = brokenlinksbutton.getText();
        Assert.assertEquals(brokenlinksbuttonText, "Broken Links - Images");

        WebElement uploadanddownloadbutton = driver.findElement(By.id("item-7"));
        String uploadanddownloadbuttonText = uploadanddownloadbutton.getText();
        Assert.assertEquals(uploadanddownloadbuttonText, "Upload and Download");

        WebElement dynamicpropertiesbutton = driver.findElement(By.id("item-8"));
        String dynamicpropertiesbuttonText = dynamicpropertiesbutton.getText();
        Assert.assertEquals(dynamicpropertiesbuttonText, "Dynamic Properties");

        log.info("All the labels are verified. Ending Elements Test");

    }

    @Test (dependsOnMethods = "elementstest")
    public void textboxtest() {

        WaitUtility waitUtility = new WaitUtility(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        log.info("Starting Textbox Test");

        // Click on Text Box button and verify all the elements

        By textboxclick = By.id("item-0");
        waitUtility.waitAndClick(textboxclick);

        String title = driver.findElement(By.cssSelector(".main-header")).getText();
        Assert.assertEquals(title, "Text Box");

        String fullname = driver.findElement(By.id("userName-label")).getText();
        Assert.assertEquals(fullname, "Full Name");

        WebElement fullnametextbox = driver.findElement(By.id("userName"));
        String fullnametextboxText = fullnametextbox.getAttribute("placeholder");
        Assert.assertEquals(fullnametextboxText, "Full Name");

        String email = driver.findElement(By.id("userEmail-label")).getText();
        Assert.assertEquals(email, "Email");

        WebElement emailtextbox = driver.findElement(By.id("userEmail"));
        String emailtextboxText = emailtextbox.getAttribute("placeholder");
        Assert.assertEquals(emailtextboxText, "name@example.com");

        String currentaddress = driver.findElement(By.id("currentAddress-label")).getText();
        Assert.assertEquals(currentaddress, "Current Address");

        WebElement currentaddresstextbox = driver.findElement(By.id("currentAddress"));
        String currentaddresstextboxText = currentaddresstextbox.getAttribute("placeholder");
        Assert.assertEquals(currentaddresstextboxText, "Current Address");

        String permanentaddress = driver.findElement(By.id("permanentAddress-label")).getText();
        Assert.assertEquals(permanentaddress, "Permanent Address");

        WebElement permanentaddresstextbox = driver.findElement(By.id("permanentAddress"));
        String permanentaddresstextboxText = permanentaddresstextbox.getAttribute("placeholder");
        Assert.assertEquals(permanentaddresstextboxText, "");

        log.info("All the labels are verified.");

        // Send keys to each element and verify the results

        fullnametextbox.sendKeys("Test Name");
        emailtextbox.sendKeys("Test Email");
        currentaddresstextbox.sendKeys("Test Current Address");
        permanentaddresstextbox.sendKeys("Test Permanent Address");

        String fullnamevalue = fullnametextbox.getAttribute("value");
        Assert.assertEquals(fullnamevalue, "Test Name");

        String emailvalue = emailtextbox.getAttribute("value");
        Assert.assertEquals(emailvalue, "Test Email");

        String currentaddressvalue = currentaddresstextbox.getAttribute("value");
        Assert.assertEquals(currentaddressvalue, "Test Current Address");

        String permanentaddressvalue = permanentaddresstextbox.getAttribute("value");
        Assert.assertEquals(permanentaddressvalue, "Test Permanent Address");

        log.info("All the values typed are verified and the same as expected.");

        // Clear all the values and verify the results

        fullnametextbox.clear();
        emailtextbox.clear();
        currentaddresstextbox.clear();
        permanentaddresstextbox.clear();

        fullnamevalue = fullnametextbox.getAttribute("value");
        Assert.assertEquals(fullnamevalue, "");

        emailvalue = emailtextbox.getAttribute("value");
        Assert.assertEquals(emailvalue, "");

        currentaddressvalue = currentaddresstextbox.getAttribute("value");
        Assert.assertEquals(currentaddressvalue, "");

        permanentaddressvalue = permanentaddresstextbox.getAttribute("value");
        Assert.assertEquals(permanentaddressvalue, "");

        log.info("All values are cleared.");

        // Click on submit button and verify if nothing is displayed

        WebElement submitbutton = driver.findElement(By.id("submit"));
        js.executeScript("arguments[0].click();", submitbutton);

        log.info("Submit button is clicked. Nothing is displayed.");

        // Type all the value again and click on submit button

        fullnametextbox.sendKeys("Test Name");
        emailtextbox.sendKeys("Test Email");
        currentaddresstextbox.sendKeys("Test Current Address");
        permanentaddresstextbox.sendKeys("Test Permanent Address");

        js.executeScript("arguments[0].click();", submitbutton);

        String output = driver.findElement(By.id("output")).getText();
        Assert.assertEquals( output, "");

        emailtextbox.clear();
        emailtextbox.sendKeys("email@example.com");
        js.executeScript("arguments[0].click();", submitbutton);

        output = driver.findElement(By.id("output")).getText();
        Assert.assertEquals(output, """
                Name:Test Name
                Email:email@example.com
                Current Address :Test Current Address
                Permananet Address :Test Permanent Address""");

        log.info("All the values are typed and submit button is clicked. The output is verified and it's as expected.");
    }

    @Test (dependsOnMethods = "textboxtest")
    public void checkboxtest() {

        WaitUtility waitUtility = new WaitUtility(driver);

        log.info("Starting Checkbox Test");

        // Click on Check Box button, click on each dropdown and verify all the elements

        By checkboxclick = By.id("item-1");
        waitUtility.waitAndClick(checkboxclick);

        WebElement expandAllButton = driver.findElement(By.cssSelector(".rct-option-expand-all"));
        expandAllButton.click();

        // Find and click specific checkboxes by their IDs

        WebElement notesCheckbox = driver.findElement(By.xpath("//span[contains(text(),'Notes')]"));
        notesCheckbox.click();

        String result = driver.findElement(By.id("result")).getText();
        Assert.assertEquals(result, "You have selected :\n" +
                "notes");

        WebElement commandsCheckbox = driver.findElement(By.xpath("//span[contains(text(),'Commands')]"));
        commandsCheckbox.click();

        result = driver.findElement(By.id("result")).getText();
        Assert.assertEquals(result, """
                You have selected :
                desktop
                notes
                commands""");

        WebElement desktopCheckbox = driver.findElement(By.xpath("//span[contains(text(),'Desktop')]"));
        desktopCheckbox.click();

        WebElement reactCheckbox = driver.findElement(By.xpath("//span[contains(text(),'React')]"));
        reactCheckbox.click();

        WebElement angularCheckbox = driver.findElement(By.xpath("//span[contains(text(),'Angular')]"));
        angularCheckbox.click();

        WebElement veuCheckbox = driver.findElement(By.xpath("//span[contains(text(),'Veu')]"));
        veuCheckbox.click();

        result = driver.findElement(By.id("result")).getText();
        Assert.assertEquals(result, """
                You have selected :
                workspace
                react
                angular
                veu""");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        WebElement workspaceCheckbox = driver.findElement(By.xpath("//span[contains(text(),'WorkSpace')]"));
        workspaceCheckbox.click();

        WebElement privateCheckbox = driver.findElement(By.xpath("//span[contains(text(),'Private')]"));
        privateCheckbox.click();

        WebElement publicCheckbox = driver.findElement(By.xpath("//span[contains(text(),'Public')]"));
        publicCheckbox.click();

        WebElement classifiedCheckbox = driver.findElement(By.xpath("//span[contains(text(),'Classified')]"));
        classifiedCheckbox.click();

        WebElement generalCheckbox = driver.findElement(By.xpath("//span[contains(text(),'General')]"));
        generalCheckbox.click();

        result = driver.findElement(By.id("result")).getText();
        Assert.assertEquals(result, """
                You have selected :
                office
                public
                private
                classified
                general""");

        WebElement officeCheckbox = driver.findElement(By.xpath("//span[contains(text(),'Office')]"));
        officeCheckbox.click();

        WebElement wordfileCheckbox = driver.findElement(By.xpath("//span[contains(text(),'Word File.doc')]"));
        wordfileCheckbox.click();

        WebElement excelfileCheckbox = driver.findElement(By.xpath("//span[contains(text(),'Excel File.doc')]"));
        excelfileCheckbox.click();

        result = driver.findElement(By.id("result")).getText();
        Assert.assertEquals(result, """
                You have selected :
                downloads
                wordFile
                excelFile""");

        WebElement homeCheckbox = driver.findElement(By.xpath("//span[contains(text(),'Home')]"));
        homeCheckbox.click();

        result = driver.findElement(By.id("result")).getText();
        Assert.assertEquals(result, """
                You have selected :
                home
                desktop
                notes
                commands
                documents
                workspace
                react
                angular
                veu
                office
                public
                private
                classified
                general
                downloads
                wordFile
                excelFile""");

        log.info("All the checkboxes are working as expected and the feedback is displayed correctly.");

        // Click on some dropdown buttons

        WebElement dropdowndesktop = driver.findElement(By.xpath("(//button[@title='Toggle'])[2]"));
        dropdowndesktop.click();

        WebElement dropdowndocuments = driver.findElement(By.xpath("(//button[@title='Toggle'])[3]"));
        dropdowndocuments.click();

        WebElement dropdownworkspace = driver.findElement(By.xpath("(//button[@title='Toggle'])[4]"));
        dropdownworkspace.click();

    }

    @Test (dependsOnMethods = "checkboxtest")
    public void radiobuttontest() {

        WaitUtility waitUtility = new WaitUtility(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        log.info("Starting Radio Button Test");

        // Click on Radio button and verify all the elements

        WebElement radiobuttonbutton = driver.findElement(By.id("item-2"));
        radiobuttonbutton.click();

        String title = driver.findElement(By.cssSelector(".mb-3")).getText();
        Assert.assertEquals(title, "Do you like the site?");

        String yes = driver.findElement(By.cssSelector("label[for='yesRadio']")).getText();
        Assert.assertEquals(yes, "Yes");

        String impressive = driver.findElement(By.cssSelector("label[for='impressiveRadio']")).getText();
        Assert.assertEquals(impressive, "Impressive");

        String no = driver.findElement(By.cssSelector("label[for='noRadio']")).getText();
        Assert.assertEquals(no, "No");

        // Click on each radio button and verify the feedback

        WebElement yesRadio = driver.findElement(By.cssSelector("label[for='yesRadio']"));
        yesRadio.click();

        String feedback = driver.findElement(By.cssSelector(".mt-3")).getText();
        Assert.assertEquals(feedback, "You have selected Yes");

        WebElement impressiveRadio = driver.findElement(By.cssSelector("label[for='impressiveRadio']"));
        impressiveRadio.click();

        feedback = driver.findElement(By.cssSelector(".mt-3")).getText();
        Assert.assertEquals(feedback, "You have selected Impressive");

        WebElement noRadio = driver.findElement(By.id("noRadio"));

        boolean isClickable = noRadio.isEnabled();

        if (!isClickable) {
            log.info("The element is not clickable.");
        } else {
            log.info("The element is clickable.");
        }

        feedback = driver.findElement(By.cssSelector(".mt-3")).getText();
        Assert.assertEquals(feedback, "You have selected Impressive");





    }

}


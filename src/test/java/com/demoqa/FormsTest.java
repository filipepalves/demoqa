package test.java.com.demoqa;

import main.java.base.TestUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FormsTest extends TestUtilities {


    @Test
    public void formstest() {

        log.info("Starting Forms Test");

        String url = "https://demoqa.com/";
        driver.get(url);

        // Click on elements button and verify all the elements
        By elementsclick = By.cssSelector(".category-cards [class='card mt-4 top-card']:nth-of-type(2)");
        waitUtility.waitAndClick(elementsclick);

        String title = driver.findElement(By.cssSelector(".main-header")).getText();
        Assert.assertEquals(title, "Forms");

        String pleaseselect = driver.findElement(By.cssSelector(".col-12.mt-4.col-md-6")).getText();
        Assert.assertEquals(pleaseselect, "Please select an item from left to start practice.");

        WebElement practiceButton = driver.findElement(By.id("item-0"));
        practiceButton.isDisplayed();
        String practiceButtonTextText = driver.findElement(By.cssSelector(".show .text")).getText();
        Assert.assertEquals(practiceButtonTextText, "Practice Form");

        log.info("All the labels are verified. Ending Forms Test");

    }

    @Test(dependsOnMethods = "formstest")
    public void practiceformstest() throws InterruptedException {

        log.info("Starting Practice Form Test");

        // Click on Pratice form button and verify all the elements

        By textboxclick = By.cssSelector(".collapse.element-list.show > .menu-list > li#item-0");
        waitUtility.waitAndClick(textboxclick);

        String title = driver.findElement(By.cssSelector(".main-header")).getText();
        Assert.assertEquals(title, "Practice Form");

        String name = driver.findElement(By.id("userName-label")).getText();
        Assert.assertEquals(name, "Name");

        WebElement firstname = driver.findElement(By.id("firstName"));
        String firstnameText = firstname.getAttribute("placeholder");
        Assert.assertEquals(firstnameText, "First Name");

        WebElement lastname = driver.findElement(By.id("lastName"));
        String lastnameText = lastname.getAttribute("placeholder");
        Assert.assertEquals(lastnameText, "Last Name");

        String email = driver.findElement(By.id("userEmail-wrapper")).getText();
        Assert.assertEquals(email, "Email");

        WebElement emailtextbox = driver.findElement(By.id("userEmail"));
        String emailtextboxText = emailtextbox.getAttribute("placeholder");
        Assert.assertEquals(emailtextboxText, "name@example.com");

        String gender = driver.findElement(By.cssSelector("div#genterWrapper > .col-md-3.col-sm-12")).getText();
        Assert.assertEquals(gender, "Gender");

        js.executeScript("window.scrollBy(0, 300)");

        WebElement genderRadio = driver.findElement(By.id("gender-radio-1"));
        String genderRadioText = genderRadio.getAttribute("value");
        Assert.assertEquals(genderRadioText, "Male");

        WebElement genderRadio2 = driver.findElement(By.id("gender-radio-2"));
        String genderRadio2Text = genderRadio2.getAttribute("value");
        Assert.assertEquals(genderRadio2Text, "Female");

        WebElement genderRadio3 = driver.findElement(By.id("gender-radio-3"));
        String genderRadio3Text = genderRadio3.getAttribute("value");
        Assert.assertEquals(genderRadio3Text, "Other");

        String mobile = driver.findElement(By.id("userNumber-label")).getText();
        Assert.assertEquals(mobile, "Mobile(10 Digits)");

        WebElement mobiletextbox = driver.findElement(By.id("userNumber"));
        String mobiletextboxText = mobiletextbox.getAttribute("placeholder");
        Assert.assertEquals(mobiletextboxText, "Mobile Number");

        String dateofbirth = driver.findElement(By.id("dateOfBirth-label")).getText();
        Assert.assertEquals(dateofbirth, "Date of Birth");

        WebElement dateofbirthtextbox = driver.findElement(By.id("dateOfBirthInput"));
        String dateofbirthtextboxText = dateofbirthtextbox.getAttribute("value");
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String formattedDate = today.format(formatter);
        Assert.assertEquals(dateofbirthtextboxText, formattedDate);

        String subject = driver.findElement(By.cssSelector("#subjectsWrapper #subjects-label")).getText();
        Assert.assertEquals(subject, "Subjects");

        WebElement subjecttextbox = driver.findElement(By.id("subjectsContainer"));
        subjecttextbox.isDisplayed();

        String hobbies = driver.findElement(By.cssSelector("div#hobbiesWrapper  label#subjects-label")).getText();
        Assert.assertEquals(hobbies, "Hobbies");

        WebElement checkboxSports = driver.findElement(By.cssSelector("div#hobbiesWrapper > .col-md-9.col-sm-12 > div:nth-of-type(1) > label"));
        String checkboxSportsText = checkboxSports.getText();
        Assert.assertEquals(checkboxSportsText, "Sports");

        WebElement checkboxReading = driver.findElement(By.cssSelector("div#hobbiesWrapper > .col-md-9.col-sm-12 > div:nth-of-type(2) > label"));
        String checkboxReadingText = checkboxReading.getText();
        Assert.assertEquals(checkboxReadingText, "Reading");

        WebElement checkboxMusic = driver.findElement(By.cssSelector("div#hobbiesWrapper > .col-md-9.col-sm-12 > div:nth-of-type(3) > label"));
        String checkboxMusicText = checkboxMusic.getText();
        Assert.assertEquals(checkboxMusicText, "Music");

        String picture = driver.findElement(By.cssSelector("div:nth-of-type(8) > .col-md-3.col-sm-12")).getText();
        Assert.assertEquals(picture, "Picture");

        String selectPicture = driver.findElement(By.cssSelector(".form-file-label")).getText();
        Assert.assertEquals(selectPicture, "Select picture");

        WebElement chooseButton = driver.findElement(By.cssSelector("#uploadPicture"));
        chooseButton.isDisplayed();

        String currentAddress = driver.findElement(By.id("currentAddress-label")).getText();
        Assert.assertEquals(currentAddress, "Current Address");

        WebElement currentAddressTextbox = driver.findElement(By.id("currentAddress"));
        String currentAddressTextboxText = currentAddressTextbox.getAttribute("placeholder");
        Assert.assertEquals(currentAddressTextboxText, "Current Address");

        String stateAndCity = driver.findElement(By.id("stateCity-label")).getText();
        Assert.assertEquals(stateAndCity, "State and City");

        WebElement stateDropdown = driver.findElement(By.cssSelector(".col-md-4.col-sm-12 > div#state"));
        String stateDropdownText = stateDropdown.getText();
        Assert.assertEquals(stateDropdownText, "Select State");

        WebElement cityDropdown = driver.findElement(By.cssSelector(".col-md-4.col-sm-12 > div#city"));
        String cityDropdownText = cityDropdown.getText();
        Assert.assertEquals(cityDropdownText, "Select City");

        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.isDisplayed();

        log.info("All the elements are as expected.");

        // Click on submit button and verify all the mandatory fields

        js.executeScript("arguments[0].click();", submitButton);

        Thread.sleep(500);

        List<WebElement> errorMessages = driver.findElements(By.cssSelector(".form-control.is-invalid, .was-validated .form-control:invalid, .custom-control-input.is-invalid~.custom-control-label, .was-validated .custom-control-input:invalid~.custom-control-label"));

        Assert.assertEquals(errorMessages.size(), 6, "Expected 6 error messages, but found: " + errorMessages.size());

        for (WebElement errorMessage : errorMessages) {

            String borderColor = errorMessage.getCssValue("border-color");
            Assert.assertEquals(borderColor, "rgb(220, 53, 69)");

        }

        errorMessages = driver.findElements(By.cssSelector(".form-control.is-invalid, .was-validated .form-control:invalid"));

        for (WebElement errorMessage : errorMessages) {

            String borderColor = errorMessage.getCssValue("border-color");
            Assert.assertEquals(borderColor, "rgb(220, 53, 69)");

            String backgroundImage = errorMessage.getCssValue("background-image");
            Assert.assertTrue(backgroundImage.contains("data:image/svg+xml,%3csvg"));

        }

        log.info("All the mandatory fields are with border line and warning icon after clicked without filling.");

        // Give 2 of the mandatory fields and click on submit button and verify if only 4 error message is displayed

        firstname.sendKeys("Filipe");

        lastname.sendKeys("Alves");

        js.executeScript("arguments[0].click();", submitButton);

        errorMessages = driver.findElements(By.cssSelector(".form-control.is-invalid, .was-validated .form-control:invalid, .custom-control-input.is-invalid~.custom-control-label, .was-validated .custom-control-input:invalid~.custom-control-label"));

        Assert.assertEquals(errorMessages.size(), 4, "Expected 4 error messages, but found: " + errorMessages.size());

        if (!errorMessages.isEmpty()) {
            WebElement errorMessage = errorMessages.get(0);

            String borderColor = errorMessage.getCssValue("border-color");
            Assert.assertEquals(borderColor, "rgb(220, 53, 69)");

        }

        // Give all the mandatory fields and click on submit button and verify if no error message is displayed

        emailtextbox.sendKeys("filipealves@qa.com");

        js.executeScript("arguments[0].click();", genderRadio);

        mobiletextbox.sendKeys("123456789");
        Assert.assertTrue(mobiletextbox.getCssValue("border-color").equals("rgb(220, 53, 69)"));
        mobiletextbox.clear();
        mobiletextbox.sendKeys("1234567890");
        Thread.sleep(500);
        Assert.assertTrue(mobiletextbox.getCssValue("border-color").equals("rgb(40, 167, 69)"));

        log.info("All the mandatory fields are without border line with red color and without warning icon after filled.");

        // Test the date text box

        dateofbirthtextbox.click();

        Select yearDropdown = new Select(driver.findElement(By.className("react-datepicker__year-select")));
        yearDropdown.selectByVisibleText("1987");
        Select monthDropdown = new Select(driver.findElement(By.className("react-datepicker__month-select")));
        monthDropdown.selectByVisibleText("June");
        WebElement daySelected = driver.findElement(By.cssSelector(".react-datepicker__day.react-datepicker__day--007.react-datepicker__day--weekend"));
        daySelected.click();

        dateofbirthtextboxText = dateofbirthtextbox.getAttribute("value");
        Assert.assertEquals(dateofbirthtextboxText, "07 Jun 1987");

        // Type some information on subject

        subjecttextbox.click();
        js.executeScript("arguments[0].value='a';", subjecttextbox);

        Assert.assertTrue(subjecttextbox.getAttribute("value").equals("This autometed test was created by Filipe Alves"));

        log.info("Finishing Practice Forms Test.");


    }

}


package com.salmon.test.step_definitions.gui.register;


import com.salmon.test.framework.helpers.utils.RandomGenerator;
import com.salmon.test.page_objects.gui.NewRegistrationPage;
import cucumber.api.java.en.When;
import lombok.Getter;
import org.testng.Assert;

import static com.salmon.test.enums.PermittedCharacters.ALPHABETS;
import static com.salmon.test.enums.PermittedCharacters.ALPHANUMERIC;
import static com.salmon.test.framework.helpers.utils.RandomGenerator.random;

@Getter
public class NewRegistrationSteps {

    private NewRegistrationPage newRegistrationPage;

    private String loginIdData = random(6, ALPHANUMERIC);
    private String passwordData = random(6, ALPHANUMERIC);
    private String titleData = "Dr.";
    private String firstNameData = random(6, ALPHABETS);
    private String lastNameData = random(6, ALPHABETS);
    private String postCodeData = "UB10 9DW";
    private String address1Data = random(6, ALPHABETS);
    private String townOrCityData = random(6, ALPHABETS);
    private String emailAddressData = RandomGenerator.randomEmailAddress(6);
    private String birthDateData = "1";
    private String birthMonthData = "1";
    private String birthYearData = "1982";


    public NewRegistrationSteps(NewRegistrationPage newRegistrationPage) {
        this.newRegistrationPage = newRegistrationPage;
    }


    @When("^i fill in the registration form on New Registration page$")
    public void i_fill_in_the_registration_form_on_New_Registration_page() throws Throwable {
        Assert.assertTrue(newRegistrationPage.checkNewRegistrationForm(), "New Registration Form is Displayed");
        enterUserRegistrationDetails();

    }


    public void enterUserRegistrationDetails() {

        newRegistrationPage.loginIdText().sendKeys(loginIdData);
        newRegistrationPage.passwordText().sendKeys(passwordData);
        newRegistrationPage.verifyPasswordText().sendKeys(passwordData);

        newRegistrationPage.selectTitle().selectByVisibleText(titleData);
        newRegistrationPage.firstNameText().sendKeys(firstNameData);
        newRegistrationPage.lastNameText().sendKeys(lastNameData);

        newRegistrationPage.postCodeText().sendKeys(postCodeData);
        newRegistrationPage.address1Text().sendKeys(address1Data);
        newRegistrationPage.townOrCityText().sendKeys(townOrCityData);

        newRegistrationPage.emailAddressText().sendKeys(emailAddressData);
        newRegistrationPage.confirmEmailAddressText().sendKeys(emailAddressData);

        newRegistrationPage.birthDayText().selectByVisibleText(birthDateData);
        newRegistrationPage.birthMonthText().selectByVisibleText(birthMonthData);
        newRegistrationPage.birthYearText().selectByVisibleText(birthYearData);

        newRegistrationPage.acceptTermsAndConditions(true);

        newRegistrationPage.submitRegistration();

    }


}
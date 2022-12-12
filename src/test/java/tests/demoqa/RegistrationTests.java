package tests.demoqa;

import com.codeborne.selenide.selector.ByText;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class RegistrationTests extends TestBase {

    @Test
    void fillFormTest() {

        step("Open registrations form", () -> {
                    open("/automation-practice-form");
                    $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
                    executeJavaScript("$('footer').remove()");
                    executeJavaScript("$('#fixedban').remove()");
                });


        step("Fill form", () -> {
                    $("#firstName").setValue("Кайрат");
                    $("#lastName").setValue("Кайратов");
                    $("#userEmail").setValue("kairat_test@testtt.comm");
                    $(".custom-control-label").click();
                    $("#userNumber").setValue("87771111111");
                    $("#dateOfBirthInput").click();
                    $(".react-datepicker__month-select").click();
                    $(".react-datepicker__month-select").selectOption("July");
                    $(".react-datepicker__year-select").click();
                    $(".react-datepicker__year-select").selectOption("1900");
                    $(".react-datepicker__year-select").click();
                    $(".react-datepicker__day--030").click();
                    /*
                    PS Другой вариант
                    $(by("value", "6")).click();
                    $(by("aria-label", "Choose Saturday, July 30th, 2022")).click();
                    */
                    $("#subjectsInput").click();
                    $("#subjectsInput").setValue("qwerty").pressTab();
                    $(by("for", "hobbies-checkbox-1")).click();
                    $("#uploadPicture").uploadFile(new File("src/test/resources/homework.jpg"));
                    $("#currentAddress").setValue("Some address 1");
                    $("#state").click();
                    $("#stateCity-wrapper").$(new ByText("Uttar Pradesh")).click();
                    $("#city").click();
                    $("#stateCity-wrapper").$(new ByText("Agra")).click();
                    $("#submit").click();
                });

        // Проверки
        step("Check form results", () -> {
                    $(".modal-title").shouldHave(text("Thanks for submitting the form"));
                    $(".table-responsive").shouldHave(
                        text("Кайрат"),
                        text("kairat_test@testtt.comm"),
                        text("Male"), text("8777111111"),
                        text("30 July,1900"),
                        text("Sports"), text("homework.JPG"),
                        text("Some address 1"),
                        text("Uttar Pradesh Agra")
                );
        });
    }
}

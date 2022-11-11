package ru.iteco.fmhandroid.ui.data.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.DataHelper;

public class LoginPage {
    public static ViewInteraction loginField = onView(withHint("Логин"));
    public static ViewInteraction passwordField = onView(withHint("Пароль"));
    public static ViewInteraction loginButton = onView(withId(R.id.enter_button));
    public static ViewInteraction title = onView(withText("Авторизация"));

    @Step("Авторизация под валидным пользователем - Иванов Д.Д.")
    public static void validLogIn() {
        DataHelper help = new DataHelper();
        LoginPage.loginField.perform(typeText(help.getValidUser().getLogin()));
        LoginPage.passwordField.perform(typeText(help.getValidUser().getPassword()));
        LoginPage.loginButton.perform(click());
    }

    @Step("Авторизация под НЕвалидной учеткой")
    public static void notValidLogIn() {
        DataHelper help = new DataHelper();
        LoginPage.loginField.perform(typeText(help.getNotValidUser().getLogin()));
        LoginPage.passwordField.perform(typeText(help.getNotValidUser().getPassword()));
        LoginPage.loginButton.perform(click());
    }

}

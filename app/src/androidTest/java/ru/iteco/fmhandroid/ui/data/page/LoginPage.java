package ru.iteco.fmhandroid.ui.data.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

import static ru.iteco.fmhandroid.ui.data.customViewAction.TimeoutEspresso.onViewWithTimeout;

import androidx.test.espresso.ViewInteraction;
import androidx.test.rule.ActivityTestRule;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.DataHelper;
import ru.iteco.fmhandroid.ui.data.customViewAction.TimeoutEspresso;

public class LoginPage {
    public static TimeoutEspresso.TimedViewInteraction loginField = onViewWithTimeout(withHint("Логин"));
    public static TimeoutEspresso.TimedViewInteraction passwordField = onViewWithTimeout(withHint("Пароль"));
    public static TimeoutEspresso.TimedViewInteraction loginButton = onViewWithTimeout(withId(R.id.enter_button));
    public static TimeoutEspresso.TimedViewInteraction title = onViewWithTimeout(withText("Авторизация"));

    @Step("Авторизация под валидным пользователем - Иванов Д.Д.")
    public static void validLogIn() throws InterruptedException {
        DataHelper help = new DataHelper();
        LoginPage.loginField.perform(typeText(help.getValidUser().getLogin()), closeSoftKeyboard());
        LoginPage.passwordField.perform(typeText(help.getValidUser().getPassword()), closeSoftKeyboard());
//        Thread.sleep(3000);
        LoginPage.loginButton.perform(click());
    }

    @Step("Авторизация под НЕвалидной учеткой")
    public static void notValidLogIn() {
        DataHelper help = new DataHelper();
        LoginPage.loginField.perform(typeText(help.getNotValidUser().getLogin()), closeSoftKeyboard());
        LoginPage.passwordField.perform(typeText(help.getNotValidUser().getPassword()), closeSoftKeyboard());
        LoginPage.loginButton.perform(click());
    }

    public static void checkTextToastMessage(String text, ActivityTestRule<AppActivity> activityTestRule) {
        onView(withText(text)).inRoot(withDecorView(not(activityTestRule
                .getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
    }

}

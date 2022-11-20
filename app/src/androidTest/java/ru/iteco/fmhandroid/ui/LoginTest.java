package ru.iteco.fmhandroid.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Feature;
import io.qameta.allure.kotlin.Step;
import io.qameta.allure.kotlin.Story;
import ru.iteco.fmhandroid.ui.data.DataHelper;
import ru.iteco.fmhandroid.ui.data.page.LoginPage;
import ru.iteco.fmhandroid.ui.data.page.MainPage;

@RunWith(AllureAndroidJUnit4.class)

public class LoginTest {
    DataHelper help = new DataHelper();

    @Rule
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(AppActivity.class);

    @Before
    public void setUp() throws InterruptedException {
        try {
            Thread.sleep(5000);
            LoginPage.title.check(matches(isDisplayed()));
        } catch (Exception e) {
            MainPage.logOut();
        }
    }

    @Feature(value = "Набор тест кейсов по LogIn и LogOut из учетной записи")
    @Story("1.1.1 и 1.1.2 Авторизация в приложение под валидными учетными данными, проверка LogOut")
    @Test
    public void shouldLoginByValidUserAndExit() throws InterruptedException {
//        Thread.sleep(5000);
        LoginPage.validLogIn();
        Thread.sleep(5000);
        //Проверка главной страницы, лого и блоки Новости и Заявки
        MainPage.mainLogo.check(matches(isDisplayed()));
        MainPage.newsContainer.check(matches(isDisplayed()));
        MainPage.claimContainer.check(matches(isDisplayed()));
        //Выход из приложения
        MainPage.logOut();
        //Проверка страницы авторизации, заголовок и поля логин/пароль
        LoginPage.title.check(matches(isDisplayed()));
        LoginPage.loginButton.check(matches(isDisplayed()));
        LoginPage.passwordField.check(matches(isDisplayed()));
    }

    @Feature(value = "Набор тест кейсов по LogIn и LogOut из учетной записи")
    @Story("1.1.3 Авторизация в приложение под невалидными учетными данными")
    @Test
    public void shouldLoginByNotValidUser() throws InterruptedException {
        LoginPage.notValidLogIn();
        //Проверить toast message
        onView(withText("Неверный логин или пароль")).inRoot(withDecorView(not(activityTestRule
                .getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
        Thread.sleep(2000);
        MainPage.mainLogo.check(matches(not(isDisplayed())));
        LoginPage.title.check(matches(isDisplayed()));
    }
}

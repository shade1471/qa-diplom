package ru.iteco.fmhandroid.ui;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.Matchers.not;
import static ru.iteco.fmhandroid.ui.data.customViewAction.CustomViewAction.needWait;

import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Feature;
import io.qameta.allure.kotlin.Story;
import ru.iteco.fmhandroid.ui.data.DataHelper;
import ru.iteco.fmhandroid.ui.data.page.LoginPage;
import ru.iteco.fmhandroid.ui.data.page.MainPage;

@RunWith(AllureAndroidJUnit4.class)

public class LoginTest {

    @Rule
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(AppActivity.class);

    @Before
    public void setUp() {
        try {
            needWait(5000);
            LoginPage.title.check(matches(isDisplayed()));
        } catch (Exception e) {
            MainPage.logOut();
        }
    }

    @Feature(value = "Набор тест кейсов по LogIn и LogOut из учетной записи")
    @Story("1.1.1 и 1.1.2 Авторизация в приложение под валидными учетными данными, проверка LogOut")
    @Test
    public void shouldLoginByValidUserAndExit() {
        LoginPage.validLogIn();
        needWait(8000);
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
    public void shouldLoginByNotValidUser() {
        LoginPage.notValidLogIn();
        //Проверить toast message
        LoginPage.checkTextToastMessage("Неверный логин или пароль", activityTestRule);
        //Проверка что остались на странице
        MainPage.mainLogo.check(matches(not(isDisplayed())));
        LoginPage.title.check(matches(isDisplayed()));
    }
}

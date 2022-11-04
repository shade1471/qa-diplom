package ru.iteco.fmhandroid.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

import androidx.test.espresso.ViewInteraction;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Feature;
import io.qameta.allure.kotlin.Story;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.DataHelper;

@RunWith(AllureAndroidJUnit4.class)

public class LoginTest {
    ViewInteraction loginField = onView(withHint("Логин"));
    ViewInteraction passwordField = onView(withHint("Пароль"));
    ViewInteraction loginButton = onView(withId(R.id.enter_button));
    public static ViewInteraction mainLogo = onView(allOf(withId(R.id.trademark_image_view)));
    ViewInteraction profileButton = onView(withId(R.id.authorization_image_button));
    ViewInteraction profileMenu = onView(withText("Выйти"));
    ViewInteraction title = onView(withText("Авторизация"));
    ViewInteraction newsContainer = onView(withId(R.id.container_list_news_include_on_fragment_main));
    ViewInteraction claimContainer = onView(withId(R.id.container_list_claim_include_on_fragment_main));
    DataHelper help = new DataHelper();

    @Rule
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(AppActivity.class);

    @Before
    public void setUp() {
        try {
            Thread.sleep(5000);
            title.check(matches(isDisplayed()));
        } catch (Exception e) {
            profileButton.perform(click());
            profileMenu.perform(click());
        }
    }

    @Feature(value = "Набор тест кейсов по LogIn и LogOut из учетной записи")
    @Story("Авторизация в приложение под валидными учетными данными, проверка LogOut")
    @Test
    public void shouldLoginByValidUserAndExit() throws InterruptedException {
//        Thread.sleep(5000);
        loginField.perform(typeText(help.getValidUser().getLogin()));
        passwordField.perform(typeText(help.getValidUser().getPassword()));
        loginButton.perform(click());
        Thread.sleep(5000);
        //Проверка главной страницы, лого и блоки Новости и Заявки
        mainLogo.check(matches(isDisplayed()));
        newsContainer.check(matches(isDisplayed()));
        claimContainer.check(matches(isDisplayed()));
        //Выход из приложения
        profileButton.perform(click());
        profileMenu.perform(click());
        //Проверка страницы авторизации, заголовок и поля логин/пароль
        title.check(matches(isDisplayed()));
        loginButton.check(matches(isDisplayed()));
        passwordField.check(matches(isDisplayed()));
    }

    @Feature(value = "Набор тест кейсов по LogIn и LogOut из учетной записи")
    @Story("Авторизация в приложение под невалидными учетными данными")
    @Test
    public void shouldLoginByNotValidUser() throws InterruptedException {
//        Thread.sleep(5000);
        loginField.perform(typeText(help.getNotValidUser().getLogin()));
        passwordField.perform(typeText(help.getNotValidUser().getPassword()));
        loginButton.perform(click());
        Thread.sleep(5000);
        mainLogo.check(matches(not(isDisplayed())));
        title.check(matches(isDisplayed()));
    }
}

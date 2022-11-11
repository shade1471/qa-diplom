package ru.iteco.fmhandroid.ui;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;

import androidx.test.espresso.Root;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import io.qameta.allure.kotlin.Feature;
import io.qameta.allure.kotlin.Story;
import ru.iteco.fmhandroid.ui.data.DataHelper;
import ru.iteco.fmhandroid.ui.data.page.LoginPage;
import ru.iteco.fmhandroid.ui.data.page.MainPage;
import ru.iteco.fmhandroid.ui.data.page.NewClaimPage;
import ru.iteco.fmhandroid.ui.data.page.NewNewsPage;
import ru.iteco.fmhandroid.ui.data.page.NewsMainPage;

public class NewsFunctionalTest {
    DataHelper help = new DataHelper();

    // Выбор из PopUpMenu
    public static Matcher<Root> isPopupWindow() {
        return isPlatformPopup();
    }

    @Rule
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(AppActivity.class);

    @Before
    public void setUp() throws InterruptedException {
        try {
            Thread.sleep(5000);
            LoginPage.validLogIn();
            Thread.sleep(7000);
        } catch (Exception e) {
            MainPage.openNewsPage();
            Thread.sleep(4000);
        }
    }

    @Feature(value = "Набор тест кейсов по проверке функционала события типа Новости (Функциональное тестирование)")
    @Story("1.4.1 Создание новой новости (Happy Path)")
    @Test
    public void shouldAddNews() throws InterruptedException {
        NewsMainPage.editNews.perform(click());
        NewsMainPage.addNews.perform(click());
        NewNewsPage.addNews();

    }
}

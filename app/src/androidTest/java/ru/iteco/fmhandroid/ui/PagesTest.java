package ru.iteco.fmhandroid.ui;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasPackage;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.allOf;

import androidx.test.espresso.intent.Intents;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Feature;
import io.qameta.allure.kotlin.Story;
import ru.iteco.fmhandroid.ui.data.DataHelper;
import ru.iteco.fmhandroid.ui.data.page.AboutPage;
import ru.iteco.fmhandroid.ui.data.page.ClaimMainPage;
import ru.iteco.fmhandroid.ui.data.page.LoginPage;
import ru.iteco.fmhandroid.ui.data.page.MainPage;
import ru.iteco.fmhandroid.ui.data.page.NewsPage;
import ru.iteco.fmhandroid.ui.data.page.OurMissionPage;

@RunWith(AllureAndroidJUnit4.class)

public class PagesTest {

    DataHelper help = new DataHelper();

    @Rule
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(AppActivity.class);

    @Before
    public void setUp() throws InterruptedException {
        try {
            Thread.sleep(5000);
            LoginPage.loginField.perform(typeText(help.getValidUser().getLogin()));
            LoginPage.passwordField.perform(typeText(help.getValidUser().getPassword()));
            LoginPage.loginButton.perform(click());
            Thread.sleep(4000);
        } catch (Exception e) {
            return;
        }
    }

    @After
    public void tearDown() throws InterruptedException {
        MainPage.profileButton.perform(click());
        MainPage.profileMenu.perform(click());
    }

    @Feature(value = "Набор тест кейсов по проверке страниц через меню навигации приложения (GUI)")
    @Story("1.2.1 Страница \"Главная\"")
    @Test
    public void shouldCheckMainPage() {
        //Проверка наличия элементов на странице
        MainPage.mainLogo.check(matches(isDisplayed()));
        MainPage.newsContainer.check(matches(isDisplayed()));
        MainPage.claimContainer.check(matches(isDisplayed()));
    }

    @Feature(value = "Набор тест кейсов по проверке страниц через меню навигации приложения (GUI)")
    @Story("1.2.2 Страница \"Заявки\"")
    @Test
    public void shouldCheckClaimPage() throws InterruptedException {
        MainPage.generalMenu.perform(click());
        MainPage.generalMenuClaim.perform(click());
        Thread.sleep(2000);
        //Проверка наличия элементов на странице
        ClaimMainPage.title.check(matches(isDisplayed()));
        ClaimMainPage.filter.check(matches(isDisplayed()));
        ClaimMainPage.newClaim.check(matches(isDisplayed()));
    }

    @Feature(value = "Набор тест кейсов по проверке страниц через меню навигации приложения (GUI)")
    @Story("1.2.3 Страница \"Новости\"")
    @Test
    public void shouldCheckNewsPage() throws InterruptedException {
        MainPage.generalMenu.perform(click());
        MainPage.generalMenuNews.perform(click());
        Thread.sleep(2000);
        //Проверка наличия элементов на странице
        NewsPage.title.check(matches(isDisplayed()));
        NewsPage.editNews.check(matches(isDisplayed()));
        NewsPage.sort.check(matches(isDisplayed()));
    }

    @Feature(value = "Набор тест кейсов по проверке страниц через меню навигации приложения (GUI)")
    @Story("1.2.4 Страница \"О приложении\"")
    @Test
    public void shouldCheckAboutPage() throws InterruptedException {
        MainPage.generalMenu.perform(click());
        MainPage.generalMenuAbout.perform(click());
        Thread.sleep(2000);
        //Проверка наличия элементов на странице
        AboutPage.versionTitleField.check(matches(isDisplayed()));
        AboutPage.versionNumberField.check(matches(isDisplayed()));
        AboutPage.privacyPolicy.check(matches(isDisplayed()));
        AboutPage.termsUse.check(matches(isDisplayed()));
        //Возвращение на главную
        AboutPage.backButton.perform(click());
    }

    @Feature(value = "Набор тест кейсов по проверке страниц через меню навигации приложения (GUI)")
    @Story("1.2.7 Страница \"Наша Миссия\"")
    @Test
    public void shouldCheckOurMissionPage() throws InterruptedException {
        MainPage.ourMissionButton.perform(click());
        Thread.sleep(2000);
        //Проверка наличия и соответствия заголовка
        OurMissionPage.title.check(matches(isDisplayed())).check(matches(withText("Главное - жить любя")));
        OurMissionPage.ourMissionList.check(matches(isDisplayed()));
    }

    @Feature(value = "Набор тест кейсов по проверке страниц через меню навигации приложения (GUI)")
    @Story("1.2.5 Открытие пользовательского соглашения в разделе \"О приложении\"")
    @Test
    public void shouldCheckTermsUseLink() throws InterruptedException {
        MainPage.generalMenu.perform(click());
        MainPage.generalMenuAbout.perform(click());
        Thread.sleep(2000);

        Intents.init();
        AboutPage.termsUse.perform(click());
        intended(
                hasData("https://vhospice.org/#/terms-of-use")
                );
        Intents.release();
    }

    @Feature(value = "Набор тест кейсов по проверке страниц через меню навигации приложения (GUI)")
    @Story("1.2.6 Открытие политики конфиденциальности в разделе \"О приложении\"")
    @Test
    public void shouldCheckPrivacyPoliceLink() throws InterruptedException {
        MainPage.generalMenu.perform(click());
        MainPage.generalMenuAbout.perform(click());
        Thread.sleep(2000);

        Intents.init();
        AboutPage.privacyPolicy.perform(click());
        intended(
                hasData("https://vhospice.org/#/privacy-policy")
                );
        Intents.release();
    }
}

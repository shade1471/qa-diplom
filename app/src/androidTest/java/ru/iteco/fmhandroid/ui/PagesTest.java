package ru.iteco.fmhandroid.ui;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.hasContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static ru.iteco.fmhandroid.ui.data.customViewAction.CustomViewAction.needWait;
import static ru.iteco.fmhandroid.ui.data.customViewAction.TimeoutEspresso.onViewWithTimeout;
import static ru.iteco.fmhandroid.ui.data.page.AboutPage.policyText;
import static ru.iteco.fmhandroid.ui.data.page.AboutPage.termsOfUseText;

import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.VerificationMode;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Feature;
import io.qameta.allure.kotlin.Story;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.page.AboutPage;
import ru.iteco.fmhandroid.ui.data.page.ClaimMainPage;
import ru.iteco.fmhandroid.ui.data.page.LoginPage;
import ru.iteco.fmhandroid.ui.data.page.MainPage;
import ru.iteco.fmhandroid.ui.data.page.NewsMainPage;
import ru.iteco.fmhandroid.ui.data.page.OurMissionPage;

@RunWith(AllureAndroidJUnit4.class)

public class PagesTest {

    @Rule
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(AppActivity.class);

    @Before
    public void setUp() {
        try {
            needWait(5000);
            LoginPage.validLogIn();
            needWait(8000);
        } catch (Exception e) {
            return;
        }
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
    public void shouldCheckClaimPage() {
        MainPage.openClaimPage();
        //Проверка наличия элементов на странице
        ClaimMainPage.title.check(matches(isDisplayed()));
        ClaimMainPage.filter.check(matches(isDisplayed()));
        ClaimMainPage.newClaim.check(matches(isDisplayed()));
    }

    @Feature(value = "Набор тест кейсов по проверке страниц через меню навигации приложения (GUI)")
    @Story("1.2.3 Страница \"Новости\"")
    @Test
    public void shouldCheckNewsPage() {
        MainPage.openNewsPage();
        //Проверка наличия элементов на странице
        NewsMainPage.title.check(matches(isDisplayed()));
        NewsMainPage.editNews.check(matches(isDisplayed()));
        NewsMainPage.sort.check(matches(isDisplayed()));
    }

    @Feature(value = "Набор тест кейсов по проверке страниц через меню навигации приложения (GUI)")
    @Story("1.2.4 Страница \"О приложении\"")
    @Test
    public void shouldCheckAboutPage() {
        MainPage.openAboutPage();
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
    public void shouldCheckOurMissionPage() {
        MainPage.ourMissionButton.perform(click());
        //Проверка наличия и соответствия заголовка
        OurMissionPage.title.check(matches(isDisplayed())).check(matches(withText("Главное - жить любя")));
        OurMissionPage.ourMissionList.check(matches(isDisplayed()));
    }

    @Feature(value = "Набор тест кейсов по проверке страниц через меню навигации приложения (GUI)")
    @Story("1.2.5 Открытие пользовательского соглашения в разделе \"О приложении\"")
    @Test
    public void shouldCheckTermsUseLink() {
        MainPage.openAboutPage();
        Intents.init();
        AboutPage.termsUse.perform(click());
        intended(
                hasData("https://vhospice.org/#/terms-of-use")
        );
        Intents.release();
        termsOfUseText.check(matches(isDisplayed()));
    }

    @Feature(value = "Набор тест кейсов по проверке страниц через меню навигации приложения (GUI)")
    @Story("1.2.6 Открытие политики конфиденциальности в разделе \"О приложении\"")
    @Test
    public void shouldCheckPrivacyPoliceLink() {
        MainPage.openAboutPage();
        Intents.init();
        AboutPage.privacyPolicy.perform(click());
        intended(
                hasData("https://vhospice.org/#/privacy-policy")
        );
        Intents.release();
        policyText.check(matches(isDisplayed()));
    }
}

package ru.iteco.fmhandroid.ui;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;

import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import ru.iteco.fmhandroid.ui.data.DataHelper;
import ru.iteco.fmhandroid.ui.data.page.ClaimPage;
import ru.iteco.fmhandroid.ui.data.page.LoginPage;
import ru.iteco.fmhandroid.ui.data.page.MainPage;
import ru.iteco.fmhandroid.ui.data.page.NewClaimPage;

@RunWith(AllureAndroidJUnit4.class)
public class ClaimFunctionalTest {
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
            MainPage.generalMenu.perform(click());
            MainPage.generalMenuClaim.perform(click());
            Thread.sleep(2000);
        }
    }

    @Test
    public void shouldCreateNewClaim() throws InterruptedException {
        ClaimPage.newClaim.perform(click());
        Thread.sleep(2000);
        NewClaimPage.theme.perform(replaceText("Срочно украсить палаты к празднику!"));
        NewClaimPage.executor.perform(replaceText("Иванов Данил Динилович"));
        NewClaimPage.date.perform(replaceText(help.getDateToday()));
        NewClaimPage.time.perform(replaceText(help.getTimeNow()));
        Thread.sleep(3000);

    }

}

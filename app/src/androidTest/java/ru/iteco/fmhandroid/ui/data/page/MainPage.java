package ru.iteco.fmhandroid.ui.data.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class MainPage {
    public static ViewInteraction mainLogo = onView(allOf(withId(R.id.trademark_image_view)));
    public static ViewInteraction generalMenu = onView(withId(R.id.main_menu_image_button));
    public static ViewInteraction generalMenuMain = onView(withText("Главная"));
    public static ViewInteraction generalMenuClaim = onView(withText("Заявки"));
    public static ViewInteraction generalMenuNews = onView(withText("Новости"));
    public static ViewInteraction generalMenuAbout = onView(withText("О приложении"));
    public static ViewInteraction ourMissionButton = onView(withId(R.id.our_mission_image_button));
    public static ViewInteraction profileButton = onView(withId(R.id.authorization_image_button));
    public static ViewInteraction profileMenu = onView(withText("Выйти"));
    public static ViewInteraction newsContainer = onView(withId(R.id.container_list_news_include_on_fragment_main));
    public static ViewInteraction claimContainer = onView(withId(R.id.container_list_claim_include_on_fragment_main));

    @Step("Открытие раздела \"Заявки\"")
    public static void openClaimPage() throws InterruptedException {
        MainPage.generalMenu.perform(click());
        MainPage.generalMenuClaim.perform(click());
        Thread.sleep(2000);
    }

    @Step("Открытие раздела \"Новости\"")
    public static void openNewsPage() throws InterruptedException {
        MainPage.generalMenu.perform(click());
        MainPage.generalMenuNews.perform(click());
        Thread.sleep(2000);
    }

    @Step("Открытие раздела \"О приложении\"")
    public static void openAboutPage() throws InterruptedException {
        MainPage.generalMenu.perform(click());
        MainPage.generalMenuAbout.perform(click());
        Thread.sleep(2000);
    }

    @Step("Выход из профиля")
    public static void logOut() throws InterruptedException {
        MainPage.profileButton.perform(click());
        MainPage.profileMenu.perform(click());
        Thread.sleep(2000);
    }


}

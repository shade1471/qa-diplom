package ru.iteco.fmhandroid.ui.data.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.data.customViewAction.CustomViewAction.needWait;
import static ru.iteco.fmhandroid.ui.data.customViewAction.TimeoutEspresso.onViewWithTimeout;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.customViewAction.TimeoutEspresso;

public class MainPage {
    public static TimeoutEspresso.TimedViewInteraction mainLogo = onViewWithTimeout(withId(R.id.trademark_image_view));
    public static TimeoutEspresso.TimedViewInteraction generalMenu = onViewWithTimeout(withId(R.id.main_menu_image_button));
    public static ViewInteraction generalMenuInter = onView(withId(R.id.main_menu_image_button));
    public static TimeoutEspresso.TimedViewInteraction generalMenuMain = onViewWithTimeout(withText("Главная"));
    public static TimeoutEspresso.TimedViewInteraction generalMenuClaim = onViewWithTimeout(withText("Заявки"));
    public static TimeoutEspresso.TimedViewInteraction generalMenuNews = onViewWithTimeout(withText("Новости"));
    public static TimeoutEspresso.TimedViewInteraction generalMenuAbout = onViewWithTimeout(withText("О приложении"));
    public static ViewInteraction generalMenuAboutInter = onView(withText("О приложении"));
    public static TimeoutEspresso.TimedViewInteraction ourMissionButton = onViewWithTimeout(withId(R.id.our_mission_image_button));
    public static TimeoutEspresso.TimedViewInteraction profileButton = onViewWithTimeout(withId(R.id.authorization_image_button));
    public static TimeoutEspresso.TimedViewInteraction profileMenu = onViewWithTimeout(withText("Выйти"));
    public static TimeoutEspresso.TimedViewInteraction newsContainer = onViewWithTimeout(withId(R.id.container_list_news_include_on_fragment_main));
    public static TimeoutEspresso.TimedViewInteraction claimContainer = onViewWithTimeout(withId(R.id.container_list_claim_include_on_fragment_main));

    @Step("Открытие раздела \"Заявки\"")
    public static void openClaimPage() {
        MainPage.generalMenu.perform(click());
        MainPage.generalMenuClaim.perform(click());
    }

    @Step("Открытие раздела \"Новости\"")
    public static void openNewsPage() {
        MainPage.generalMenu.perform(click());
        MainPage.generalMenuNews.perform(click());
    }

    @Step("Открытие раздела \"О приложении\"")
    public static void openAboutPage() {
        MainPage.generalMenuInter.perform(click());
        MainPage.generalMenuAboutInter.perform(click());
        needWait(2000);
    }

    @Step("Выход из профиля")
    public static void logOut() {
        MainPage.profileButton.perform(click());
        MainPage.profileMenu.perform(click());
        needWait(3000);
    }


}

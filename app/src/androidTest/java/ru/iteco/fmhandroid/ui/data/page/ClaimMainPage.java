package ru.iteco.fmhandroid.ui.data.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class ClaimMainPage {
    public static ViewInteraction title = onView(withText("Заявки"));
    public static ViewInteraction filter = onView(withId(R.id.filters_material_button));
    public static ViewInteraction newClaim = onView(withId(R.id.add_new_claim_material_button));
    public static ViewInteraction openStatus = onView(withId(R.id.item_filter_open));
    public static ViewInteraction inProgressStatus = onView(withId(R.id.item_filter_in_progress));
    public static ViewInteraction executedStatus = onView(withId(R.id.item_filter_executed));
    public static ViewInteraction canceledStatus = onView(withId(R.id.item_filter_cancelled));
    public static ViewInteraction okFilterClaim = onView(withId(R.id.claim_list_filter_ok_material_button));

    @Step("Фильтрация заявки со статусом \"Открыта\"")
    public static void filteringByOpen() throws InterruptedException {
        filter.perform(click());
        Thread.sleep(1000);
        inProgressStatus.perform(click());
        okFilterClaim.perform(click());
        Thread.sleep(2000);
    }
    @Step("Фильтрация заявки со статусом \"В работе\"")
    public static void filteringByProgress() throws InterruptedException {
        ClaimMainPage.filter.perform(click());
        Thread.sleep(1000);
        ClaimMainPage.openStatus.perform(click());
        ClaimMainPage.okFilterClaim.perform(click());
        Thread.sleep(2000);
    }



}

package ru.iteco.fmhandroid.ui.data.page;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.data.customViewAction.CustomViewAction.needWait;
import static ru.iteco.fmhandroid.ui.data.customViewAction.TimeoutEspresso.onViewWithTimeout;

import androidx.test.espresso.contrib.RecyclerViewActions;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.customViewAction.TimeoutEspresso;

public class ClaimMainPage {
    public static TimeoutEspresso.TimedViewInteraction title = onViewWithTimeout(10000, withText("Заявки"));
    public static TimeoutEspresso.TimedViewInteraction filter = onViewWithTimeout(10000, withId(R.id.filters_material_button));
    public static TimeoutEspresso.TimedViewInteraction newClaim = onViewWithTimeout(10000, withId(R.id.add_new_claim_material_button));
    public static TimeoutEspresso.TimedViewInteraction openStatus = onViewWithTimeout(10000, withId(R.id.item_filter_open));
    public static TimeoutEspresso.TimedViewInteraction inProgressStatus = onViewWithTimeout(10000, withId(R.id.item_filter_in_progress));
    public static TimeoutEspresso.TimedViewInteraction executedStatus = onViewWithTimeout(10000, withId(R.id.item_filter_executed));
    public static TimeoutEspresso.TimedViewInteraction canceledStatus = onViewWithTimeout(10000, withId(R.id.item_filter_cancelled));
    public static TimeoutEspresso.TimedViewInteraction okFilterClaim = onViewWithTimeout(10000, withId(R.id.claim_list_filter_ok_material_button));
    public static TimeoutEspresso.TimedViewInteraction claimListRecycler = onViewWithTimeout(10000, withId(R.id.claim_list_recycler_view));

    @Step("Фильтрация заявки со статусом \"Открыта\"")
    public static void filteringByOpen() {
        filter.perform(click());
        inProgressStatus.perform(click());
        okFilterClaim.perform(click());
    }

    @Step("Фильтрация заявки со статусом \"В работе\"")
    public static void filteringByProgress() {
        ClaimMainPage.filter.perform(click());
        ClaimMainPage.openStatus.perform(click());
        ClaimMainPage.okFilterClaim.perform(click());
    }

    public static void openByIndex(int i) {
        needWait(500);
        claimListRecycler
                .perform(RecyclerViewActions.actionOnItemAtPosition(i, click()));
    }


}

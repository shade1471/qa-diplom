package ru.iteco.fmhandroid.ui.data.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.ClaimFunctionalTest.isPopupWindow;
import static ru.iteco.fmhandroid.ui.data.customViewAction.CustomViewAction.needWait;
import static ru.iteco.fmhandroid.ui.data.customViewAction.TimeoutEspresso.onViewWithTimeout;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;

import java.util.UUID;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.DataHelper;
import ru.iteco.fmhandroid.ui.data.customViewAction.TimeoutEspresso;

public class NewClaimPage {
    public static TimeoutEspresso.TimedViewInteraction themeParentLayout = onViewWithTimeout(withId(R.id.title_text_input_layout));
    public static TimeoutEspresso.TimedViewInteraction theme = onViewWithTimeout(withHint("Тема"));
    public static TimeoutEspresso.TimedViewInteraction executorParentLayout = onViewWithTimeout(withId(R.id.executor_drop_menu_text_input_layout));
    public static TimeoutEspresso.TimedViewInteraction executor = onViewWithTimeout(withId(R.id.executor_drop_menu_auto_complete_text_view));
    public static TimeoutEspresso.TimedViewInteraction executorIvanov = onViewWithTimeout(withText("Иванов Данил Данилович"));
    public static TimeoutEspresso.TimedViewInteraction dateParentLayout = onViewWithTimeout(withId(R.id.date_in_plan_text_input_layout));
    public static TimeoutEspresso.TimedViewInteraction date = onViewWithTimeout(withId(R.id.date_in_plan_text_input_edit_text));
    public static TimeoutEspresso.TimedViewInteraction timeParentLayout = onViewWithTimeout(withId(R.id.time_in_plan_text_input_layout));
    public static TimeoutEspresso.TimedViewInteraction time = onViewWithTimeout(withId(R.id.time_in_plan_text_input_edit_text));
    public static TimeoutEspresso.TimedViewInteraction descriptionParentLayout = onViewWithTimeout(withId(R.id.description_text_input_layout));
    public static TimeoutEspresso.TimedViewInteraction description = onViewWithTimeout(withId(R.id.description_edit_text));
    public static TimeoutEspresso.TimedViewInteraction saveButton = onViewWithTimeout(withId(R.id.save_button));
    public static TimeoutEspresso.TimedViewInteraction cancelButton = onViewWithTimeout(withId(R.id.cancel_button));
    public static TimeoutEspresso.TimedViewInteraction message = onViewWithTimeout(withText("Заполните пустые поля"));
    public static TimeoutEspresso.TimedViewInteraction okButtonMessage = onViewWithTimeout(withText("OK"));
    public static TimeoutEspresso.TimedViewInteraction claimRecyclerView = onViewWithTimeout(withId(R.id.claim_list_recycler_view));


    @Step("Создание новой полностью заполненной заявки и ее открытие")
    public static void addNewClaimAndOpenIt() {
        DataHelper help = new DataHelper();
        String id = UUID.randomUUID().toString();
        String finalTheme = "NewP " + id;
        //Создание и заполнение новой заявки
        ClaimMainPage.newClaim.perform(click());
        theme.perform(replaceText(finalTheme), closeSoftKeyboard());
        executor.perform(click());
        executorIvanov.inRoot(isPopupWindow()).perform(click());
        date.perform(replaceText(help.getDateToday()), closeSoftKeyboard());
        time.perform(replaceText(help.getTimeNow()), closeSoftKeyboard());
        description.perform(replaceText("Срочно позвонить! Сроки подходят"), closeSoftKeyboard());
        saveButton.perform(click());
        // Поиск заявки в списке по теме. Поиск по RecyclerView
        // https://stackoverflow.com/questions/31394569/how-to-assert-inside-a-recyclerview-in-espresso
        needWait(2000);
        ClaimMainPage.claimListRecycler.perform(RecyclerViewActions.actionOnItem(
                        hasDescendant(withText(finalTheme)),
                        click()));

    }

    @Step("Создание новой заявки без исполнителя")
    public static void addNewOpenClaim() {
        DataHelper help = new DataHelper();
        String id = UUID.randomUUID().toString();
        String finalTheme = "NewO " + id;
        //Создание и заполнение новой заявки без исполнителя
        ClaimMainPage.newClaim.perform(click());
        theme.perform(replaceText(finalTheme), closeSoftKeyboard());
        date.perform(replaceText(help.getDateToday()), closeSoftKeyboard());
        time.perform(replaceText(help.getTimeNow()), closeSoftKeyboard());
        description.perform(replaceText("Кому нибудь срочно позвонить! Сроки подходят!"), closeSoftKeyboard());
        saveButton.perform(scrollTo(), click());
        // Поиск заявки в списке, Поиск по RecyclerView
        // https://stackoverflow.com/questions/31394569/how-to-assert-inside-a-recyclerview-in-espresso
        claimRecyclerView
                .perform(RecyclerViewActions.actionOnItem(
                        hasDescendant(withText(finalTheme)),
                        click()));
    }

}

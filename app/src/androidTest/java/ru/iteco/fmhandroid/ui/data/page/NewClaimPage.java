package ru.iteco.fmhandroid.ui.data.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.ClaimFunctionalTest.isPopupWindow;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;

import java.util.UUID;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.DataHelper;

public class NewClaimPage {
    public static ViewInteraction themeParentLayout = onView(withId(R.id.title_text_input_layout));
    public static ViewInteraction theme = onView(withHint("Тема"));
    public static ViewInteraction executorParentLayout = onView(withId(R.id.executor_drop_menu_text_input_layout));
    public static ViewInteraction executor = onView(withId(R.id.executor_drop_menu_auto_complete_text_view));
    public static ViewInteraction executorIvanov = onView(withText("Иванов Данил Данилович"));
    public static ViewInteraction dateParentLayout = onView(withId(R.id.date_in_plan_text_input_layout));
    public static ViewInteraction date = onView(withId(R.id.date_in_plan_text_input_edit_text));
    public static ViewInteraction timeParentLayout = onView(withId(R.id.time_in_plan_text_input_layout));
    public static ViewInteraction time = onView(withId(R.id.time_in_plan_text_input_edit_text));
    public static ViewInteraction descriptionParentLayout = onView(withId(R.id.description_text_input_layout));
    public static ViewInteraction description = onView(withId(R.id.description_edit_text));
    public static ViewInteraction saveButton = onView(withId(R.id.save_button));
    public static ViewInteraction cancelButton = onView(withId(R.id.cancel_button));
    public static ViewInteraction message = onView(withText("Заполните пустые поля"));
    public static ViewInteraction okButtonMessage = onView(withText("OK"));


    @Step("Создание новой полностью заполненной заявки и ее открытие")
    public static void addNewClaimAndOpenIt() throws InterruptedException {
        DataHelper help = new DataHelper();
        String id = UUID.randomUUID().toString();
        String finalTheme = "NewP " + id;
        //Создание и заполнение новой заявки
        ClaimMainPage.newClaim.perform(click());
        Thread.sleep(2000);
        theme.perform(replaceText(finalTheme), closeSoftKeyboard());
        executor.perform(click());
        executorIvanov.inRoot(isPopupWindow()).perform(click());
        date.perform(replaceText(help.getDateToday()), closeSoftKeyboard());
        time.perform(replaceText(help.getTimeNow()), closeSoftKeyboard());
        description.perform(replaceText("Срочно позвонить! Сроки подходят"), closeSoftKeyboard());
        Thread.sleep(2000);
        saveButton.perform(scrollTo(), click());
        Thread.sleep(5000);
        // Поиск заявки в списке по теме. Поиск по RecyclerView
        // https://stackoverflow.com/questions/31394569/how-to-assert-inside-a-recyclerview-in-espresso
        onView(withId(R.id.claim_list_recycler_view))
                .perform(RecyclerViewActions.actionOnItem(
                        hasDescendant(withText(finalTheme)),
                        click()));
        // Проверка статуса заявки при заполненном исполнителе
        Thread.sleep(2000);
        ClaimPage.statusLabel.check(matches(withText("В работе")));
    }

    @Step("Создание новой заявки без исполнителя")
    public static void addNewOpenClaim() throws InterruptedException {
        DataHelper help = new DataHelper();
        String id = UUID.randomUUID().toString();
        String finalTheme = "NewO " + id;
        //Создание и заполнение новой заявки без исполнителя
        ClaimMainPage.newClaim.perform(click());
        theme.perform(replaceText(finalTheme), closeSoftKeyboard());
        date.perform(replaceText(help.getDateToday()), closeSoftKeyboard());
        time.perform(replaceText(help.getTimeNow()), closeSoftKeyboard());
        description.perform(replaceText("Кому нибудь срочно позвонить! Сроки подходят!"), closeSoftKeyboard());
        Thread.sleep(3000);
        saveButton.perform(scrollTo(), click());
        Thread.sleep(5000);
        // Поиск заявки в списке, Поиск по RecyclerView
        // https://stackoverflow.com/questions/31394569/how-to-assert-inside-a-recyclerview-in-espresso
        onView(withId(R.id.claim_list_recycler_view))
                .perform(RecyclerViewActions.actionOnItem(
                        hasDescendant(withText(finalTheme)),
                        click()));
        // Проверка статуса заявки при заполненном исполнителе
        Thread.sleep(1000);
        ClaimPage.statusLabel.check(matches(withText("Открыта")));
    }

}

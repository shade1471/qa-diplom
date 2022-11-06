package ru.iteco.fmhandroid.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressBack;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.hasSibling;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Root;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.UUID;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Feature;
import io.qameta.allure.kotlin.Story;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.DataHelper;
import ru.iteco.fmhandroid.ui.data.page.ClaimMainPage;
import ru.iteco.fmhandroid.ui.data.page.ClaimPage;
import ru.iteco.fmhandroid.ui.data.page.LoginPage;
import ru.iteco.fmhandroid.ui.data.page.MainPage;
import ru.iteco.fmhandroid.ui.data.page.NewClaimPage;

@RunWith(AllureAndroidJUnit4.class)
public class ClaimFunctionalTest {
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

    @Feature(value = "Набор тест кейсов по проверке функционала события типа Заявка (Функциональное тестирование)")
    @Story("1.3.1 Создание новой заполненной заявки c выбором исполнителя (Happy Path)")
    @Test
    public void shouldCreateNewClaimWithExecutor() throws InterruptedException {
        String id = UUID.randomUUID().toString().substring(0, 23);
        String finalTheme = "Связаться с поставщиками! " + id;
        //Создание и заполнение новой заявки
        ClaimMainPage.newClaim.perform(click());
        Thread.sleep(2000);
        NewClaimPage.theme.perform(replaceText(finalTheme));
        NewClaimPage.executor.perform(click());
        NewClaimPage.executorIvanov.inRoot(isPopupWindow()).perform(click());
        NewClaimPage.date.perform(replaceText(help.getDateToday()));
        NewClaimPage.time.perform(replaceText(help.getTimeNow()));
        NewClaimPage.description.perform(replaceText("Срочно позвонить! Сроки подходят"));
        NewClaimPage.saveButton.perform(scrollTo(), click());
        Thread.sleep(3000);
        // Поиск заявки в списке, Поиск по RecyclerView
        // https://stackoverflow.com/questions/31394569/how-to-assert-inside-a-recyclerview-in-espresso
        onView(withId(R.id.claim_list_recycler_view))
                .perform(RecyclerViewActions.actionOnItem(
                        hasDescendant(withText(finalTheme)),
                        click()));
        // Проверка статуса заявки при заполненном исполнителе
        Thread.sleep(5000);
        ClaimPage.statusLabel.check(matches(withText("В работе")));
    }

    @Feature(value = "Набор тест кейсов по проверке функционала события типа Заявка (Функциональное тестирование)")
    @Story("1.3.2 Попытка создания новой заявки с пустыми полями")
    @Test
    public void shouldCreateEmptyClaim() {
        ClaimMainPage.newClaim.perform(click());
        NewClaimPage.saveButton.perform(click());

        //Проверка на отображение диалогового окна про заполнение пустых полей
        NewClaimPage.message.inRoot(isDialog()).check(matches(isDisplayed()));
        NewClaimPage.okButtonMessage.inRoot(isDialog()).perform(click());

        //Проверка появления некликабельных иконок предупреждения по полям: Тема, Дата, Время, Описание
        NewClaimPage.themeParentLayout.check(matches(hasDescendant(withId(R.id.text_input_end_icon))))
                .check(matches(isNotClickable()));
        NewClaimPage.dateParentLayout.check(matches(hasDescendant(withId(R.id.text_input_end_icon))))
                .check(matches(isNotClickable()));
        NewClaimPage.timeParentLayout.check(matches(hasDescendant(withId(R.id.text_input_end_icon))))
                .check(matches(isNotClickable()));
        NewClaimPage.descriptionParentLayout.check(matches(hasDescendant(withId(R.id.text_input_end_icon))))
                .check(matches(isNotClickable()));
    }

    @Feature(value = "Набор тест кейсов по проверке функционала события типа Заявка (Функциональное тестирование)")
    @Story("1.3.3 Создние новой заявки с незаполненным исполнителем")
    @Test
    public void shouldCreateNewClaimWithoutExecutor() throws InterruptedException {
        String id = UUID.randomUUID().toString().substring(0, 23);
        String finalTheme = "Связаться с поставщиками! " + id;
        //Создание и заполнение новой заявки без исполнителя
        ClaimMainPage.newClaim.perform(click());
        NewClaimPage.theme.perform(replaceText(finalTheme));
        NewClaimPage.date.perform(replaceText(help.getDateToday()));
        NewClaimPage.time.perform(replaceText(help.getTimeNow()));
        NewClaimPage.description.perform(replaceText("Кому нибудь срочно позвонить! Сроки подходят!"));
        NewClaimPage.saveButton.perform(scrollTo(), click());
        Thread.sleep(3000);
        // Поиск заявки в списке, Поиск по RecyclerView
        // https://stackoverflow.com/questions/31394569/how-to-assert-inside-a-recyclerview-in-espresso
        onView(withId(R.id.claim_list_recycler_view))
                .perform(RecyclerViewActions.actionOnItem(
                        hasDescendant(withText(finalTheme)),
                        click()));
        // Проверка статуса заявки при заполненном исполнителе
        Thread.sleep(5000);
        ClaimPage.statusLabel.check(matches(withText("Открыта")));
    }

    @Feature(value = "Набор тест кейсов по проверке функционала события типа Заявка (Функциональное тестирование)")
    @Story("1.3.4 Фильтрация по статусу заявки \"Открыта\"")
    @Test
    public void shouldFilterByOpenStatusLabel() throws InterruptedException {
        ClaimMainPage.filter.perform(click());
        ClaimMainPage.inProgressStatus.perform(click());
        ClaimMainPage.okFilterClaim.perform(click());
        for (int i = 0; i < 5; i++) {
            onView(withId(R.id.claim_list_recycler_view))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(i, click()));
            Thread.sleep(1000);
            //Проверка статуса и что исполнитель не назначен
            ClaimPage.statusLabel.check(matches(withText("Открыта")));
            ClaimPage.executorName.check(matches(withText("НЕ НАЗНАЧЕН")));
            //Нажатие Системной кнопки Back
            onView(isRoot()).perform(ViewActions.pressBack());
        }
    }

    @Feature(value = "Набор тест кейсов по проверке функционала события типа Заявка (Функциональное тестирование)")
    @Story("1.3.4 Фильтрация по статусу заявки \"В работе\"")
    @Test
    public void shouldFilterByInProgressStatusLabel() throws InterruptedException {
        ClaimMainPage.filter.perform(click());
        ClaimMainPage.openStatus.perform(click());
        ClaimMainPage.okFilterClaim.perform(click());
        for (int i = 0; i < 5; i++) {
            onView(withId(R.id.claim_list_recycler_view))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(i, click()));
            Thread.sleep(1000);
            ClaimPage.statusLabel.check(matches(withText("В работе")));
            //Нажатие Системной кнопки Back
            onView(isRoot()).perform(ViewActions.pressBack());
        }
    }

    @Feature(value = "Набор тест кейсов по проверке функционала события типа Заявка (Функциональное тестирование)")
    @Story("1.3.5 Редактирование \"Открытой\" заявки")
    @Test
    public void shouldEditOpenClaim() throws InterruptedException {
        String id = UUID.randomUUID().toString().substring(0, 23);
        String editTheme = "Редактирование " + id;
        ClaimMainPage.filter.perform(click());
        ClaimMainPage.inProgressStatus.perform(click());
        ClaimMainPage.okFilterClaim.perform(click());
        //Открытие первой доступной заявки со статусом "Открыта"
        onView(withId(R.id.claim_list_recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        Thread.sleep(1000);
        ClaimPage.statusLabel.check(matches(withText("Открыта")));
        ClaimPage.executorName.check(matches(withText("НЕ НАЗНАЧЕН")));
        ClaimPage.editButton.perform(click());
        NewClaimPage.theme.perform(replaceText(editTheme));
        Thread.sleep(2000);
        NewClaimPage.saveButton.perform(click());
        Thread.sleep(2000);
        //Проверка, что тема была изменена
        ClaimPage.titleText.check(matches(withText(editTheme)));
        //Нажатие Системной кнопки Back
        onView(isRoot()).perform(ViewActions.pressBack());
    }


}

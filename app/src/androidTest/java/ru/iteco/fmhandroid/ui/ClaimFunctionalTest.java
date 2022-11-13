package ru.iteco.fmhandroid.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.Root;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
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
            LoginPage.validLogIn();
            Thread.sleep(7000);
        } catch (Exception e) {
            MainPage.openClaimPage();
            Thread.sleep(4000);
        }
    }

    @Feature(value = "Набор тест кейсов по проверке функционала события типа Заявка (Функциональное тестирование)")
    @Story("1.3.1 Создание новой заполненной заявки c выбором исполнителя (Happy Path)")
    @Test
    public void shouldCreateNewClaimWithExecutor() throws InterruptedException {
        NewClaimPage.addNewClaimAndOpenIt();
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
        NewClaimPage.addNewOpenClaim();
    }

    @Feature(value = "Набор тест кейсов по проверке функционала события типа Заявка (Функциональное тестирование)")
    @Story("1.3.4 Фильтрация по статусу заявки \"Открыта\"")
    @Test
    public void shouldFilterByOpenStatusLabel() throws InterruptedException {
        ClaimMainPage.filteringByOpen();
        //Перебор 5-ти первых заявок
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
        ClaimMainPage.filteringByProgress();
        //Перебор 5-ти первых заявок
        for (int i = 0; i < 5; i++) {
            onView(withId(R.id.claim_list_recycler_view))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(i, click()));
            Thread.sleep(1000);
            //Проверка статуса
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
        NewClaimPage.addNewOpenClaim();
        Thread.sleep(1000);
        ClaimPage.executorName.check(matches(withText("НЕ НАЗНАЧЕН")));
        ClaimPage.editButton.perform(click());
        NewClaimPage.theme.perform(replaceText(editTheme), closeSoftKeyboard());
        Thread.sleep(2000);
        NewClaimPage.saveButton.perform(click());
        Thread.sleep(2000);
        //Проверка, что тема была изменена
        ClaimPage.titleText.check(matches(withText(editTheme)));
        //Нажатие Системной кнопки Back
        onView(isRoot()).perform(ViewActions.pressBack());
    }

    @Feature(value = "Набор тест кейсов по проверке функционала события типа Заявка (Функциональное тестирование)")
    @Story("1.3.6 Смена статуса \"Открытой\" заявки на \"В работе\"")
    @Test
    public void shouldChangeStatusClaimOpenToWork() throws InterruptedException {
        NewClaimPage.addNewOpenClaim();
        ClaimPage.executorName.check(matches(withText("НЕ НАЗНАЧЕН")));
        ClaimPage.statusProcessingButton.perform(click());
        ClaimPage.statusToWorkButton.perform(click());
        //Проверка, что статус поменялся на "В работе" и исполнитель на Иванов Д.Д.
        ClaimPage.statusLabel.check(matches(withText("В работе")));
        ClaimPage.executorName.check(matches(withText("Иванов Данил Данилович")));
        Thread.sleep(2000);
    }

    @Feature(value = "Набор тест кейсов по проверке функционала события типа Заявка (Функциональное тестирование)")
    @Story("1.3.7 Смена статуса \"В работе\" заявки на \"Выполнена\"")
    @Test
    public void shouldChangeStatusClaimWorkToDone() throws InterruptedException {
        NewClaimPage.addNewClaimAndOpenIt();
        ClaimPage.statusProcessingButton.perform(click());
        ClaimPage.statusToExecuteButton.perform(click());
        Thread.sleep(2000);
        //Ввод комментария
        ClaimPage.addTextDialogComment("Исполнено идеально");
        //Проверка, что статус поменялся на "Выполнена"
        ClaimPage.statusLabel.check(matches(withText("Выполнена")));
    }

    @Feature(value = "Набор тест кейсов по проверке функционала события типа Заявка (Функциональное тестирование)")
    @Story("1.3.8 Добавления комментария к заявке")
    @Test
    public void shouldAddComment() throws InterruptedException {
        String id = UUID.randomUUID().toString().substring(0, 23);
        String newComment = "Комментарий " + id;
        NewClaimPage.addNewClaimAndOpenIt();
        //Добавление комментария
        ClaimPage.addCommentButton.perform(click());
        //Фиксация текущей даты и времени
        String currentTimeForComment = help.getTimeNow();
        String currentDateForComment = help.getDateToday();
        ClaimPage.addTextComment(newComment);
        //Проверка текста комментария
        ClaimPage.commentDescription.check(matches(withText(newComment)));
        //Проверка автора комментария (авторизация под Ивановым Д.Д)
        ClaimPage.commentatorName.check(matches(withText("Иванов Данил Данилович")));
        //Проверка даты и времени комментария
        ClaimPage.commentDate.check(matches(withText(currentDateForComment)));
        ClaimPage.commentTime.check(matches(withText(currentTimeForComment)));
    }

    @Feature(value = "Набор тест кейсов по проверке функционала события типа Заявка (Функциональное тестирование)")
    @Story("1.3.9 Редактирование комментария к заявке")
    @Test
    public void shouldEditComment() throws InterruptedException {
        String id = UUID.randomUUID().toString().substring(0, 23);
        String newComment = "Комментарий " + id;
        String editComment = "Правленный " + id;
        NewClaimPage.addNewClaimAndOpenIt();
        //Добавление комментария
        ClaimPage.addCommentButton.perform(click());
        ClaimPage.addTextComment(newComment);
        //Проверка текста комментария
        ClaimPage.commentDescription.check(matches(withText(newComment)));
        ClaimPage.editCommentButton.perform(click());
        //Фиксация текущей даты и времени
        String currentTimeForComment = help.getTimeNow();
        String currentDateForComment = help.getDateToday();
        ClaimPage.addTextComment(editComment);
        //Проверка автора комментария (авторизация под Ивановым Д.Д)
        ClaimPage.commentatorName.check(matches(withText("Иванов Данил Данилович")));
        //Проверка даты и времени исправленного комментария
        ClaimPage.commentDate.check(matches(withText(currentDateForComment)));
        ClaimPage.commentTime.check(matches(withText(currentTimeForComment)));
    }


}

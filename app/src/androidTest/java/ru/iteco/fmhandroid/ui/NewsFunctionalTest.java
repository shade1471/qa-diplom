package ru.iteco.fmhandroid.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isNotClickable;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

import androidx.test.espresso.Root;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.UUID;

import io.qameta.allure.kotlin.Feature;
import io.qameta.allure.kotlin.Story;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.DataHelper;
import ru.iteco.fmhandroid.ui.data.page.LoginPage;
import ru.iteco.fmhandroid.ui.data.page.MainPage;
import ru.iteco.fmhandroid.ui.data.page.NewNewsPage;
import ru.iteco.fmhandroid.ui.data.page.NewsMainPage;

public class NewsFunctionalTest {

    @Rule
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(AppActivity.class);

    @Before
    public void setUp() throws InterruptedException {
        try {
            Thread.sleep(7000);
            LoginPage.validLogIn();
            Thread.sleep(10000);
            MainPage.openNewsPage();
        } catch (Exception e) {
            MainPage.openNewsPage();
            Thread.sleep(7000);
        }
    }

    @Feature(value = "Набор тест кейсов по проверке функционала события типа Новости (Функциональное тестирование)")
    @Story("1.4.1 Создание новой новости (Happy Path)")
    @Test
    public void shouldAddNews() throws InterruptedException {
        String id = UUID.randomUUID().toString();
        String newTitle = "Праздник " + id;
        NewsMainPage.clickEditAndPlus();
        NewNewsPage.addNews(newTitle);
        //Проверка темы и что статус новости "АКТИВНА"
        onView(withId(R.id.news_list_recycler_view)).check(matches(hasDescendant(withText(newTitle))))
                .check(matches(hasDescendant(withText("АКТИВНА"))));
    }

    @Feature(value = "Набор тест кейсов по проверке функционала события типа Новости (Функциональное тестирование)")
    @Story("1.4.2 Создание новой Новости с пустыми полями")
    @Test
    public void shouldAddEmptyNews() {
        NewsMainPage.clickEditAndPlus();
        NewNewsPage.saveButton.perform(click());
        //Проверка предупреждений по пустым полям
        NewNewsPage.categoryParentLayout.check(matches(hasDescendant(withId(R.id.text_input_end_icon))))
                .check(matches(isNotClickable()));
        NewNewsPage.titleParentLayout.check(matches(hasDescendant(withId(R.id.text_input_end_icon))))
                .check(matches(isNotClickable()));
        NewNewsPage.dateParentLayout.check(matches(hasDescendant(withId(R.id.text_input_end_icon))))
                .check(matches(isNotClickable()));
        NewNewsPage.timeParentLayout.check(matches(hasDescendant(withId(R.id.text_input_end_icon))))
                .check(matches(isNotClickable()));
        NewNewsPage.descriptionParentLayout.check(matches(hasDescendant(withId(R.id.text_input_end_icon))))
                .check(matches(isNotClickable()));
    }

    @Feature(value = "Набор тест кейсов по проверке функционала события типа Новости (Функциональное тестирование)")
    @Story("1.4.3 Удаление существующей новости")
    @Test
    public void shouldDeleteNews() throws InterruptedException {
        String id = UUID.randomUUID().toString();
        String title = "Праздник " + id;
        NewsMainPage.clickEditAndPlus();
        //Создание новости
        NewNewsPage.addNews(title);
        //Удаление новосозданной новости
        NewNewsPage.deleteNews(title);

        //String existNews = "Праздник c3ac4284-995f-4dd2-86dc-fd681922fc28";
        //Проверка, что новость с заданной темой отсутствует
        onView(withId(R.id.news_list_recycler_view)).check(matches(not(hasDescendant(withText(title)))));
    }

    @Feature(value = "Набор тест кейсов по проверке функционала события типа Новости (Функциональное тестирование)")
    @Story("1.4.4 Редактирование существующей новости")
    @Test
    public void shouldEditNews() throws InterruptedException {
        String id = UUID.randomUUID().toString();
        String newId = UUID.randomUUID().toString();
        String title = "Праздник " + id;
        String newTitle = "Ред.Праздник " + newId;
        String newDescription = "Поправим и отметим";
        NewsMainPage.clickEditAndPlus();
        //Создание новости
        NewNewsPage.addNews(title);
        //Редактирование новосозданной новости
        NewNewsPage.editNews(title, newTitle, newDescription);
        //Проверка, что новость с отредактированной темой и описанием присутсвует
        onView(withId(R.id.news_list_recycler_view)).check(matches(hasDescendant(withText(newTitle))))
                .check(matches(hasDescendant(withText(newDescription))));
    }

    @Feature(value = "Набор тест кейсов по проверке функционала события типа Новости (Функциональное тестирование)")
    @Story("1.4.5 Смена статуса у новости с \"Активна\" на \"Не активна\"")
    @Test
    public void shouldChangeNewsStatus() throws InterruptedException {
        String id = UUID.randomUUID().toString();
        String title = "Праздник " + id;
        NewsMainPage.clickEditAndPlus();
        //Создание новости
        NewNewsPage.addNews(title);
        //Редактирование новосозданной новости
        NewNewsPage.changeStatus(title);
        //Проверка, что новость с заданной темой и измененным статусом присутсвует
        onView(withId(R.id.news_list_recycler_view)).check(matches(hasDescendant(withText(title))))
                .check(matches(hasDescendant(withText("НЕ АКТИВНА"))));
    }

    @Feature(value = "Набор тест кейсов по проверке функционала события типа Новости (Функциональное тестирование)")
    @Story("1.4.6 Функционал по дате публикации новости")
    @Test
    public void shouldAddNewsForFutureTime() throws InterruptedException {
        String id = UUID.randomUUID().toString();
        String title = "Будущее " + id;
        NewsMainPage.clickEditAndPlus();
        //Создание новости +2 минуты к текущему времени
        NewNewsPage.addNews(title, 2);
        //проверка, что Новость видна через редактирование новостей
        onView(withId(R.id.news_list_recycler_view)).check(matches(hasDescendant(withText(title))))
                .check(matches(hasDescendant(withText("АКТИВНА"))));
        //Проверка, что новость с заданной темой и  большим временем публикации отсутствует в обычном режиме просмотра
        MainPage.openNewsPage();
        onView(withId(R.id.news_list_recycler_view)).check(matches(not(hasDescendant(withText(title)))));
    }

}

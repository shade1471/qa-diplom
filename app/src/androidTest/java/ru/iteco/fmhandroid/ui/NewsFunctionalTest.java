package ru.iteco.fmhandroid.ui;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.hasSibling;
import static androidx.test.espresso.matcher.ViewMatchers.isNotClickable;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.UUID;

import io.qameta.allure.kotlin.Feature;
import io.qameta.allure.kotlin.Story;
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
        NewsMainPage.newsListRecycler.check(matches(hasDescendant(withText(newTitle))));
        NewsMainPage.findNewsByTheme(newTitle).check(matches(hasSibling(withText("АКТИВНА"))));

    }

    @Feature(value = "Набор тест кейсов по проверке функционала события типа Новости (Функциональное тестирование)")
    @Story("1.4.2 Создание новой Новости с пустыми полями")
    @Test
    public void shouldAddEmptyNews() {
        NewsMainPage.clickEditAndPlus();
        NewNewsPage.saveButton.perform(click());
        //Проверка предупреждений по пустым полям
        NewNewsPage.categoryParentLayout.check(matches(hasDescendant(NewNewsPage.icon)))
                .check(matches(isNotClickable()));
        NewNewsPage.titleParentLayout.check(matches(hasDescendant(NewNewsPage.icon)))
                .check(matches(isNotClickable()));
        NewNewsPage.dateParentLayout.check(matches(hasDescendant(NewNewsPage.icon)))
                .check(matches(isNotClickable()));
        NewNewsPage.timeParentLayout.check(matches(hasDescendant(NewNewsPage.icon)))
                .check(matches(isNotClickable()));
        NewNewsPage.descriptionParentLayout.check(matches(hasDescendant(NewNewsPage.icon)))
                .check(matches(isNotClickable()));
    }

    @Feature(value = "Набор тест кейсов по проверке функционала события типа Новости (Функциональное тестирование)")
    @Story("1.4.3 Удаление существующей новости")
    @Test
    public void shouldDeleteNews() {
        String id = UUID.randomUUID().toString();
        String title = "Праздник " + id;
        NewsMainPage.clickEditAndPlus();
        //Создание новости
        NewNewsPage.addNews(title);
        //Удаление новосозданной новости
        NewNewsPage.deleteNews(title);
        //String existNews = "Праздник c3ac4284-995f-4dd2-86dc-fd681922fc28";
        //Проверка, что новость с заданной темой отсутствует
        NewsMainPage.newsListRecycler.check(matches(not(hasDescendant(withText(title)))));
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
        NewsMainPage.newsListRecycler.check(matches(hasDescendant(withText(newTitle))));
        NewsMainPage.findNewsByTheme(newTitle).check(matches(hasSibling(withText(newDescription))));
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
        NewsMainPage.newsListRecycler.check(matches(hasDescendant(withText(title))));
        NewsMainPage.findNewsByTheme(title).check(matches(hasSibling(withText("НЕ АКТИВНА"))));
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
        NewsMainPage.newsListRecycler.check(matches(hasDescendant(withText(title))));
        NewsMainPage.findNewsByTheme(title).check(matches(hasSibling(withText("АКТИВНА"))));
        //Проверка, что новость с заданной темой и большим временем публикации отсутствует в обычном режиме просмотра
        MainPage.openNewsPage();
        NewsMainPage.newsListRecycler.check(matches(not(hasDescendant(withText(title)))));
    }

}

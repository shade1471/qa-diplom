package ru.iteco.fmhandroid.ui.data.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class NewsMainPage {
    public static ViewInteraction title = onView(withText("Новости"));
    public static ViewInteraction filter = onView(withId(R.id.filter_news_material_button));
    public static ViewInteraction sort = onView(withId(R.id.sort_news_material_button));
    public static ViewInteraction editNews = onView(withId(R.id.edit_news_material_button));
    public static ViewInteraction addNews = onView(withId(R.id.add_news_image_view));
    public static ViewInteraction newsListRecycler = onView(withId(R.id.news_list_recycler_view));

    @Step("Нажать кнопку редактировать и плюс")
    public static void clickEditAndPlus() {
        NewsMainPage.editNews.perform(click());
        NewsMainPage.addNews.perform(click());
    }

    public static ViewInteraction findNewsByTheme(String theme) {
        return onView(withText(theme));
    }

}

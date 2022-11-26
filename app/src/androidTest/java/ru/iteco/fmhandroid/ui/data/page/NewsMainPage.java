package ru.iteco.fmhandroid.ui.data.page;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.data.customViewAction.TimeoutEspresso.onViewWithTimeout;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.customViewAction.TimeoutEspresso;

public class NewsMainPage {
    public static TimeoutEspresso.TimedViewInteraction title = onViewWithTimeout(withText("Новости"));
    public static TimeoutEspresso.TimedViewInteraction filter = onViewWithTimeout(withId(R.id.filter_news_material_button));
    public static TimeoutEspresso.TimedViewInteraction sort = onViewWithTimeout(withId(R.id.sort_news_material_button));
    public static TimeoutEspresso.TimedViewInteraction editNews = onViewWithTimeout(withId(R.id.edit_news_material_button));
    public static TimeoutEspresso.TimedViewInteraction addNews = onViewWithTimeout(withId(R.id.add_news_image_view));
    public static TimeoutEspresso.TimedViewInteraction newsListRecycler = onViewWithTimeout(10000, withId(R.id.news_list_recycler_view));

    @Step("Нажать кнопку редактировать и плюс")
    public static void clickEditAndPlus() {
        NewsMainPage.editNews.perform(click());
        NewsMainPage.addNews.perform(click());
    }

    public static TimeoutEspresso.TimedViewInteraction findNewsByTheme(String theme) {
        return onViewWithTimeout(withText(theme));
    }

}

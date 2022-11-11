package ru.iteco.fmhandroid.ui.data.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class NewsMainPage {
    public static ViewInteraction title = onView(withText("Новости"));
    public static ViewInteraction filter = onView(withId(R.id.filter_news_material_button));
    public static ViewInteraction sort = onView(withId(R.id.sort_news_material_button));
    public static ViewInteraction editNews = onView(withId(R.id.edit_news_material_button));
    public static ViewInteraction addNews = onView(withId(R.id.add_news_image_view));

}

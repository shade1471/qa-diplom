package ru.iteco.fmhandroid.ui.data.page;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.actionWithAssertions;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.assertion.ViewAssertions.selectedDescendantsMatch;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.hasSibling;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.ui.ClaimFunctionalTest.isPopupWindow;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;

import org.hamcrest.Matcher;

import java.util.UUID;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.DataHelper;

public class NewNewsPage {
    public static ViewInteraction titlePage =
            onView(withId(R.id.custom_app_bar_sub_title_text_view));
    public static ViewInteraction category =
            onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    public static ViewInteraction holiday =
            onView(withText("Праздник"));
    public static ViewInteraction title = onView(withId(R.id.news_item_title_text_input_edit_text));
    public static ViewInteraction date =
            onView(withId(R.id.news_item_publish_date_text_input_edit_text));
    public static ViewInteraction time =
            onView(withId(R.id.news_item_publish_time_text_input_edit_text));
    public static ViewInteraction description =
            onView(withId(R.id.news_item_description_text_input_edit_text));
    public static ViewInteraction switcher = onView(withId(R.id.switcher));
    public static ViewInteraction statusNews = onView(withId(R.id.news_item_published_text_view));
    public static ViewInteraction deleteNews = onView(withId(R.id.delete_news_item_image_view));
    public static ViewInteraction saveButton = onView(withId(R.id.save_button));
    public static ViewInteraction cancelButton = onView(withId(R.id.cancel_button));


    public static void addNews() throws InterruptedException {
        DataHelper help = new DataHelper();
        String id = UUID.randomUUID().toString();
        String newTitle = "Праздник " + id;
        category.perform(click());
        holiday.inRoot(isPopupWindow()).perform(click());
        title.perform(replaceText(newTitle), closeSoftKeyboard());
        date.perform(replaceText(help.getDateToday()), closeSoftKeyboard());
        time.perform(replaceText(help.getTimeNow()), closeSoftKeyboard());
        description.perform(replaceText("Задорно отметим"), closeSoftKeyboard());
        saveButton.perform(click());
        Thread.sleep(5000);
        ViewInteraction myNews = onView(withId(R.id.news_list_recycler_view))
                .perform(RecyclerViewActions.actionOnItem(
                        hasDescendant(withText(newTitle)),
                        click()));
        Thread.sleep(4000);
        //Проверка, что статуст новости АКТИВНА
        onView(withId(R.id.news_list_recycler_view)).check(matches(hasDescendant(withText(newTitle))))
                .check(matches(hasDescendant(withText("АКТИВНА"))));

    }


}

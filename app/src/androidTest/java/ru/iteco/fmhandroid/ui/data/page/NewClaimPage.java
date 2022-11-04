package ru.iteco.fmhandroid.ui.data.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class NewClaimPage {
    public static ViewInteraction theme = onView(withHint("Тема"));
    public static ViewInteraction executor = onView(withId(R.id.executor_drop_menu_auto_complete_text_view));
    public static ViewInteraction date = onView(withId(R.id.date_in_plan_text_input_edit_text));
    public static ViewInteraction time = onView(withId(R.id.time_in_plan_text_input_edit_text));
    public static ViewInteraction description = onView(withId(R.id.description_edit_text));
    public static ViewInteraction saveButton = onView(withId(R.id.save_button));
    public static ViewInteraction cancelButton = onView(withId(R.id.cancel_button));

}

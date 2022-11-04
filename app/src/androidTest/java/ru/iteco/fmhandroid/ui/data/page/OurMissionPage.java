package ru.iteco.fmhandroid.ui.data.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class OurMissionPage {
    public static ViewInteraction title = onView(withId(R.id.our_mission_title_text_view));
    public static ViewInteraction ourMissionList = onView(withId(R.id.our_mission_item_list_recycler_view));
}

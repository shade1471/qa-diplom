package ru.iteco.fmhandroid.ui.data.page;

import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static ru.iteco.fmhandroid.ui.data.customViewAction.TimeoutEspresso.onViewWithTimeout;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.customViewAction.TimeoutEspresso;

public class OurMissionPage {
    public static TimeoutEspresso.TimedViewInteraction title = onViewWithTimeout(withId(R.id.our_mission_title_text_view));
    public static TimeoutEspresso.TimedViewInteraction ourMissionList = onViewWithTimeout(withId(R.id.our_mission_item_list_recycler_view));
}

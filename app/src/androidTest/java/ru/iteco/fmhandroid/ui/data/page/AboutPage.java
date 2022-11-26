package ru.iteco.fmhandroid.ui.data.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static ru.iteco.fmhandroid.ui.data.customViewAction.TimeoutEspresso.onViewWithTimeout;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.customViewAction.TimeoutEspresso;

public class AboutPage {
    public static TimeoutEspresso.TimedViewInteraction backButton = onViewWithTimeout(withId(R.id.about_back_image_button));
    public static TimeoutEspresso.TimedViewInteraction versionTitleField = onViewWithTimeout(withId(R.id.about_version_title_text_view));
    public static TimeoutEspresso.TimedViewInteraction versionNumberField = onViewWithTimeout(withId(R.id.about_version_value_text_view));
    public static ViewInteraction privacyPolicy = onView(withId(R.id.about_privacy_policy_value_text_view));
    public static ViewInteraction termsUse = onView(withId(R.id.about_terms_of_use_value_text_view));
}

package ru.iteco.fmhandroid.ui.data.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class AboutPage {
    public static ViewInteraction backButton = onView(withId(R.id.about_back_image_button));
    public static ViewInteraction versionTitleField = onView(withId(R.id.about_version_title_text_view));
    public static ViewInteraction versionNumberField = onView(withId(R.id.about_version_value_text_view));
    public static ViewInteraction privacyPolicy = onView(withId(R.id.about_privacy_policy_value_text_view));
    public static ViewInteraction termsUse = onView(withId(R.id.about_terms_of_use_value_text_view));
}

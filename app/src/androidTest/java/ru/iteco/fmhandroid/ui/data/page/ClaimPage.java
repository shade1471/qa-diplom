package ru.iteco.fmhandroid.ui.data.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class ClaimPage {
    public static ViewInteraction title = onView(withText("Заявки"));
    public static ViewInteraction filter = onView(withId(R.id.filters_material_button));
    public static ViewInteraction newClaim = onView(withId(R.id.add_new_claim_material_button));
}

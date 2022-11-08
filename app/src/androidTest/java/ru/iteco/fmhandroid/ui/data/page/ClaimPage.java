package ru.iteco.fmhandroid.ui.data.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class ClaimPage {
    public static ViewInteraction titleText = onView(withId(R.id.title_text_view));
    public static ViewInteraction statusLabel = onView(withId(R.id.status_label_text_view));
    public static ViewInteraction executorName = onView(withId(R.id.executor_name_text_view));
    public static ViewInteraction addCommentButton = onView(withId(R.id.add_comment_image_button));
    public static ViewInteraction statusProcessingButton = onView(withId(R.id.status_processing_image_button));
    public static ViewInteraction statusToWorkButton = onView(withText("В работу"));
    public static ViewInteraction statusToResetButton = onView(withText("Сбросить"));
    public static ViewInteraction statusToExecuteButton = onView(withText("Исполнить"));
    public static ViewInteraction statusToCancelButton = onView(withText("Отменить"));
    public static ViewInteraction editButton = onView(withId(R.id.edit_processing_image_button));
    public static ViewInteraction closeClaim = onView(withId(R.id.close_image_button));
    public static ViewInteraction commentField = onView(withHint("Комментарий"));
    public static ViewInteraction okButtonMessage = onView(withText("ОК"));


}

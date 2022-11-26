package ru.iteco.fmhandroid.ui.data.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static ru.iteco.fmhandroid.ui.data.customViewAction.TimeoutEspresso.onViewWithTimeout;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.customViewAction.TimeoutEspresso;

public class ClaimPage {
    public static TimeoutEspresso.TimedViewInteraction titleText = onViewWithTimeout(withId(R.id.title_text_view));
    public static TimeoutEspresso.TimedViewInteraction statusLabel = onViewWithTimeout(withId(R.id.status_label_text_view));
    public static TimeoutEspresso.TimedViewInteraction executorName = onViewWithTimeout(withId(R.id.executor_name_text_view));
    public static TimeoutEspresso.TimedViewInteraction addCommentButton = onViewWithTimeout(withId(R.id.add_comment_image_button));
    public static TimeoutEspresso.TimedViewInteraction editCommentButton = onViewWithTimeout(withId(R.id.edit_comment_image_button));
    public static TimeoutEspresso.TimedViewInteraction commentDescription = onViewWithTimeout(withId(R.id.comment_description_text_view));
    public static TimeoutEspresso.TimedViewInteraction commentatorName = onViewWithTimeout(withId(R.id.commentator_name_text_view));
    public static TimeoutEspresso.TimedViewInteraction commentDate = onViewWithTimeout(withId(R.id.comment_date_text_view));
    public static TimeoutEspresso.TimedViewInteraction commentTime = onViewWithTimeout(withId(R.id.comment_time_text_view));
    public static TimeoutEspresso.TimedViewInteraction saveButton = onViewWithTimeout(withId(R.id.save_button));
    public static TimeoutEspresso.TimedViewInteraction statusProcessingButton = onViewWithTimeout(withId(R.id.status_processing_image_button));
    public static TimeoutEspresso.TimedViewInteraction statusToWorkButton = onViewWithTimeout(withText("В работу"));
    public static TimeoutEspresso.TimedViewInteraction statusToResetButton = onViewWithTimeout(withText("Сбросить"));
    public static TimeoutEspresso.TimedViewInteraction statusToExecuteButton = onViewWithTimeout(withText("Исполнить"));
    public static TimeoutEspresso.TimedViewInteraction statusToCancelButton = onViewWithTimeout(withText("Отменить"));
    public static TimeoutEspresso.TimedViewInteraction editButton = onViewWithTimeout(withId(R.id.edit_processing_image_button));
    public static TimeoutEspresso.TimedViewInteraction closeClaim = onViewWithTimeout(withId(R.id.close_image_button));
    public static TimeoutEspresso.TimedViewInteraction commentField = onViewWithTimeout(withHint("Комментарий"));
    public static TimeoutEspresso.TimedViewInteraction okButtonMessage = onViewWithTimeout(withText("ОК"));

    @Step("Написание комментария в диалоговом окне")
    public static void addTextDialogComment(String text) throws InterruptedException {
        commentField.inRoot(isDialog()).perform(replaceText(text), closeSoftKeyboard());
        okButtonMessage.inRoot(isDialog()).perform(click());
        //Thread.sleep(2000);
    }

    @Step("Написание комментария")
    public static void addTextComment(String text) throws InterruptedException {
        commentField.perform(replaceText(text), closeSoftKeyboard());
        saveButton.perform(click());
        //Thread.sleep(2000);
    }


}

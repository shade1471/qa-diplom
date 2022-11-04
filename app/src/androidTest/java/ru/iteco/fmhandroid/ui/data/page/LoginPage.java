package ru.iteco.fmhandroid.ui.data.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class LoginPage {
    public static ViewInteraction loginField = onView(withHint("Логин"));
    public static ViewInteraction passwordField = onView(withHint("Пароль"));
    public static ViewInteraction loginButton = onView(withId(R.id.enter_button));
    public static ViewInteraction title = onView(withText("Авторизация"));
}

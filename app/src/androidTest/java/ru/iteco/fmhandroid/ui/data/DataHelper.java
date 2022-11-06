package ru.iteco.fmhandroid.ui.data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DataHelper {
    public User getValidUser() {
        return new User("login2", "password2");
    }
    public User getNotValidUser() {
        return new User("wrong","wrong2");
    }

    Date currentDate = new Date();
    DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
    DateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
    String dateToday = dateFormat.format(currentDate);
    String timeNow = timeFormat.format(currentDate);

    public String getTimeNow() {
        return timeNow;
    }

    public String getDateToday() {
        return dateToday;
    }

    public class User {
        private String login;
        private String password;

        public User(String login, String password) {
            this.login = login;
            this.password = password;
        }
        public String getLogin() {
            return login;
        }
        public String getPassword() {
            return password;
        }
    }
}

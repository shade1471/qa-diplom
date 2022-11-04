package ru.iteco.fmhandroid.ui.data;

public class DataHelper {
    public User getValidUser() {
        return new User("login2", "password2");
    }
    public User getNotValidUser() {
        return new User("wrong","wrong2");
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

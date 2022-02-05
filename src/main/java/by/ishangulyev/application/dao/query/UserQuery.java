package by.ishangulyev.application.dao.query;

public enum UserQuery {
    SELECT_ALL("SELECT * FROM users;"),
    SELECT_BY_ID("SELECT * FROM users WHERE email = ?"),
    DELETE("DELETE FROM users WHERE email = ?"),
    UPDATE_ROLE("UPDATE users SET role = ? WHERE email = ?"),
    UPDATE_PHOTO("UPDATE users SET photo = ? WHERE email = ?"),
    UPDATE_USERNAME("UPDATE users SET username = ? WHERE email = ?"),
    UPDATE_PASSWORD("UPDATE users SET password = ? WHERE email = ?"),
    INSERT("INSERT INTO users(email,password,create_time,role) VALUES(?,?,?,?)");

    private String value;
    UserQuery(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

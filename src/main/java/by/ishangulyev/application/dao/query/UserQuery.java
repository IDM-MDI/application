package by.ishangulyev.application.dao.query;

public enum UserQuery {
    SELECT_ALL("SELECT * FROM users;"),
    SELECT_BY_ID("SELECT * FROM users WHERE id = ?"),
    DELETE("DELETE FROM users WHERE id = ?"),
    UPDATE("UPDATE users SET username = ?,email = ?,password = ?,create_time = ?,role = ? WHERE id = ?"),
    INSERT("INSERT INTO users(email,password,create_time,role) VALUES(?,?,?,?)");

    private String value;
    UserQuery(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

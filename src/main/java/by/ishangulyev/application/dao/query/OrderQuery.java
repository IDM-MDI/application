package by.ishangulyev.application.dao.query;

public enum OrderQuery {
    SELECT_ALL("SELECT * FROM orders;"),
    SELECT_BY_ID("SELECT * FROM orders WHERE id = ?"),
    DELETE("DELETE FROM orders WHERE id = ?"),
    UPDATE("UPDATE orders SET cartID = ?,gadgetID = ? WHERE id = ?"),
    INSERT("INSERT INTO orders(cartID,gadgetID) VALUES(?,?)");

    private String value;
    OrderQuery(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

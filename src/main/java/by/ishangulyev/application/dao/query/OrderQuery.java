package by.ishangulyev.application.dao.query;

public enum OrderQuery {
    SELECT_ALL("SELECT * FROM orders;"),
    SELECT_BY_ID("SELECT * FROM orders WHERE id = ?"),
    SELECT_BY_CART("SELECT * FROM orders WHERE cartID = ?"),
    SELECT_BY_COUNT("SELECT * FROM orders LIMIT 10 OFFSET ?;"),
    DELETE("DELETE FROM orders WHERE id = ?"),
    DELETE_BY_GADGET("DELETE FROM orders WHERE cartID = ? AND gadgetID = ?"),
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

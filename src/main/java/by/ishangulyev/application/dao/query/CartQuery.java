package by.ishangulyev.application.dao.query;

public enum CartQuery {
    SELECT_ALL("SELECT * FROM carts;"),
    SELECT_BY_ID("SELECT * FROM carts WHERE id = ?"),
    SELECT_BY_EMAIL("SELECT * FROM carts WHERE userID = ?"),
    SELECT_BY_COUNT("SELECT * FROM carts LIMIT 10 OFFSET ?;"),
    DELETE("DELETE FROM carts WHERE id = ?"),
    DELETE_BY_EMAIL("DELETE FROM carts WHERE userID = ?"),
    UPDATE("UPDATE carts SET userID = ? WHERE id = ?"),
    INSERT("INSERT INTO carts(userID) VALUES(?)");
    private String value;

    CartQuery(String value){
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}

package by.ishangulyev.application.dao.query;

public enum CartQuery {
    SELECT_ALL("SELECT * FROM carts;"),
    SELECT_BY_ID("SELECT * FROM carts WHERE id = ?"),
    DELETE("DELETE FROM carts WHERE id = ?"),
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

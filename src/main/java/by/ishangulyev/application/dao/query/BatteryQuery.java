package by.ishangulyev.application.dao.query;

public enum BatteryQuery {
    SELECT_ALL("SELECT * FROM battery;"),
    SELECT_BY_ID("SELECT * FROM battery WHERE id = ?"),
    DELETE("DELETE FROM battery WHERE id = ?"),
    UPDATE("UPDATE battery SET name = ?,mah = ? WHERE id = ?"),
    INSERT("INSERT INTO battery(name,mah) VALUES(?,?)");
    private String value;

    BatteryQuery(String value){
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}

package by.ishangulyev.application.dao.query;

import by.ishangulyev.application.controller.command.JspPath;

public enum BatteryQuery {
    SELECT_ALL("SELECT * FROM battery;"),
    SELECT_ID_NAME("SELECT id,name FROM battery;"),
    SELECT_BY_ID("SELECT * FROM battery WHERE id = ?"),
    SELECT_BY_COUNT("SELECT * FROM battery LIMIT 10 OFFSET ?;"),
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

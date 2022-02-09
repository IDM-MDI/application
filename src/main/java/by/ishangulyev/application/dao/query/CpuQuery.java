package by.ishangulyev.application.dao.query;

public enum CpuQuery {
    SELECT_ALL("SELECT * FROM cpus;"),
    SELECT_ID_NAME("SELECT id,name FROM cpus;"),
    SELECT_BY_ID("SELECT * FROM cpus WHERE id = ?"),
    SELECT_BY_COUNT("SELECT * FROM cpus LIMIT 10 OFFSET ?;"),
    DELETE("DELETE FROM cpus WHERE id = ?"),
    UPDATE("UPDATE cpus SET name = ?,core = ?,frequence = ?,bit = ? WHERE id = ?"),
    INSERT("INSERT INTO cpus(name,core,frequence,bit) VALUES(?,?,?,?)");

    private String value;
    CpuQuery(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

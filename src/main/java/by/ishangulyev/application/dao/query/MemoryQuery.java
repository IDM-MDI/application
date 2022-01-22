package by.ishangulyev.application.dao.query;

public enum MemoryQuery {
    SELECT_ALL("SELECT * FROM memories;"),
    SELECT_BY_ID("SELECT * FROM memories WHERE id = ?"),
    DELETE("DELETE FROM memories WHERE id = ?"),
    UPDATE("UPDATE memories SET name = ?,size = ?, memorytype = ? WHERE id = ?"),
    INSERT("INSERT INTO memories(name,size,memorytype) VALUES(?,?,?)");

    private String value;
    MemoryQuery(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

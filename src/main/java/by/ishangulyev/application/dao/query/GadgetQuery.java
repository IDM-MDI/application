package by.ishangulyev.application.dao.query;

public enum GadgetQuery {
    SELECT_ALL("SELECT * FROM gadgets;"),
    SELECT_COUNT("SELECT * FROM gadgets LIMIT 9 OFFSET 9*?;"),
    SELECT_BY_ID("SELECT * FROM gadgets WHERE id = ?"),
    DELETE("DELETE FROM gadgets WHERE id = ?"),
    UPDATE("UPDATE gadgets" +
            " SET name = ?,smallDescription = ?,bigDescription = ?,photo = ?," +
            "cpuID = ?,memoryID = ?,price = ?,batteryID = ?,videoID = ?,audioID = ?,categoryID = ? " +
            "WHERE id = ?"),
    INSERT("INSERT INTO " +
            "gadgets(name,smallDescription,bigDescription,photo,cpuID,memoryID,price,batteryID,videoID,audioID,categoryID) " +
            "VALUES(?,?,?,?,?,?,?,?,?,?,?)");

    private String value;
    GadgetQuery(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

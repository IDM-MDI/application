package by.ishangulyev.application.dao.query;

public enum VideoQuery {
    SELECT_ALL("SELECT * FROM videos;"),
    SELECT_ID_NAME("SELECT id,name FROM videos;"),
    SELECT_BY_ID("SELECT * FROM videos WHERE id = ?"),
    SELECT_BY_COUNT("SELECT * FROM videos LIMIT 10 OFFSET ?;"),
    DELETE("DELETE FROM videos WHERE id = ?"),
    UPDATE("UPDATE videos SET name = ?,resolution = ?,ratio = ?,brightness = ?,videotype = ? WHERE id = ?"),
    INSERT("INSERT INTO videos(name,resolution,ratio,brightness,videotype) VALUES(?,?,?,?,?)");

    private String value;
    VideoQuery(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

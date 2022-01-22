package by.ishangulyev.application.dao.query;

public enum AudioQuery {
    SELECT_ALL("SELECT * FROM audios;"),
    SELECT_BY_ID("SELECT * FROM audios WHERE id = ?"),
    DELETE("DELETE FROM audios WHERE id = ?"),
    UPDATE("UPDATE audios SET name = ?,audiotype = ?, frequency = ? WHERE id = ?"),
    INSERT("INSERT INTO audios(name,audiotype,frequency) VALUES(?,?,?)");
    private String value;

    AudioQuery(String value){
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}

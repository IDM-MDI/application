package by.ishangulyev.application.dao.query;

public enum CategoryQuery {
    SELECT_ALL("SELECT * FROM categories;"),
    SELECT_ID_NAME("SELECT id,name FROM categories;"),
    SELECT_BY_ID("SELECT * FROM categories WHERE id = ?"),
    SELECT_BY_COUNT("SELECT * FROM categories LIMIT 10 OFFSET ?;"),
    DELETE("DELETE FROM categories WHERE id = ?"),
    UPDATE("UPDATE categories SET name = ? WHERE id = ?"),
    INSERT("INSERT INTO categories(name) VALUES(?)");
    private String value;

    CategoryQuery(String value){
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}

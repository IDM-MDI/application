package by.ishangulyev.application.dao.query;

public enum GadgetQuery {
    SELECT_ALL("SELECT * FROM gadgets;"),
    SELECT_BY_COUNT("SELECT g.id,g.name,g.smallDescription,g.bigDescription,g.photo,g.price,cpus.name,m.name,b.name,v.name,a.name,c.name\n" +
            "FROM gadgets g\n" +
            "INNER JOIN cpus on g.cpuID = cpus.id\n" +
            "INNER JOIN memories m on g.memoryID = m.id\n" +
            "INNER JOIN battery b on g.batteryID = b.id\n" +
            "INNER JOIN videos v on g.videoID = v.id\n" +
            "INNER JOIN audios a on g.audioID = a.id\n" +
            "INNER JOIN categories c on g.categoryID = c.id\n" +
            "LIMIT 10 \n" +
            "OFFSET ?;"),
    SELECT_BY_ID("SELECT g.id,g.name,g.smallDescription,g.bigDescription,g.photo,g.price,cpus.name,m.name,b.name,v.name,a.name,c.name\n" +
            "FROM gadgets g\n" +
            "INNER JOIN cpus on g.cpuID = cpus.id\n" +
            "INNER JOIN memories m on g.memoryID = m.id\n" +
            "INNER JOIN battery b on g.batteryID = b.id\n" +
            "INNER JOIN videos v on g.videoID = v.id\n" +
            "INNER JOIN audios a on g.audioID = a.id\n" +
            "INNER JOIN categories c on g.categoryID = c.id\n" +
            "WHERE g.id = ?;"),
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

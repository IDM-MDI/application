package edu.by.ishangulyev.application.dao.query;

public enum GadgetQuery
{
    SELECT_ALL
    {
        @Override
        public String toString()
        {
            return "SELECT * FROM gadgets;";
        }
    },
    SELECT_BY_ID
    {
        @Override
        public String toString()
        {
            return "SELECT * FROM gadgets WHERE id = ?";
        }
    },
    DELETE
    {
        @Override
        public String toString()
        {
            return "DELETE FROM gadgets WHERE id = ?";
        }
    },
    UPDATE
    {
        @Override
        public String toString()
        {
            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE gadgets").
                    append(" SET ").append("name = ?,").
                    append("smallDescription = ?,").append("bigDescription = ?,").
                    append("photo = ?,").
                    append("cpuID = ?,").append("memoryID = ?,").
                    append("price = ?,").
                    append("batteryID = ?,").append("videoID = ?,").
                    append("audioID = ?,").append("categoryID = ?").
                    append(" WHERE id = ?");
            return sb.toString();
        }
    },
    INSERT
    {
        @Override
        public String toString()
        {
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO gadgets(").
                    append("name,smallDescription,bigDescription,photo,cpuID,").
                    append("memoryID,price,batteryID,videoID,audioID,categoryID").
                    append(") VALUES(?,?,?,?,?,?,?,?,?,?,?)");
            return sb.toString();
        }
    };
}

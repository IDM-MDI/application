package edu.by.ishangulyev.application.dao.query;

public enum VideoQuery
{
    SELECT_ALL
    {
        @Override
        public String toString()
        {
            return "SELECT * FROM videos;";
        }
    },
    SELECT_BY_ID
    {
        @Override
        public String toString()
        {
            return "SELECT * FROM videos WHERE id = ?";
        }
    },
    DELETE
    {
        @Override
        public String toString()
        {
            return "DELETE FROM videos WHERE id = ?";
        }
    },
    UPDATE
    {
        @Override
        public String toString()
        {
            return "UPDATE videos SET name = ?,resolution = ?,ratio = ?,brightness = ?,videotype = ? WHERE id = ?";
        }
    },
    INSERT
    {
        @Override
        public String toString()
        {
            return "INSERT INTO videos(name,resolution,ratio,brightness,videotype) VALUES(?,?,?,?,?)";
        }
    };
}

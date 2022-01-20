package edu.by.ishangulyev.application.dao.query;

public enum CategoryQuery
{
    SELECT_ALL
    {
        @Override
        public String toString()
        {
            return "SELECT * FROM categories;";
        }
    },
    SELECT_BY_ID
    {
        @Override
        public String toString()
        {
            return "SELECT * FROM categories WHERE id = ?";
        }
    },
    DELETE
    {
        @Override
        public String toString()
        {
            return "DELETE FROM categories WHERE id = ?";
        }
    },
    UPDATE
    {
        @Override
        public String toString()
        {
            return "UPDATE categories SET name = ? WHERE id = ?";
        }
    },
    INSERT
    {
        @Override
        public String toString()
        {
            return "INSERT INTO categories(name) VALUES(?)";
        }
    };
}

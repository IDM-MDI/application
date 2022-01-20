package edu.by.ishangulyev.application.dao.query;

public enum CartQuery
{
    SELECT_ALL
    {
        @Override
        public String toString()
        {
            return "SELECT * FROM carts;";
        }
    },
    SELECT_BY_ID
    {
        @Override
        public String toString()
        {
            return "SELECT * FROM carts WHERE id = ?";
        }
    },
    DELETE
    {
        @Override
        public String toString()
        {
            return "DELETE FROM carts WHERE id = ?";
        }
    },
    UPDATE
    {
        @Override
        public String toString()
        {
            return "UPDATE carts SET userID = ? WHERE id = ?";
        }
    },
    INSERT
    {
        @Override
        public String toString()
        {
            return "INSERT INTO battery(userID) VALUES(?)";
        }
    };
}

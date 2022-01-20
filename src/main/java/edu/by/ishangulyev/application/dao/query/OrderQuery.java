package edu.by.ishangulyev.application.dao.query;

public enum OrderQuery
{
    SELECT_ALL
    {
        @Override
        public String toString()
        {
            return "SELECT * FROM orders;";
        }
    },
    SELECT_BY_ID
    {
        @Override
        public String toString()
        {
            return "SELECT * FROM orders WHERE id = ?";
        }
    },
    DELETE
    {
        @Override
        public String toString()
        {
            return "DELETE FROM orders WHERE id = ?";
        }
    },
    UPDATE
    {
        @Override
        public String toString()
        {
            return "UPDATE orders SET cartID = ?,gadgetID = ? WHERE id = ?";
        }
    },
    INSERT
    {
        @Override
        public String toString()
        {
            return "INSERT INTO orders(cartID,gadgetID) VALUES(?,?)";
        }
    };
}

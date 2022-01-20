package edu.by.ishangulyev.application.dao.query;

public enum BatteryQuery
{
    SELECT_ALL
    {
        @Override
        public String toString()
        {
            return "SELECT * FROM battery;";
        }
    },
    SELECT_BY_ID
     {
         @Override
         public String toString()
         {
             return "SELECT * FROM battery WHERE id = ?";
         }
     },
    DELETE
    {
        @Override
        public String toString()
        {
            return "DELETE FROM battery WHERE id = ?";
        }
    },
    UPDATE
    {
        @Override
        public String toString()
        {
            return "UPDATE battery SET name = ?,mah = ? WHERE id = ?";
        }
    },
    INSERT
    {
        @Override
        public String toString()
        {
            return "INSERT INTO battery(name,mah) VALUES(?,?)";
        }
    };
}

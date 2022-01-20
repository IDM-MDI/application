package edu.by.ishangulyev.application.dao.query;

public enum MemoryQuery
{
    SELECT_ALL
    {
        @Override
        public String toString()
        {
            return "SELECT * FROM memories;";
        }
    },
    SELECT_BY_ID
    {
        @Override
        public String toString()
        {
            return "SELECT * FROM memories WHERE id = ?";
        }
    },
    DELETE
    {
        @Override
        public String toString()
        {
            return "DELETE FROM memories WHERE id = ?";
        }
    },
    UPDATE
    {
        @Override
        public String toString()
        {
            return "UPDATE memories SET name = ?,size = ?, memorytype = ? WHERE id = ?";
        }
    },
    INSERT
    {
        @Override
        public String toString()
        {
            return "INSERT INTO memories(name,size,memorytype) VALUES(?,?,?)";
        }
    };
}

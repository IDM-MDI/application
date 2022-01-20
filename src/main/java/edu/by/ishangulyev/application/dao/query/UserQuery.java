package edu.by.ishangulyev.application.dao.query;

public enum UserQuery
{
    SELECT_ALL
    {
        @Override
        public String toString()
        {
            return "SELECT * FROM users;";
        }
    },
    SELECT_BY_ID
    {
        @Override
        public String toString()
        {
            return "SELECT * FROM users WHERE id = ?";
        }
    },
    DELETE
    {
        @Override
        public String toString()
        {
            return "DELETE FROM users WHERE id = ?";
        }
    },
    UPDATE
    {
        @Override
        public String toString()
        {
            return "UPDATE users SET username = ?,email = ?,password = ?,create_time = ?,role = ? WHERE id = ?";
        }
    },
    INSERT
    {
        @Override
        public String toString()
        {
            return "INSERT INTO users(username,email,password,create_time,role) VALUES(?,?,?,?,?)";
        }
    };
}

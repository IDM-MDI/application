package edu.by.ishangulyev.application.model.entity.impl;

public enum MemoryType
{
    HDD
    {
        @Override
        public String toString()
        {
            return "HDD";
        }
    },
    SDD
    {
        @Override
        public String toString()
        {
            return "SDD";
        }
    },
    RAM
    {
        @Override
        public String toString()
        {
            return "RAM";
        }
    };
}

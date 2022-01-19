package edu.by.ishangulyev.application.model.entity.impl;

public enum AudioType
{
    MONO
    {
        @Override
        public String toString()
        {
            return "MONO";
        }
    },
    STEREO
    {
        @Override
        public String toString()
        {
            return "STEREO";
        }
    },
    SURROUND
    {
        @Override
        public String toString()
        {
            return "SURROUND";
        }
    };
}

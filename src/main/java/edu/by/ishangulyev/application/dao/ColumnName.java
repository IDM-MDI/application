package edu.by.ishangulyev.application.dao;

public enum ColumnName
{
    AUDIO
    {
        @Override
        public String getType()
        {
            return "audiotype";
        }

        @Override
        public String getFrequency()
        {
            return "frequency";
        }
    },
    BATTERY
    {
        @Override
        public String getMaH()
        {
            return "mah";
        }
    },
    CART
    {
        @Override
        public String getUserID()
        {
            return "userID";
        }
    },
    CATEGORY
    {

    },
    CPU
    {
        @Override
        public String getCore()
        {
            return "core";
        }

        @Override
        public String getBit()
        {
            return "bit";
        }

        @Override
        public String getFrequency()
        {
            return "frequence";
        }
    },
    GADGET
    {
        @Override
        public String getSmallDescription()
        {
            return "smallDescription";
        }
        @Override
        public String getBigDescription()
        {
            return "bigDescription";
        }
        @Override
        public String getPhoto()
        {
            return "photo";
        }
        @Override
        public String getPrice()
        {
            return "price";
        }
        @Override
        public String getMemoryID()
        {
            return "memoryID";
        }
        @Override
        public String getCpuID()
        {
            return "cpuID";
        }
        @Override
        public String getBatteryID()
        {
            return "batteryID";
        }
        @Override
        public String getCategoryID()
        {
            return "categoryID";
        }
        @Override
        public String getAudioID()
        {
            return "audioID";
        }
        @Override
        public String getVideoID()
        {
            return "videoID";
        }
    },
    MEMORY
    {
        @Override
        public String getType()
        {
            return "memorytype";
        }

        @Override
        public String getSize()
        {
            return "size";
        }
    },
    ORDER
    {
        @Override
        public String getCartID()
        {
            return "cartID";
        }

        @Override
        public String getGadgetID()
        {
            return "gadgetID";
        }
    },
    USER
    {
        @Override
        public String getName()
        {
            return "username";
        }

        @Override
        public String getEmail()
        {
            return "email";
        }

        @Override
        public String getPassword()
        {
            return "password";
        }

        @Override
        public String getTime()
        {
            return "create_time";
        }

        @Override
        public String getRole()
        {
            return "role";
        }
    },
    VIDEO
    {
        @Override
        public String getType()
        {
            return "videotype";
        }

        @Override
        public String getResolution()
        {
            return "resolution";
        }

        @Override
        public String getRatio()
        {
            return "ratio";
        }

        @Override
        public String getBrightness()
        {
            return "brightness";
        }
    };


    public String getId()
    {
        return "id";
    }
    public String getName()
    {
        return "name";
    }
    public String getType()
    {
        return null;
    }
    public String getFrequency()
    {
        return null;
    }
    public String getMaH()
    {
        return null;
    }
    public String getUserID()
    {
        return null;
    }
    public String getCore()
    {
        return null;
    }
    public String getBit()
    {
        return null;
    }
    public String getSmallDescription()
    {
        return null;
    }
    public String getBigDescription()
    {
        return null;
    }
    public String getPhoto()
    {
        return null;
    }
    public String getPrice()
    {
        return null;
    }
    public String getMemoryID()
    {
        return null;
    }
    public String getCpuID()
    {
        return null;
    }
    public String getBatteryID()
    {
        return null;
    }
    public String getCategoryID()
    {
        return null;
    }
    public String getAudioID()
    {
        return null;
    }
    public String getCartID()
    {
        return null;
    }
    public String getGadgetID()
    {
        return null;
    }
    public String getVideoID()
    {
        return null;
    }
    public String getSize()
    {
        return null;
    }
    public String getResolution()
    {
        return null;
    }
    public String getBrightness()
    {
        return null;
    }
    public String getEmail()
    {
        return null;
    }
    public String getPassword()
    {
        return null;
    }
    public String getTime()
    {
        return null;
    }
    public String getRole()
    {
        return null;
    }
    public String getRatio()
    {
        return null;
    }
}

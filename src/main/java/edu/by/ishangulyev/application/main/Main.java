package edu.by.ishangulyev.application.main;

import edu.by.ishangulyev.application.dao.ColumnName;
import edu.by.ishangulyev.application.dao.impl.DaoAudio;
import edu.by.ishangulyev.application.exception.DataBaseException;
import edu.by.ishangulyev.application.model.entity.impl.Audio;
import edu.by.ishangulyev.application.model.entity.impl.AudioType;

public class Main
{
    public static void main(String[] args) throws DataBaseException
    {
        DaoAudio dao = new DaoAudio();
        Audio entity = new Audio();
        entity.setName("Razor");
        entity.setFrequency(18000);
        entity.setType(AudioType.STEREO);
        entity.setId(2);
        dao.update(entity);
        System.out.println(dao.getAll());
        ColumnName name = ColumnName.VIDEO;
    }
}

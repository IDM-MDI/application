package by.ishangulyev.application.main;

import by.ishangulyev.application.exception.DataBaseException;
import by.ishangulyev.application.model.entity.impl.AudioType;

public class Main {
    public static void main(String[] args) throws DataBaseException {
//        DaoAudio dao = new DaoAudio();
//        Audio entity = new Audio();
//        entity.setName("Razor");
//        entity.setFrequency(18000);
//        entity.setType(AudioType.STEREO);
//        entity.setId(2);
//        dao.update(entity);
//        System.out.println(dao.findAll());
//        ColumnName name = ColumnName.VIDEO;
        AudioType audioType = AudioType.MONO;
        System.out.println(audioType.name());
    }
}

package by.ishangulyev.application.main;

import by.ishangulyev.application.controller.command.RequestParameterValue;
import by.ishangulyev.application.exception.DataBaseException;
import by.ishangulyev.application.model.entity.impl.AudioType;
import by.ishangulyev.application.util.HashPassGenerator;

import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws DataBaseException, NoSuchAlgorithmException {
//        DaoAudio dao = new DaoAudio();
//        Audio entity = new Audio();
//        entity.setName("Razor");
//        entity.setFrequency(18000);
//        entity.setType(AudioType.STEREO);
//        entity.setId(2);
//        dao.update(entity);
//        System.out.println(dao.findAll());
//        ColumnName name = ColumnName.VIDEO;
//        AudioType audioType = AudioType.MONO;
//        System.out.println(audioType.name());
        System.out.println(HashPassGenerator.generate("hello world"));
        System.out.println(RequestParameterValue.valueOf("asd"));
    }
}

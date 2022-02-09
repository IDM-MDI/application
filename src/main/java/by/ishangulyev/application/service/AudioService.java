package by.ishangulyev.application.service;

import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.RouterType;
import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.dao.impl.DaoAudio;
import by.ishangulyev.application.dao.impl.DaoBattery;
import by.ishangulyev.application.exception.DaoException;
import by.ishangulyev.application.model.entity.impl.Audio;
import by.ishangulyev.application.model.entity.impl.AudioType;
import by.ishangulyev.application.model.entity.impl.Battery;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class AudioService {
    private static AudioService instance = new AudioService();
    private AudioService(){}

    public static AudioService getInstance() {
        return instance;
    }

    public boolean add(String name,String type,String frequency){
        boolean result = true;
        DaoAudio daoAudio = new DaoAudio();
        Audio audio = new Audio();
        try{
            audio.setName(name);
            audio.setType(AudioType.valueOf(type.toUpperCase()));
            audio.setFrequency(Integer.parseInt(frequency));
            daoAudio.create(audio);
        }catch (Exception e){
            result = false;
        }
        return result;
    }
    public boolean update(String id,String name,String type,String frequency){
        boolean result = true;
        DaoAudio daoAudio = new DaoAudio();
        Audio audio = new Audio();
        try{
            audio.setId(Long.parseLong(id));
            audio.setName(name);
            audio.setType(AudioType.valueOf(type.toUpperCase()));
            audio.setFrequency(Integer.parseInt(frequency));
            daoAudio.update(audio);
        }catch (Exception e){
            result = false;
        }
        return result;
    }
    public boolean delete(String id){
        DaoAudio daoAudio = new DaoAudio();
        long realId;
        try{
            realId = Long.parseLong(id);
        }
        catch (NumberFormatException e){
            realId = -1;
        }
        return daoAudio.delete(realId);
    }
    public Router get(HttpServletRequest request){
        String page = request.getParameter("page");
        if(page == null || page.isEmpty()){
            page = "1";
        }
        int pageNumber = Integer.parseInt(page);
        int next = 0,prev = pageNumber-1;
        List<Audio> audioList = null;
        DaoAudio daoAudio = new DaoAudio();
        try {
            pageNumber--;
            audioList = daoAudio.findByCount(pageNumber);
            if(audioList.size() > 9){
                next = pageNumber+1;
                audioList.remove(audioList.size()-1);
            }
        } catch (DaoException e) {
            // TODO: 2/8/2022
        }
        request.setAttribute("audioList",audioList);
        request.setAttribute("currentPage",++pageNumber);
        request.setAttribute("nextPage",next);
        request.setAttribute("prevPage",prev);
        return new Router(JspPath.AUDIO_SETTINGS, RouterType.FORWARD);
    }
}

package by.ishangulyev.application.service;

import by.ishangulyev.application.controller.AttributeName;
import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.RouterType;
import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.dao.impl.DaoAudio;
import by.ishangulyev.application.dao.impl.DaoBattery;
import by.ishangulyev.application.dao.impl.DaoVideo;
import by.ishangulyev.application.exception.DaoException;
import by.ishangulyev.application.model.entity.impl.*;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class VideoService {
    private static final Logger logger = LogManager.getLogger();
    private static VideoService instance = new VideoService();
    private VideoService(){}

    public static VideoService getInstance() {
        return instance;
    }

    public boolean add(String name, String resolution, String ratio, String brightness, String type){
        boolean result = true;
        DaoVideo daoVideo = new DaoVideo();
        Video video = new Video();
        try{
            video.setName(name);
            video.setType(VideoType.valueOf(type));
            video.setResolution(resolution);
            video.setRatio(ratio);
            video.setBrightness(Integer.parseInt(brightness));
            daoVideo.create(video);
        }catch (Exception e){
            result = false;
        }
        return result;
    }
    public boolean delete(String id){
        DaoVideo daoVideo = new DaoVideo();
        long realId;
        try{
            realId = Long.parseLong(id);
        }
        catch (NumberFormatException e){
            realId = -1;
        }
        return daoVideo.delete(realId);
    }
    public Router get(HttpServletRequest request){
        String page = request.getParameter(AttributeName.PAGE);
        if(page == null || page.isEmpty()){
            page = "1";
        }
        int pageNumber = Integer.parseInt(page);
        int next = 0,prev = pageNumber-1;
        List<Video> videoList = null;
        DaoVideo daoVideo = new DaoVideo();
        try {
            pageNumber--;
            videoList = daoVideo.findByCount(pageNumber);
            if(videoList.size() > 9){
                next = pageNumber+2;
                videoList.remove(videoList.size()-1);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR,"Error getting all Video");
        }
        request.setAttribute("videoList",videoList);
        request.setAttribute("currentPage",++pageNumber);
        request.setAttribute("nextPage",next);
        request.setAttribute("prevPage",prev);
        return new Router(JspPath.VIDEO_SETTINGS, RouterType.FORWARD);
    }

    public boolean update(String id, String name, String resolution, String ratio, String brightness, String type) {
        DaoVideo daoVideo = new DaoVideo();
        Video video = new Video();
        boolean result = true;
        try{
            video.setId(Long.parseLong(id));
            video.setName(name);
            video.setType(VideoType.valueOf(type.toUpperCase()));
            video.setRatio(ratio);
            video.setResolution(resolution);
            video.setBrightness(Integer.parseInt(brightness));
            daoVideo.update(video);
        }catch (Exception e){
            result = false;
        }
        return result;
    }
}

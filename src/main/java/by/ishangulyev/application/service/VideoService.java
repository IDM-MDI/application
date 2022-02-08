package by.ishangulyev.application.service;

import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.RouterType;
import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.dao.impl.DaoAudio;
import by.ishangulyev.application.dao.impl.DaoBattery;
import by.ishangulyev.application.dao.impl.DaoVideo;
import by.ishangulyev.application.exception.DaoException;
import by.ishangulyev.application.model.entity.impl.Battery;
import by.ishangulyev.application.model.entity.impl.Video;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class VideoService {
    private static VideoService instance = new VideoService();
    private VideoService(){}

    public static VideoService getInstance() {
        return instance;
    }

    public boolean add(Video add){
        DaoVideo daoVideo = new DaoVideo();
        return daoVideo.create(add);
    }
    public boolean update(Video update){
        DaoVideo daoVideo = new DaoVideo();
        return daoVideo.update(update);
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
        String page = request.getParameter("page");
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
                next = pageNumber+1;
                videoList.remove(videoList.size()-1);
            }
        } catch (DaoException e) {
            // TODO: 2/8/2022
        }
        request.setAttribute("videoList",videoList);
        request.setAttribute("currentPage",++pageNumber);
        request.setAttribute("nextPage",next);
        request.setAttribute("prevPage",prev);
        return new Router(JspPath.VIDEO_SETTINGS, RouterType.FORWARD);
    }
}

package by.ishangulyev.application.service;

import by.ishangulyev.application.controller.AttributeName;
import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.RouterType;
import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.dao.impl.DaoAudio;
import by.ishangulyev.application.dao.impl.DaoBattery;
import by.ishangulyev.application.dao.impl.DaoGadget;
import by.ishangulyev.application.exception.DaoException;
import by.ishangulyev.application.model.entity.impl.*;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class GadgetService {
    private static final Logger logger = LogManager.getLogger();
    private static GadgetService instance = new GadgetService();
    private GadgetService(){}

    public static GadgetService getInstance() {
        return instance;
    }

    public boolean add(Gadget add){
        DaoGadget daoGadget = new DaoGadget();
        return daoGadget.create(add);
    }
    public boolean update(Gadget update){
        DaoGadget daoGadget = new DaoGadget();
        return daoGadget.update(update);
    }
    public boolean delete(String id){
        DaoGadget daoGadget = new DaoGadget();
        long realId;
        try{
            realId = Long.parseLong(id);
        }
        catch (NumberFormatException e){
            realId = -1;
        }
        return daoGadget.delete(realId);
    }
    public Router get(HttpServletRequest request){
        String page = request.getParameter(AttributeName.PAGE);

        if(page == null || page.isEmpty()){
            page = "1";
        }
        int pageNumber = Integer.parseInt(page);
        int next = 0,prev = pageNumber-1;
        List<Gadget> gadgetList = null;

        List<Audio> audioList = new ArrayList<>();
        List<Battery> batteryList = new ArrayList<>();
        List<Category> categoryList = new ArrayList<>();
        List<Cpu> cpuList = new ArrayList<>();
        List<Memory> memoryList = new ArrayList<>();
        List<Video> videoList = new ArrayList<>();

        DaoGadget daoGadget = new DaoGadget();
        DaoGadget foreign = new DaoGadget();
        try {
            pageNumber--;
            gadgetList = daoGadget.findByCount(pageNumber);
            foreign.findAllForeign(audioList,batteryList,categoryList,cpuList,memoryList,videoList);
            if(gadgetList.size() > 9){
                next = pageNumber+1;
                gadgetList.remove(gadgetList.size()-1);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR,"Error getting all Gadget");
        }
        request.setAttribute("gadgetList",gadgetList);
        request.setAttribute("audioList",audioList);
        request.setAttribute("batteryList",batteryList);
        request.setAttribute("categoryList",categoryList);
        request.setAttribute("cpuList",cpuList);
        request.setAttribute("memoryList",memoryList);
        request.setAttribute("videoList",videoList);


        request.setAttribute("currentPage",++pageNumber);
        request.setAttribute("nextPage",next);
        request.setAttribute("prevPage",prev);
        return new Router(JspPath.GADGET_SETTINGS, RouterType.FORWARD);
    }
}

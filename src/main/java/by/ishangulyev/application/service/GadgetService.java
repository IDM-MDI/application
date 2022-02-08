package by.ishangulyev.application.service;

import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.RouterType;
import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.dao.impl.DaoAudio;
import by.ishangulyev.application.dao.impl.DaoBattery;
import by.ishangulyev.application.dao.impl.DaoGadget;
import by.ishangulyev.application.exception.DaoException;
import by.ishangulyev.application.model.entity.impl.Battery;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class GadgetService {
    private static GadgetService instance = new GadgetService();
    private GadgetService(){}

    public static GadgetService getInstance() {
        return instance;
    }

    public boolean add(){
        DaoGadget daoGadget = new DaoGadget();
        return daoGadget.create();
    }
    public boolean update(){
        DaoGadget daoGadget = new DaoGadget();
        return daoGadget.update();
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
        String page = request.getParameter("page");
        if(page == null || page.isEmpty()){
            page = "1";
        }
        int pageNumber = Integer.parseInt(page);
        int next = 0,prev = pageNumber-1;
        List<Battery> batteryList = null;
        DaoBattery daoBattery = new DaoBattery();
        try {
            pageNumber--;
            batteryList = daoBattery.findByCount(pageNumber);
            if(batteryList.size() > 9){
                next = pageNumber+1;
                batteryList.remove(batteryList.size()-1);
            }
        } catch (DaoException e) {
            // TODO: 2/8/2022
        }
        request.setAttribute("batteryList",batteryList);
        request.setAttribute("currentPage",++pageNumber);
        request.setAttribute("nextPage",next);
        request.setAttribute("prevPage",prev);
        return new Router(JspPath.GADGET_SETTINGS, RouterType.FORWARD);
    }
}
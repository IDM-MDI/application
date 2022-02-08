package by.ishangulyev.application.service;

import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.RouterType;
import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.dao.impl.DaoAudio;
import by.ishangulyev.application.dao.impl.DaoBattery;
import by.ishangulyev.application.dao.impl.DaoMemory;
import by.ishangulyev.application.exception.DaoException;
import by.ishangulyev.application.model.entity.impl.Battery;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class MemoryService {
    private static MemoryService instance = new MemoryService();
    private MemoryService(){}

    public static MemoryService getInstance() {
        return instance;
    }

    public boolean add(){
        DaoMemory daoMemory = new DaoMemory();
        return daoMemory.create();
    }
    public boolean update(){
        DaoMemory daoMemory = new DaoMemory();
        return daoMemory.update();
    }
    public boolean delete(String id){
        DaoMemory daoMemory = new DaoMemory();
        long realId;
        try{
            realId = Long.parseLong(id);
        }
        catch (NumberFormatException e){
            realId = -1;
        }
        return daoMemory.delete(realId);
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
        return new Router(JspPath.MEMORY_SETTINGS, RouterType.FORWARD);
    }
}

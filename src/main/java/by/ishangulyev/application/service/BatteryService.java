package by.ishangulyev.application.service;

import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.RouterType;
import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.dao.impl.DaoBattery;
import by.ishangulyev.application.exception.DaoException;
import by.ishangulyev.application.model.entity.impl.Battery;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class BatteryService {
    private static BatteryService instance = new BatteryService();
    private BatteryService(){}

    public static BatteryService getInstance() {
        return instance;
    }

    public boolean add(Battery battery){
        DaoBattery daoBattery = new DaoBattery();
        return daoBattery.create(battery);
    }
    public boolean update(Battery update){
        DaoBattery daoBattery = new DaoBattery();
        return daoBattery.update(update);
    }
    public boolean delete(String id){
        boolean result = true;
        DaoBattery daoBattery = new DaoBattery();
        try{
            long delete = Long.parseLong(id);
            daoBattery.delete(delete);
        }catch (NumberFormatException e) {
            result = false;
        }
        return result;
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
        return new Router(JspPath.BATTERY_SETTINGS,RouterType.FORWARD);
    }
}
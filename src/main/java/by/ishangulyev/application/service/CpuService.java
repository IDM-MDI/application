package by.ishangulyev.application.service;

import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.RouterType;
import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.dao.impl.DaoAudio;
import by.ishangulyev.application.dao.impl.DaoBattery;
import by.ishangulyev.application.dao.impl.DaoCpu;
import by.ishangulyev.application.exception.DaoException;
import by.ishangulyev.application.model.entity.impl.Battery;
import by.ishangulyev.application.model.entity.impl.Cpu;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class CpuService {
    private static CpuService instance = new CpuService();
    private CpuService(){}

    public static CpuService getInstance() {
        return instance;
    }
    public boolean add(String name, String core, String frequency, String bit){
        boolean result = true;
        DaoCpu daoCpu = new DaoCpu();
        Cpu cpu = new Cpu();
        try{
            cpu.setName(name);
            cpu.setCore(core);
            cpu.setBit(bit);
            cpu.setFrequency(frequency);
            daoCpu.create(cpu);
        }catch (Exception e){
            result = false;
        }
        return result;
    }
    public boolean delete(String id){
        DaoCpu daoCpu = new DaoCpu();
        long realId;
        try{
            realId = Long.parseLong(id);
        }
        catch (NumberFormatException e){
            realId = -1;
        }
        return daoCpu.delete(realId);
    }
    public Router get(HttpServletRequest request){
        String page = request.getParameter("page");

        if(page == null || page.isEmpty()){
            page = "1";
        }
        int pageNumber = Integer.parseInt(page);
        int next = 0,prev = pageNumber-1;
        List<Cpu> cpuList = null;
        DaoCpu daoCpu = new DaoCpu();
        try {
            pageNumber--;
            cpuList = daoCpu.findByCount(pageNumber);
            if(cpuList.size() > 9){
                next = pageNumber+1;
                cpuList.remove(cpuList.size()-1);
            }
        } catch (DaoException e) {
            // TODO: 2/8/2022
        }
        request.setAttribute("cpuList",cpuList);
        request.setAttribute("currentPage",++pageNumber);
        request.setAttribute("nextPage",next);
        request.setAttribute("prevPage",prev);
        return new Router(JspPath.CPU_SETTINGS, RouterType.FORWARD);
    }

    public boolean update(String id, String name, String core, String frequency, String bit) {
        boolean result = true;
        DaoCpu daoCpu = new DaoCpu();
        Cpu cpu = new Cpu();
        try{
            cpu.setId(Long.parseLong(id));
            cpu.setName(name);
            cpu.setCore(core);
            cpu.setBit(bit);
            cpu.setFrequency(frequency);
            daoCpu.update(cpu);
        }catch (Exception e){
            result = false;
        }
        return result;
    }
}

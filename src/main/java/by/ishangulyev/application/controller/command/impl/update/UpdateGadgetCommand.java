package by.ishangulyev.application.controller.command.impl.update;

import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.RouterType;
import by.ishangulyev.application.controller.command.ActionCommand;
import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.model.entity.impl.Gadget;
import by.ishangulyev.application.service.BatteryService;
import by.ishangulyev.application.service.GadgetService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.math.BigDecimal;

public class UpdateGadgetCommand implements ActionCommand {
    private GadgetService service = GadgetService.getInstance();
    @Override public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router;
        Gadget gadget = fillEntityInfo(request);
        if(gadget != null){
            service.update(gadget);
            router = service.get(request);
        }
        else{
            router = new Router(JspPath.ERROR400, RouterType.FORWARD);
        }
        return router;
    }


    private Gadget fillEntityInfo(HttpServletRequest request){
        Gadget gadget = new Gadget();
        String id = request.getParameter("gadgetId");
        String name = request.getParameter("gadgetName");
        String bDes = request.getParameter("gadgetBigDescription");
        String sDes = request.getParameter("gadgetSmallDescription");
        String price = request.getParameter("gadgetPrice");
        String audio = request.getParameter("gadgetAudio");
        String video = request.getParameter("gadgetVideo");
        String category = request.getParameter("gadgetCategory");
        String battery = request.getParameter("gadgetBattery");
        String cpu = request.getParameter("gadgetCpu");
        String memory = request.getParameter("gadgetMemory");
        try{
            gadget.setId(Long.parseLong(id));
            gadget.setName(name);
            gadget.setBigDescription(bDes);
            gadget.setSmallDescription(sDes);
            gadget.setPrice(BigDecimal.valueOf(Long.parseLong(price)));
            Part part = request.getPart("gadgetPhoto");
            gadget.setMainPhoto(part.getInputStream().readAllBytes());
            gadget.setAudioID(Long.parseLong(audio));
            gadget.setVideoID(Long.parseLong(video));
            gadget.setCategoryID(Long.parseLong(category));
            gadget.setBatteryID(Long.parseLong(battery));
            gadget.setCpyID(Long.parseLong(cpu));
            gadget.setMemoryID(Long.parseLong(memory));
        }catch (Exception e){
            gadget = null;
        }
        return gadget;
    }
}

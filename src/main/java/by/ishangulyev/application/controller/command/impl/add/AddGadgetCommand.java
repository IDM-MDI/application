package by.ishangulyev.application.controller.command.impl.add;

import by.ishangulyev.application.controller.AttributeName;
import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.RouterType;
import by.ishangulyev.application.controller.command.ActionCommand;
import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.model.entity.impl.Gadget;
import by.ishangulyev.application.service.AudioService;
import by.ishangulyev.application.service.GadgetService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.math.BigDecimal;

public class AddGadgetCommand implements ActionCommand {
    private GadgetService service = GadgetService.getInstance();
    @Override public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router;
        Gadget gadget = fillEntityInfo(request);
        if(gadget != null){
            service.add(gadget);
            router = service.get(request);
        }
        else{
            router = new Router(JspPath.ERROR400, RouterType.FORWARD);
        }
        return router;
    }


    private Gadget fillEntityInfo(HttpServletRequest request){
        Gadget gadget = new Gadget();
        String name = request.getParameter(AttributeName.GADGET_NAME);
        String bDes = request.getParameter(AttributeName.GADGET_BD);
        String sDes = request.getParameter(AttributeName.GADGET_SD);
        String price = request.getParameter(AttributeName.GADGET_PRICE);
        String audio = request.getParameter(AttributeName.GADGET_AUDIO);
        String video = request.getParameter(AttributeName.GADGET_VIDEO);
        String category = request.getParameter(AttributeName.GADGET_CATEGORY);
        String battery = request.getParameter(AttributeName.GADGET_BATTERY);
        String cpu = request.getParameter(AttributeName.GADGET_CPU);
        String memory = request.getParameter(AttributeName.GADGET_MEMORY);
        try{
            gadget.setName(name);
            gadget.setBigDescription(bDes);
            gadget.setSmallDescription(sDes);
            gadget.setPrice(BigDecimal.valueOf(Long.parseLong(price)));
            Part part = request.getPart(AttributeName.GADGET_PHOTO);
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

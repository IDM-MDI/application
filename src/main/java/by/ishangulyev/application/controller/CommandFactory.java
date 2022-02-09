package by.ishangulyev.application.controller;

import by.ishangulyev.application.controller.command.ActionCommand;
import by.ishangulyev.application.controller.command.RequestParameterName;
import by.ishangulyev.application.controller.command.RequestParameterValue;
import by.ishangulyev.application.controller.command.impl.*;
import by.ishangulyev.application.controller.command.impl.add.*;
import by.ishangulyev.application.controller.command.impl.delete.*;
import by.ishangulyev.application.controller.command.impl.get.*;
import by.ishangulyev.application.controller.command.impl.update.*;
import jakarta.servlet.http.HttpServletRequest;

public class CommandFactory {
    public ActionCommand getCommand(HttpServletRequest req){
        ActionCommand result = null;
        RequestParameterName commandName = RequestParameterName.valueOf(req.getParameterNames().nextElement().toUpperCase());
        RequestParameterValue commandValue = null;
        if(!(commandName == RequestParameterName.JSP_SWITCHER || commandName == RequestParameterName.LANGUAGE)){
            commandValue = RequestParameterValue.valueOf(req.getParameter(commandName.name().toLowerCase()).toUpperCase());
        }
        switch(commandName){
            case USER -> {
                switch(commandValue){
                    case ADD -> result = new SignUpCommand();
                    case UPDATE -> result = new UpdateUserCommand();
                    case EXIT -> result = new SignOutCommand();
                    case DELETE -> result = new DeleteUserCommand();
                    case ENTER -> result = new SignInCommand();
                    case GET -> result = new GetUserCommand();
                }
            }
            case GADGET -> {
                switch(commandValue){
                    case ADD -> result = new AddGadgetCommand();
                    case UPDATE -> result = new UpdateGadgetCommand();
                    case DELETE -> result = new DeleteGadgetCommand();
                    case GET -> result = new GetGadgetCommand();
                }
            }
            case AUDIO -> {
                switch(commandValue){
                    case ADD -> result = new AddAudioCommand();
                    case UPDATE -> result = new UpdateAudioCommand();
                    case DELETE -> result = new DeleteAudioCommand();
                    case GET -> result = new GetAudioCommand();
                }
            }
            case VIDEO -> {
                switch(commandValue){
                    case ADD -> result = new AddVideoCommand();
                    case UPDATE -> result = new UpdateVideoCommand();
                    case DELETE -> result = new DeleteVideoCommand();
                    case GET -> result = new GetVideoCommand();
                }
            }
            case CATEGORY -> {
                switch(commandValue){
                    case ADD -> result = new AddCategoryCommand();
                    case UPDATE -> result = new UpdateCategoryCommand();
                    case DELETE -> result = new DeleteCategoryCommand();
                    case GET -> result = new GetCategoryCommand();
                }
            }
            case BATTERY -> {
                switch(commandValue){
                    case ADD -> result = new AddBatteryCommand();
                    case UPDATE -> result = new UpdateBatteryCommand();
                    case DELETE -> result = new DeleteBatteryCommand();
                    case GET -> result = new GetBatteryCommand();
                }
            }
            case MEMORY -> {
                switch(commandValue){
                    case ADD -> result = new AddMemoryCommand();
                    case UPDATE -> result = new UpdateMemoryCommand();
                    case DELETE -> result = new DeleteMemoryCommand();
                    case GET -> result = new GetMemoryCommand();
                }
            }
            case CPU -> {
                switch(commandValue){
                    case ADD -> result = new AddCpuCommand();
                    case UPDATE -> result = new UpdateCpuCommand();
                    case DELETE -> result = new DeleteCpuCommand();
                    case GET -> result = new GetCpuCommand();
                }
            }
            case CART -> {
                switch(commandValue){
                    case ADD -> result = new AddCartCommand();
                    case DELETE -> result = new DeleteCartCommand();
                    case GET -> result = new GetCartCommand();
                }
            }
            case LANGUAGE -> result = new LanguageCommand();
            case JSP_SWITCHER -> result = new JspSwitcherCommand();
        }
        return result;
    }

}

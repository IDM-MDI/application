package by.ishangulyev.application.service;

import by.ishangulyev.application.controller.command.ActionCommand;
import by.ishangulyev.application.controller.command.RequestParameterName;
import by.ishangulyev.application.controller.command.RequestParameterValue;
import by.ishangulyev.application.controller.command.impl.*;
import jakarta.servlet.http.HttpServletRequest;

public class RequestService {
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
                    case ADD -> {
                        result = new SignUpCommand();
                    }
                    case UPDATE -> {
                        result = new UpdateUserCommand();
                    }
                    case EXIT -> {
                        result = new SignOutCommand();
                    }
                    case DELETE -> {
                        result = new DeleteUserCommand();
                    }
                    case ENTER -> {
                        result = new SignInCommand();
                    }
                }
            }
            case COMMAND -> {

            }
            case GADGET -> {
                switch(commandValue){
                    case ADD -> {
                        result = new AddGadgetCommand();
                    }
                    case UPDATE -> {
                        result = new UpdateGadgetCommand();
                    }
                    case DELETE -> {
                        result = new DeleteGadgetCommand();
                    }
                }
            }
            case LANGUAGE -> {
                result = new LanguageCommand();
            }
            case JSP_SWITCHER -> {
                result = new JspSwitcherCommand();
            }
        }
        return result;
    }

}

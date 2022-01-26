package by.ishangulyev.application.validator;

import by.ishangulyev.application.controller.command.CommandType;
import by.ishangulyev.application.controller.command.JspPath;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Enumeration;

public class ParameterValidator {

    public boolean isParameterValid(HttpServletRequest request){
        String parameter = request.getParameterNames().nextElement();

//        if(isCommandExist(parameter)){
//            switch (parameter){
//                case
//            }
//        }
        return isCommandExist(parameter);
    }

    public boolean isCommandExist(String name){
        return CommandType.valueOf(name.toUpperCase()) != null;
    }

}

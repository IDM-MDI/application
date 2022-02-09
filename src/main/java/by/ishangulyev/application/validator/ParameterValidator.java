package by.ishangulyev.application.validator;

import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.controller.command.RequestParameterName;
import by.ishangulyev.application.controller.command.RequestParameterValue;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Enumeration;
import java.util.Locale;

public class ParameterValidator {
    private static ParameterValidator instance = new ParameterValidator();

    private ParameterValidator(){}

    public static ParameterValidator getInstance() {
        return instance;
    }

    public boolean isParameterValid(HttpServletRequest request){
        String parameterName = request.getParameterNames().nextElement();
        String parameterValue = request.getParameter(parameterName);
        return isCommandExist(parameterName,parameterValue);
    }

    public boolean isCommandExist(String parameterName, String parameterValue){
        boolean paramNameResult = false,paramValueResult = false;
        RequestParameterName name;
        RequestParameterValue value;
        try{
            name = RequestParameterName.valueOf(parameterName.toUpperCase());
            paramNameResult = true;
            if(name == RequestParameterName.LANGUAGE || name == RequestParameterName.JSP_SWITCHER){
                paramValueResult = true;
            }
            else {
                value = RequestParameterValue.valueOf(parameterValue.toUpperCase());
                paramValueResult = true;
            }
        }catch (IllegalArgumentException e){
        }
        return paramNameResult && paramValueResult;
    }

}

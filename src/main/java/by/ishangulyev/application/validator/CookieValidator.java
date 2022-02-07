package by.ishangulyev.application.validator;

import jakarta.servlet.http.Cookie;

public class CookieValidator {
    private static CookieValidator instance = new CookieValidator();

    private CookieValidator(){}

    public static CookieValidator getInstance() {
        return instance;
    }

    public boolean isCookieExist(Cookie[] cookies, String name){
        boolean result = false;
        for (Cookie i:cookies) {
            if(i.getName().equals(name)){
                result = true;
            }
        }
        return result;
    }
    public boolean isCookieNull(Cookie[] cookies,String name){
        boolean result = false;
        for (Cookie i:cookies) {
            if(i.getName().equals(name) && (i.getValue() == null || i.getValue().isEmpty())){
                result = true;
            }
        }
        return result;
    }
    public boolean isLoginValid(Cookie[] cookies){
       return !isCookieNull(cookies,"pass") && !isCookieNull(cookies,"email")
                && isCookieExist(cookies,"pass") && isCookieExist(cookies,"email");
    }
}

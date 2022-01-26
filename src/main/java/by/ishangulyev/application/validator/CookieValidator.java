package by.ishangulyev.application.validator;

import jakarta.servlet.http.Cookie;

public class CookieValidator {
    public boolean isPasswordHashExist(Cookie[] cookies){
        boolean result = false;
        for (Cookie i:cookies) {
            if(i.getName().equals("pass")){
                result = true;
            }
        }
        return result;
    }
    public boolean isLanguageExist(Cookie[] cookies){
        boolean result = false;
        for (Cookie i:cookies) {
            if(i.getName().equals("language")){
                result = true;
            }
        }
        return result;
    }
}

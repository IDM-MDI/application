package by.ishangulyev.application.service;

import by.ishangulyev.application.controller.command.LanguageType;
import by.ishangulyev.application.util.HashPassGenerator;
import jakarta.servlet.http.Cookie;

import java.security.NoSuchAlgorithmException;

public class PasswordHashService {

    public Cookie createCookie(String pass) throws NoSuchAlgorithmException {
        Cookie cookie = new Cookie("pass", HashPassGenerator.generate(pass));
        cookie.setMaxAge(60*60);
        return cookie;
    }
}

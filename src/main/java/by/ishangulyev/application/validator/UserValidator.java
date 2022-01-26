package by.ishangulyev.application.validator;

import by.ishangulyev.application.model.entity.impl.User;

public class UserValidator {
    private static final UserValidator instance = new UserValidator();
    private final String EMAIL_REGEX = "[a-z0-9]+@[a-z]+\\.[a-z]{2,3}";
    private final String PASS_REGEX = "[a-zA-Z0-9]{5,15}";

    private UserValidator(){}

    public static UserValidator getInstance()
    {
        return instance;
    }

    public boolean isUserValid(User entity){
        return isEmailValid(entity) && isPasswordValid(entity);
    }

    public boolean isEmailValid(User entity){
        boolean result = false;
        if(entity.getEmail().matches(EMAIL_REGEX)){
            result = true;
        }
        return result;
    }

    public boolean isPasswordValid(User entity){
        boolean result = false;
        if(entity.getPass().matches(PASS_REGEX)){
            result = true;
        }
        return result;
    }
}

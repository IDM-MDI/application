package edu.by.ishangulyev.application.controller.command;

import edu.by.ishangulyev.application.controller.command.impl.LanguageCommand;
import edu.by.ishangulyev.application.controller.command.impl.SignInCommand;
import edu.by.ishangulyev.application.controller.command.impl.SignUpCommand;
import jakarta.servlet.http.HttpServletRequest;

public enum CommandType
{
    SIGN_IN()
    {
        public ActionCommand getCommand()
        {
            return new SignInCommand();
        }
    },
    SIGN_UP()
    {
        public ActionCommand getCommand()
        {
            return new SignUpCommand();
        }
    },
    LANGUAGE()
    {
        public ActionCommand getCommand()
       {
           return new LanguageCommand();
       }
    };

    public ActionCommand getCommand()
    {
        return null;
    }
}

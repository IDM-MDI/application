package by.ishangulyev.application.controller.command;

import by.ishangulyev.application.controller.command.impl.JspSwitcherCommand;
import by.ishangulyev.application.controller.command.impl.LanguageCommand;
import by.ishangulyev.application.controller.command.impl.SignInCommand;
import by.ishangulyev.application.controller.command.impl.SignUpCommand;

public enum CommandType {
    SIGN_IN() {
        public ActionCommand getCommand() {
            return new SignInCommand();
        }
    },
    SIGN_UP() {
        public ActionCommand getCommand() {
            return new SignUpCommand();
        }
    },
    LANGUAGE() {
        public ActionCommand getCommand() {
            return new LanguageCommand();
        }
    },
    JSP_SWITCHER(){
        public ActionCommand getCommand() {return new JspSwitcherCommand();}
    };

    public ActionCommand getCommand() {
        return null;
    }
}

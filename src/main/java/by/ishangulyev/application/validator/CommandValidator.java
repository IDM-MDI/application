package by.ishangulyev.application.validator;

public class CommandValidator {
    private CommandValidator instance = new CommandValidator();

    private CommandValidator(){}

    public CommandValidator getInstance() {
        return instance;
    }
}

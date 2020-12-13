package by.mvashkewi4.wt.controller.command;

import by.mvashkewi4.wt.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private Map<String, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put("login", new LogInCommand());
        commands.put("logout", new LogOutCommand());
        commands.put("signUp", new SignUpCommand());
        commands.put("go_login", new GoToLogInCommand());
        commands.put("go_signUp", new GoToSignUpCommand());
        commands.put("go_index", new GoToIndexCommand());
        commands.put("go_accounts", new GoToAccountsCommand());
        commands.put("go_addAccount", new GoToAddAccountCommand());
        commands.put("addAccount", new AddAccountCommand());
    }

    public Command getCommand(String commandName) {
        return commands.get(commandName);
    }
}

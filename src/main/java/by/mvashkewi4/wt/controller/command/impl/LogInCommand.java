package by.mvashkewi4.wt.controller.command.impl;

import by.mvashkewi4.wt.controller.command.Command;
import by.mvashkewi4.wt.entity.User;
import by.mvashkewi4.wt.service.ClientService;
import by.mvashkewi4.wt.service.ServiceException;
import by.mvashkewi4.wt.service.ServiceProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogInCommand implements Command {
    private static final String PARAMETER_LOGIN = "login";
    private static final String PARAMETER_PASSWORD = "password";

    private static final String MAIN_PAGE = "/index";
    private static final String DEFAULT_PAGE = "/login";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login;
        String password;

        login = request.getParameter(PARAMETER_LOGIN);
        password = request.getParameter(PARAMETER_PASSWORD);

        ServiceProvider provider = ServiceProvider.getInstance();
        ClientService service = provider.getClientService();

        String page;

        try {
            User user = service.logIn(login, password);

            if (user != null) {
                request.getServletContext().setAttribute("user", user);
                page = MAIN_PAGE;
            } else {
                request.setAttribute("error", "login or password error");
                page = DEFAULT_PAGE;
            }
        } catch (ServiceException e) {
            request.setAttribute("error", "login or password error");
            page = DEFAULT_PAGE;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}

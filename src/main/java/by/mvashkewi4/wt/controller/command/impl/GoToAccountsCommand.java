package by.mvashkewi4.wt.controller.command.impl;

import by.mvashkewi4.wt.controller.command.Command;
import by.mvashkewi4.wt.entity.Account;
import by.mvashkewi4.wt.entity.User;
import by.mvashkewi4.wt.service.PaymentService;
import by.mvashkewi4.wt.service.ServiceException;
import by.mvashkewi4.wt.service.ServiceProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoToAccountsCommand implements Command {
    private static final String ACCOUNTS_PAGE = "/accounts";
    private static final String MAIN_PAGE = "/main";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceProvider provider = ServiceProvider.getInstance();
        PaymentService service = provider.getPaymentService();

        List<Account> accounts;
        String page = MAIN_PAGE;
        User user = (User) request.getServletContext().getAttribute("user");
        if (user != null) {
            try {
                accounts = service.getAccounts(user);
                request.setAttribute("accounts", accounts);
                page = ACCOUNTS_PAGE;
            } catch (ServiceException e) {
                request.setAttribute("error", "Can't get accounts");
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}

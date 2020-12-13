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

public class AddAccountCommand implements Command {
    public static final String PARAMETER_ACCOUNT_NUMBER = "account_number";

    public static final String MAIN_PAGE = "/main";
    public static final String ACCOUNTS_PAGE = "/controller?command=go_accounts";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceProvider provider = ServiceProvider.getInstance();
        PaymentService service = provider.getPaymentService();

        String page = MAIN_PAGE;
        User user = (User) request.getServletContext().getAttribute("user");
        if (user != null) {

            Account account = new Account(user.getId(), 1, request.getParameter(PARAMETER_ACCOUNT_NUMBER));
            try {
                service.addAccount(account);
                page = ACCOUNTS_PAGE;
            } catch (ServiceException e) {
                request.setAttribute("error", "Can't add account");
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}

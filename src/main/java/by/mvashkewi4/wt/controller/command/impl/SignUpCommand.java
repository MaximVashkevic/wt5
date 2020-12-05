package by.mvashkewi4.wt.controller.command.impl;

import by.mvashkewi4.wt.controller.command.Command;
import by.mvashkewi4.wt.entity.*;
import by.mvashkewi4.wt.service.ClientService;
import by.mvashkewi4.wt.service.ServiceException;
import by.mvashkewi4.wt.service.ServiceProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

public class SignUpCommand implements Command {
    private static final String PARAMETER_LOGIN = "login";
    private static final String PARAMETER_PASSWORD = "password";
    private static final String PARAMETER_CODEWORD = "codeword";
    private static final String PARAMETER_EMAIL = "email";
    private static final String PARAMETER_SERIES = "series";
    private static final String PARAMETER_NUMBER = "number";
    private static final String PARAMETER_PERSONAL_NUMBER = "personal_number";
    private static final String PARAMETER_ISSUE_DATE = "issue_date";
    private static final String PARAMETER_VALIDITY_DATE = "validity_date";
    private static final String PARAMETER_BIRTH_DATE = "birth_date";
    private static final String PARAMETER_SEX = "sex";
    private static final String PARAMETER_AUTHORITY = "authority";
    private static final String PARAMETER_NAME = "name";
    private static final String PARAMETER_SURNAME = "surname";
    private static final String PARAMETER_MIDDLENAME = "middlename";
    private static final String PARAMETER_NAME_ENG = "name_eng";
    private static final String PARAMETER_SURNAME_ENG = "surname_eng";
    private static final String PARAMETER_COUNTRY = "country";
    private static final String PARAMETER_REGION = "region";
    private static final String PARAMETER_DISTRICT = "district";
    private static final String PARAMETER_SETTLEMENT_TYPE = "settlement_type";
    private static final String PARAMETER_SETTLEMENT_NAME = "settlement_name";
    private static final String PARAMETER_STREET_TYPE = "street_type";
    private static final String PARAMETER_STREET_NAME = "street_name";
    private static final String PARAMETER_HOUSE = "house";
    private static final String PARAMETER_BUILDING = "building";
    private static final String PARAMETER_FLAT = "flat";
    private static final String PARAMETER_ZIPCODE = "zipcode";
    private static final String PARAMETER_PHONE_NUMBER = "phone_number";

    private static final String AUTH_PAGE = "/login";
    private static final String REGISTRATION_PAGE = "/signUp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceProvider provider = ServiceProvider.getInstance();
        ClientService service = provider.getClientService();

        User user = new User(
                request.getParameter(PARAMETER_LOGIN),
                request.getParameter(PARAMETER_PASSWORD),
                Role.UserRoleId
        );

        ClientData clientData = new ClientData(
                request.getParameter(PARAMETER_CODEWORD),
                request.getParameter(PARAMETER_EMAIL)
        );

        String page;

        try {
            PassportData passportData = new PassportData(
                    request.getParameter(PARAMETER_SERIES),
                    Integer.parseInt(request.getParameter(PARAMETER_NUMBER)),
                    request.getParameter(PARAMETER_PERSONAL_NUMBER),
                    Date.valueOf(request.getParameter(PARAMETER_ISSUE_DATE)),
                    Date.valueOf(request.getParameter(PARAMETER_VALIDITY_DATE)),
                    Date.valueOf(request.getParameter(PARAMETER_BIRTH_DATE)),
                    Sex.valueOf(request.getParameter(PARAMETER_SEX)),
                    request.getParameter(PARAMETER_AUTHORITY),
                    request.getParameter(PARAMETER_NAME),
                    request.getParameter(PARAMETER_SURNAME),
                    request.getParameter(PARAMETER_MIDDLENAME),
                    request.getParameter(PARAMETER_NAME_ENG),
                    request.getParameter(PARAMETER_SURNAME_ENG)
            );

            Address address = new Address(
                    request.getParameter(PARAMETER_COUNTRY),
                    request.getParameter(PARAMETER_REGION),
                    request.getParameter(PARAMETER_DISTRICT),
                    request.getParameter(PARAMETER_SETTLEMENT_TYPE),
                    request.getParameter(PARAMETER_SETTLEMENT_NAME),
                    request.getParameter(PARAMETER_STREET_TYPE),
                    request.getParameter(PARAMETER_STREET_NAME),
                    Short.parseShort(request.getParameter(PARAMETER_HOUSE)),
                    Byte.valueOf(request.getParameter(PARAMETER_BUILDING)),
                    Integer.valueOf(request.getParameter(PARAMETER_FLAT)),
                    request.getParameter(PARAMETER_ZIPCODE),
                    request.getParameter(PARAMETER_PHONE_NUMBER)
            );
            try {
                service.signUp(user, clientData, passportData, address);
                request.setAttribute("login", user.getLogin());
                page = AUTH_PAGE;
            } catch (ServiceException e) {
                e.printStackTrace();
                request.setAttribute("error", "Error. Try again!");
                page = REGISTRATION_PAGE;
            }
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", "Error. Invalid data.");
            page = REGISTRATION_PAGE;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}

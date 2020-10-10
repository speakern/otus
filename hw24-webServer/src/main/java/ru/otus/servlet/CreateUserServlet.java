package ru.otus.servlet;

import ru.otus.model.AddressDataSet;
import ru.otus.model.PhoneDataSet;
import ru.otus.model.User;
import ru.otus.services.DBServiceUser;
import ru.otus.services.TemplateProcessor;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CreateUserServlet extends HttpServlet {

    private static final String PARAM_NAME = "name";
    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_PHONE1 = "phone1";
    private static final String PARAM_PHONE2 = "phone2";
    private static final String PARAM_ADDRESS = "address";
    private static final String USERS_PAGE_TEMPLATE = "create_user.html";

    private final TemplateProcessor templateProcessor;
    private final DBServiceUser dbServiceUser;

    public CreateUserServlet(TemplateProcessor templateProcessor, DBServiceUser dbServiceUser) {
        this.templateProcessor = templateProcessor;
        this.dbServiceUser = dbServiceUser;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
        Map<String, Object> paramsMap = new HashMap<>();

        response.setContentType("text/html");
        response.getWriter().println(templateProcessor.getPage(USERS_PAGE_TEMPLATE, paramsMap));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String name = request.getParameter(PARAM_NAME);
        String login =  request.getParameter(PARAM_LOGIN);
        String password = request.getParameter(PARAM_PASSWORD);
        String phone1 =  request.getParameter(PARAM_PHONE1);
        String phone2 =  request.getParameter(PARAM_PHONE2);
        String address =  request.getParameter(PARAM_ADDRESS);

        if ((name.length() > 3) && (login.length() > 3) && (password.length() > 3)) {
            User user = new User(0, name, login, password);

            if (address.length() > 3){
                user.setAddressDataSet(new AddressDataSet(address, user));
            }

            var listPhone = new ArrayList<PhoneDataSet>();
            if (phone1.length() > 3) listPhone.add(new PhoneDataSet(phone1, user));
            if (phone2.length() > 3) listPhone.add(new PhoneDataSet(phone2, user));
            if (listPhone.size() > 0) user.setPhoneDataSets(listPhone);

            dbServiceUser.saveUser(user);
        }
        response.sendRedirect("/users");
    }
}

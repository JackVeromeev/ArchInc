package com.netcracker.veromeev.archinc.assistant;

import com.netcracker.veromeev.archinc.entity.User;
import com.netcracker.veromeev.archinc.enumeration.UserType;
import com.netcracker.veromeev.archinc.service.RegisterService;
import com.netcracker.veromeev.archinc.service.ServiceException;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by jack on 24/04/17.
 *
 * @author Jack Veromeyev
 */
public class RoleCheckAssistant {

    private static RoleCheckAssistant instance = null;

    private static final String USER_COOKIE_NAME = "archinc_username";
    private static final String USER_COOKIE_PASSWORD = "archinc_password";
    private static int cookieAge = 3600;

    private RoleCheckAssistant() {

        Properties cookieProperty = new Properties();

        try (InputStream stream = getClass().getClassLoader().
                getResourceAsStream("cookie.properties")) {

            cookieProperty.load(stream);

            cookieAge = Integer.parseInt(
                    cookieProperty.getProperty("sessionMaxTimeMin")) * 60;

        } catch (IOException e) {
            LoggerFactory.getLogger(RoleCheckAssistant.class).error(
                    "File cookie.properties is not found. " +
                            "Cookie age is set to default (1 hour)", e);
        }
    }

    public static synchronized RoleCheckAssistant getInstance() {
        if (instance == null) {
            instance = new RoleCheckAssistant();
        }
        return instance;
    }

    public User parseUserFromCookie(HttpServletRequest request)
            throws ServiceException {
        Cookie[] cookies = request.getCookies();
        String username = null;
        String password = null;
        if(cookies !=null){
            for (Cookie cookie : cookies) {
                if(USER_COOKIE_NAME.equals(cookie.getName())) {
                    username = cookie.getValue();
                }
                if (USER_COOKIE_PASSWORD.equals(cookie.getName())) {
                    password = cookie.getValue();
                }
            }
        }
        if (username == null || password == null) {
            return new User(0, UserType.NOT_AUTHORIZED, "", "");
        }
        return RegisterService.getInstance().signIn(username, password);
    }

    public void addUserToCookie(User user, HttpServletResponse response) {
        Cookie userNameCookie = new Cookie(USER_COOKIE_NAME, user.getLogin());
        userNameCookie.setMaxAge(cookieAge);
        Cookie userPassCookie =
                new Cookie(USER_COOKIE_PASSWORD, user.getPassword());
        userPassCookie.setMaxAge(cookieAge);
        response.addCookie(userNameCookie);
        response.addCookie(userPassCookie);
    }
}

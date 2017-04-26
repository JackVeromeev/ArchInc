package com.netcracker.veromeev.archinc.assistant.cookie;

import com.netcracker.veromeev.archinc.command.CommandException;
import com.netcracker.veromeev.archinc.entity.User;
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
public class CookieHandler {

    private static CookieHandler instance = null;

    private static final String USER_COOKIE_NAME = "archinc_username";
    private static final String USER_COOKIE_PASSWORD = "archinc_password";
    private static int cookieAge = 3600;

    private CookieHandler() {

        Properties cookieProperty = new Properties();

        try (InputStream stream = getClass().getClassLoader().
                getResourceAsStream("cookie.properties")) {

            cookieProperty.load(stream);

            cookieAge = Integer.parseInt(
                    cookieProperty.getProperty("sessionMaxTimeMin")) * 60;

        } catch (IOException e) {
            LoggerFactory.getLogger(CookieHandler.class).error(
                    "File cookie.properties is not found. " +
                            "Cookie age is set to default (1 hour)", e);
        }
    }

    public static synchronized CookieHandler getInstance() {
        if (instance == null) {
            instance = new CookieHandler();
        }
        return instance;
    }

    /**
     * parses cookies from request and returns User objects, if cookies contain
     * information about him
     * @param request HTTPRequest
     * @return user object if cookie contain it
     * @throws ServiceException if user was not found
     */
    public User parseUserFromCookie(HttpServletRequest request)
            throws CookieException {
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
            throw new CookieException("user not found in cookie");
        }
        try{
            return RegisterService.getInstance().findUserByLoginEncryptedPass(
                    username, password);
        } catch (ServiceException e) {
            throw new CookieException("user not found in DB", e);
        }
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

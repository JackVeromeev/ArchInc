package com.netcracker.veromeev.archinc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jack on 24/04/17.
 *
 * @author Jack Veromeyev
 */
public class Controller extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(Controller.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("login");
        String pass = request.getParameter("password");
        String buttonSignIn = request.getParameter("signin");
        String buttonSignUp = request.getParameter("signup");
        logger.info("name=" + name + "     pass=" + pass + "    signin=" + buttonSignIn + "    signup=" + buttonSignUp);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

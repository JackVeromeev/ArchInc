package com.netcracker.veromeev.archinc.command;

import com.netcracker.veromeev.archinc.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminCommand extends Command {

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response,
                          User user) {
        return "";
    }
}

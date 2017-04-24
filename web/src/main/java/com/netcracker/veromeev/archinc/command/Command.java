package com.netcracker.veromeev.archinc.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jack on 24/04/17.
 *
 * @author Jack Veromeyev
 */
interface Command {
    void execute(HttpServletRequest request, HttpServletResponse response);
}

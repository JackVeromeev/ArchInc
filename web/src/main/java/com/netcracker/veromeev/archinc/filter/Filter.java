package com.netcracker.veromeev.archinc.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by jack on 24/04/17.
 *
 * @author Jack Veromeyev
 */
public class Filter implements javax.servlet.Filter {

    public void destroy() {
    }

    public void doFilter(ServletRequest req,
                         ServletResponse resp,
                         FilterChain chain)
            throws ServletException, IOException {

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}

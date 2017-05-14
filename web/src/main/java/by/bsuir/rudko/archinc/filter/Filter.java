package by.bsuir.rudko.archinc.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;


/**
 * Created by jack on 24/04/17.
 *
 * @author Jack Veromeyev
 */
public class Filter implements javax.servlet.Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(Filter.class);

    public void destroy() {
    }

    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws ServletException {

        try {
            servletRequest.setCharacterEncoding("UTF-8");
            servletResponse.setContentType("text/html; charset=UTF-8");
            servletResponse.setCharacterEncoding("UTF-8");
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception ex) {
            LOGGER.error("Error in Filter", ex);
        }

    }

    @Override
    public void init(FilterConfig config) {}

}

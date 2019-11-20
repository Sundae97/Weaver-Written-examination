package com.sundae.filemanagerserver.filter;

import com.sundae.filemanagerserver.Constant;
import com.sundae.filemanagerserver.util.RSAUtil;
import org.eclipse.jetty.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author daijiyuan
 * @Email 948820549@qq.com
 * @Date 2019/11/18 下午1:22
 * @Description
 */
public class AuthFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("AuthFilter init()");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("AuthFilter doFilter()");
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String X_SID = req.getHeader("X-SID");
        String X_Signature = req.getHeader("X-Signature");

        if( (!StringUtil.isEmpty(X_SID) && !StringUtil.isEmpty(X_Signature)) &&
                RSAUtil.verifySign(X_SID, RSAUtil.getPublicKeyFromResource(Constant.PUBLIC_KEY_PATH), X_Signature)){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        } else{
            resp.sendError(403, "Illegal parameter!");
            logger.info("AuthFilter -> " + req.getRemoteAddr() + " None X-SID or X-Signature");
            return;
        }
    }

    @Override
    public void destroy() {
        logger.info("AuthFilter destroy()");
    }
}

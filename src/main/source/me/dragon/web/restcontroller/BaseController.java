package me.dragon.web.restcontroller;

import me.dragon.config.RestConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by dragon on 11/4/2017.
 */
public class BaseController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){
        this.request = request;
        this.response = response;
        this.session = request.getSession();
    }

    @Resource
    RestConfig restConfig;

    @Value("${env}")
    private String dev;

    protected void printErrorLog(String logName, Exception e) {
        logger.error(logName + "={}", e.getMessage(), e);
    }

}

package com.kgawa.spring2;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class HelloController extends AbstractController
{
    private String viewName;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView mv = new ModelAndView(viewName);
        mv.addObject("greeting", "hey man.");
        return mv;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }
}

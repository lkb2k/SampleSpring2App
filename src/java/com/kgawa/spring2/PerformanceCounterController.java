package com.kgawa.spring2;

import com.kgawa.jmx.HangTimeConfig;
import com.kgawa.jmx.PerformanceCounter;
import com.kgawa.util.Stopwatch;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: Z173466
 * Date: Jun 19, 2010
 * Time: 5:18:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class PerformanceCounterController extends AbstractController {
    private String view;

    public void setView(String view) {
        this.view = view;
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView mv = new ModelAndView(view);

        //start stop watch
        Stopwatch sw = new Stopwatch();
        sw.start();

        //do random  & sleep
        Random r = new Random();
        int rand = 100 + r.nextInt(HangTimeConfig.getInstance().getMaxHangTime());
        Thread.sleep(rand);

        //stop stopwatch
        sw.stop();

        //get bean and add data
        PerformanceCounter pCounter = PerformanceCounter.getInstance(this.getClass().getName());
        pCounter.Add(sw.getElapsedTime());

        //set some model and retuen view
        mv.addObject("sleepTime", rand);
        return mv;
    }

}

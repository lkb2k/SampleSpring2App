package com.kgawa.spring2;

import com.kgawa.jmx.PerformanceCounter;
import com.kgawa.spring2.model.Address;
import com.kgawa.spring2.model.ObjectFactory;
import com.kgawa.spring2.model.PersonalInfo;
import com.kgawa.util.Stopwatch;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: Z173466
 * Date: Jun 17, 2010
 * Time: 11:18:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class PersonalInfoController extends AbstractController {

    private String view;
    private static int viewCount = 0;
    private final static Object viewCountLock = new Object();

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        Stopwatch sw = new Stopwatch();
        sw.start();

        ModelAndView mv = new ModelAndView(view);
        com.kgawa.spring2.model.PersonalInfo info;
        String file = httpServletRequest.getParameter("file");

        if ((file == null) || (file.isEmpty())) {

            file = this.getServletContext().getRealPath("/data/personalInfo.xml");

            ObjectFactory jaxbObjFactory = new ObjectFactory();
            info = jaxbObjFactory.createPersonalInfo();
            info.setName("Magesh Chandramouli");
            info.setAge(38);
            info.setCompanyName("JPMorgan Chase");
            info.setEmployeeGrade("L07");

            Address address = jaxbObjFactory.createAddress();
            address.setCity("Seattle");
            address.setState("WA");
            address.setStreet1("1301 2nd Avenue");
            address.setZip("98101");
            info.setLocation(address);

            /* *
            * Following code serializes an object into an xml
            */
            JAXBContext jc = JAXBContext.newInstance("com.kgawa.spring2.model");
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(info, new File(file));
        }
        else {
            file = this.getServletContext().getRealPath("/data/" + file + ".xml");
        }

        /**
         * Following code deserializes the given xml file into an object
         */
        JAXBContext jc = JAXBContext.newInstance("com.kgawa.spring2.model");
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        info = (PersonalInfo) unmarshaller.unmarshal(new File(file));

        mv.addObject("info", info);
        mv.addObject("viewCount", getViewCount());

        //done .. get some time added to get some counter data
        Thread.sleep(100);
        sw.stop();
        PerformanceCounter pCounter = PerformanceCounter.getInstance(this.getClass().getName());
        pCounter.Add(sw.getElapsedTime());
        return mv;
    }

    private int getViewCount() {
        synchronized (viewCountLock) {
            return ++viewCount;
        }
    }

    public void setView(String view) {
        this.view = view;
    }
}

package com.kgawa.jmx;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: Z173466
 * Date: Jun 19, 2010
 * Time: 11:51:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class PerformanceCounter implements PerformanceCounterMBean {
    private String name;
    private long startTime;
    private long totalCount;
    private double averageResponseTime;
    private double averageCallsPerSecond;
    private static HashMap<String, PerformanceCounter> table = new HashMap<String, PerformanceCounter>();
    private static final Object lock = new Object();

    public String getName() {
        return name;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public double getAverageResponseTime() {
        return averageResponseTime;
    }

    public double getAverageCallsPerSecond() {
        return averageCallsPerSecond;
    }

    public synchronized void Add(long timeInMs) {
        totalCount++;
        long now = System.currentTimeMillis();
        long totalElapsedTime = (now - startTime) == 0 ? 1 : (now - startTime);
        averageCallsPerSecond =  (totalCount / totalElapsedTime) * 1000;
        averageResponseTime = ((averageResponseTime * (totalCount - 1)) + timeInMs) / totalCount;
    }

    private PerformanceCounter(String name) {
        this.name = name;
        this.startTime = System.currentTimeMillis();
    }

    public static PerformanceCounter getInstance(String instanceName) throws MalformedObjectNameException, MBeanRegistrationException, InstanceAlreadyExistsException, NotCompliantMBeanException {
        if (table.containsKey(instanceName)) {
            return table.get(instanceName);
        } else {
            synchronized (lock) {
                MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

                //derive name
                Class c = PerformanceCounter.class;
                String name = String.format("%s:type=%s,name=%s",
                        c.getPackage().getName(), c.getName(), instanceName);
                ObjectName objName = new ObjectName(name);

                // Create MBean
                PerformanceCounter counter = new PerformanceCounter(instanceName);

                // Register the MBean
                mbs.registerMBean(counter, objName);

                //return the MBean
                table.put(instanceName, counter);
                return counter;
            }
        }
    }
}

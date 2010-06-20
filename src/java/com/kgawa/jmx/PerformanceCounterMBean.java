package com.kgawa.jmx;

/**
 * Created by IntelliJ IDEA.
 * User: Z173466
 * Date: Jun 19, 2010
 * Time: 10:26:30 AM
 * To change this template use File | Settings | File Templates.
 */
public interface PerformanceCounterMBean {
    public String getName();

    public double getAverageResponseTime();

    public long getTotalCount();

    public double getAverageCallsPerSecond();
}

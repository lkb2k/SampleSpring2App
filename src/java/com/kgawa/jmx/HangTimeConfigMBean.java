package com.kgawa.jmx;

/**
 * Created by IntelliJ IDEA.
 * User: Z173466
 * Date: Jun 20, 2010
 * Time: 7:14:17 AM
 * To change this template use File | Settings | File Templates.
 */
public interface HangTimeConfigMBean {
    @Description(value = "hang time in milliseconds")
    public int getMaxHangTime();
    public void setMaxHangTime(int value);
}

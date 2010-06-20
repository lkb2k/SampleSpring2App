package com.kgawa.jmx;

import javax.management.*;
import java.lang.management.ManagementFactory;

/**
 * Created by IntelliJ IDEA.
 * User: Z173466
 * Date: Jun 20, 2010
 * Time: 7:15:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class HangTimeConfig extends NotificationBroadcasterSupport implements HangTimeConfigMBean {
    private int maxHangTime = 1000;
    private long sequenceNo = 1;
    private static HangTimeConfig instance = null;
    private static final Object lock = new Object();

    public int getMaxHangTime() {
        return maxHangTime;
    }

    public synchronized void setMaxHangTime(int value) {
        AttributeChangeNotification notification = new AttributeChangeNotification(
                this, ++sequenceNo, System.currentTimeMillis(), "MaxHangTime Changed", "MaxHangTime", "int", maxHangTime, value);

        maxHangTime = value;
        this.sendNotification(notification);
    }

    private HangTimeConfig() {

    }

    public static HangTimeConfig getInstance() throws MalformedObjectNameException, MBeanRegistrationException, InstanceAlreadyExistsException, NotCompliantMBeanException {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new HangTimeConfig();
                    MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

                    //derive name
                    Class c = HangTimeConfig.class;
                    String name = String.format("%s:type=%s",
                            c.getPackage().getName(), c.getName());
                    ObjectName objName = new ObjectName(name);

                    // Register the MBean
                    mbs.registerMBean(instance, objName);
                }
            }
        }

        return instance;
    }

}

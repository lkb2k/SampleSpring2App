<html>
    <title>JMX MBean Test</title>
<head>
</head>
<body>
    <h2>Simple JMX Test</h2>

    <p>
        Open JConsole and look for a MBeans named 'com.kgawa.jmx.PerformanceCounterMBean' and 'com.kgawa.jmx.HangTimeConfig'.
    </p>
    <p>
        <strong>com.kgawa.jmx.HangTimeConfig</strong>  - exposes a read write attribute to set the maximum hang time for this page.
        The attribute 'MaxHangTime' is also annotated with a descriptor to provide a "description" in the attribute's descriptor info
        section.
    </p>
    <p>
        <strong>com.kgawa.jmx.PerformanceCounterMBean</strong>  - exposes some attributes that compute the performance of this page.
        This MBean has two instances. One for this page and another one for JAXB sample page
    </p>

    <p>
        This page was delayed by ${sleepTime} milliseconds.
    </p>
</body>
</html>
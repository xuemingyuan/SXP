package com.alibaba.quartz;

import java.io.Serializable;

public class QuartzTaskMethod implements Serializable
{
    private static final long serialVersionUID = 1L;

    public void quartz1()
    {
        System.out.println("Will run after 5 seconds");
    }
    
    public void quartz2()
    {
        System.out.println("Will run after 50 seconds");
    }
}

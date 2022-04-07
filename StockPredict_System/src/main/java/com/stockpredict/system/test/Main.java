package com.stockpredict.system.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.util.Factory;
import org.apache.shiro.mgt.SecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wangzhewen
 * @time 2022/3/26 22:38
 * @description: TODO
 */
public class Main {

    private static final transient Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        log.info("My First Apache Shiro Application");

        //1.
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");

        //2.
        SecurityManager securityManager = factory.getInstance();

        //3.
        SecurityUtils.setSecurityManager(securityManager);

        System.exit(0);


/*         SmallCat smallCat = new SmallCat("王哲文", "男");
        smallCat.howl();
        smallCat.laugh();
        System.out.println(smallCat);

        SmallCat smallCat1 = new SmallCat(); */

    }

}

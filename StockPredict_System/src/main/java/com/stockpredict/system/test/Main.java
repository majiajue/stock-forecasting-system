package com.stockpredict.system.test;

/**
 * @author wangzhewen
 * @time 2022/3/26 22:38
 * @description: TODO
 */
public class Main {

    public static void main(String[] args) {

        SmallCat smallCat = new SmallCat("王哲文", "男");
        smallCat.howl();
        smallCat.laugh();
        System.out.println(smallCat);

        SmallCat smallCat1 = new SmallCat();

    }

}

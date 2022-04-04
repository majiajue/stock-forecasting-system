package com.stockpredict.system.test;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author wangzhewen
 * @time 2022/3/26 22:36
 * @description: TODO
 */
@Data
@AllArgsConstructor
public class SmallCat extends Cat{

    SmallCat(String name, String sex) {
        super.setName(name);
        super.setSex(sex);
    }

    @Override
    public void howl() {
        System.out.println("aooooooooo！");
    }

    @Override
    public void laugh() {
        System.out.println("hahahahaha!");
    }

    @Override
    public String toString() {
        return super.toString();
    }

}

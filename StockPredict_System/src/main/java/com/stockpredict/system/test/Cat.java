package com.stockpredict.system.test;

import lombok.Data;

/**
 * @author wangzhewen
 * @time 2022/3/26 22:26
 * @description: abstract 不能被实例化，也就没有构造函数
 */
@Data
public abstract class Cat implements Animal {

    private String name;

    protected String sex;

    public abstract void laugh();

    @Override
    public String toString() {
        return this.getName() + this.getSex();
    }

}

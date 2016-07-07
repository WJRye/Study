package com.wj.study.domain;

/**
 * Author：王江 on 2016/7/5 14:28
 * Description:
 */
public class Person implements Comparable<Person> {

    public static final int TYPE_TITLE = 1;
    public static final int TYPE_CONTENT = 2;

    private int type;

    private String name;
    private String pinyin;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    @Override
    public int compareTo(Person o) {
        return this.pinyin.compareTo(o.getPinyin());
    }
}

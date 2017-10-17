package com.wj.study.modules.diffuitl;

import com.wj.study.domain.User;
import com.wj.study.util.PinYin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Author：王江 on 2016/9/29 11:00
 * Description:
 */

public final class UserDao {
    private UserDao() {
    }

    public static List<User> getUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, 33, "王小红", "AB"));
        users.add(new User(2, 44, "张天强", "A"));
        users.add(new User(3, 12, "阿瑶", "O"));
        users.add(new User(4, 33, "白雪", "O"));
        users.add(new User(5, 12, "白玉洁", "A"));
        users.add(new User(6, 21, "蔡宇", "AB"));
        users.add(new User(7, 50, "游华", "O"));
        users.add(new User(8, 17, "曲小雨", "A"));
        users.add(new User(9, 22, "宋静", "B"));
        users.add(new User(10, 31, "刘晨", "B"));
        users.add(new User(11, 13, "华琼", "A"));
        users.add(new User(12, 33, "薛芙", "O"));
        users.add(new User(13, 44, "南梅", "AB"));
        users.add(new User(14, 76, "余槐", "A"));
        users.add(new User(15, 80, "钟兰", "AB"));
        return users;
    }

    public static List<User> getUsersSortedByName() {
        List<User> users = getUsers();
        users.get(0).setName("啊你");
        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return PinYin.getPinYin(o1.getName()).compareTo(PinYin.getPinYin(o2.getName()));
            }
        });
        return users;
    }

    public static List<User> getUsersSortedByAge() {
        List<User> users = getUsers();
        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                if (o1.getAge() > o2.getAge()) {
                    return 1;
                } else if (o1.getAge() == o2.getAge()) {
                    return 0;
                }
                return -1;
            }
        });
        return users;
    }
}

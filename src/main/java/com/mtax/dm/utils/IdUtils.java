package com.mtax.dm.utils;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Stream;

public class IdUtils {
    /**
     * 普通Id生成器,用时间戳生成+指定位随机数生成，
     * 此方法用于单机应用并且并发量不高的情况之下
     *
     * @return
     */
    public static String getId() {
        //获取当前时间戳
        String str = String.valueOf(System.currentTimeMillis());
        ArrayList<String> list = Lists.newArrayList();
        //将时间戳放入到List中
        Stream.of(str.toCharArray()).forEach(item->{
            list.add(item.toString());
        });
        //随机打乱
        Collections.shuffle(list);
        //拼接字符串，并添加2(自定义)位随机数
        return String.join("", list) + randomNumber(2);
    }


    /**
     * 生成指定长度的一个数字字符串
     *
     * @param num
     * @return
     */
    public static String randomNumber(int num) {
        if (num < 1) {
            num = 1;
        }
        Random random = new Random();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < num; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }
}

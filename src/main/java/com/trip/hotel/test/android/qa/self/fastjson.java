package com.trip.hotel.test.android.qa.self;

import com.alibaba.fastjson.JSON;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.apache.velocity.runtime.directive.Foreach;
import org.junit.Test;
import org.uncommons.reportng.ReportNGException;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.alibaba.fastjson.JSONArray.*;

public class fastjson {
    String i;

    @Test
    public void toJson() {
        List<room> list = new ArrayList<room>(3);
        list.add(new room(1, "yyf", 50));
        list.add(new room(2, "yyf2", 50));
        list.add(new room(3, "yyf3", 50));
        list.add(new room(4, "yyf4", 50));
        Map<String, String> map = new HashMap();
        map.put("name", "班长");
        map.put("name2", "体育委员");
        room[] arry = {new room(1, "yyf", 50), new room(1, "yyf", 50)};

        //javaBean转json
        room r = new room(1, "yyf", 50);
        String jsonstring = JSON.toJSONString(r);
        System.out.println(jsonstring);

        //List集合转json
        String stringlist = JSON.toJSONString(list);

        System.out.println("jsonString2" + stringlist);

        //map转json
        String stringmap = JSON.toJSONString(map);
        System.out.println(stringmap);

        //数组转json
        System.out.print(JSON.toJSONString(arry));

        //String 转 jsonobject
        JSONObject jsonObject = JSONObject.parseObject(jsonstring);
        jsonObject.getString("size");


    }

    ;


}


class room {
    private int id;
    private String roomname;
    private int size;

    public room(int id, String roomname, int size) {
        this.id = id;
        this.roomname = roomname;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}

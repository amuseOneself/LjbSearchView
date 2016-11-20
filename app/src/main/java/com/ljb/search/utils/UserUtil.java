package com.ljb.search.utils;

import android.text.TextUtils;

import com.ljb.search.bean.UserInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：ljb
 * 时间：2016/11/19 15:34
 * 邮箱：563773219@qq.com
 * 描述：
 */
public class UserUtil {
    public static List<UserInfo > getUsers(String jsonString){
        List<UserInfo > users = new ArrayList<>();
        if (!TextUtils.isEmpty(jsonString)){
            try {
                JSONObject jsonObject  = new JSONObject(jsonString);
                JSONArray jsonArray  = jsonObject .getJSONArray("items") ;

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject userJson = jsonArray.getJSONObject(i);
                    UserInfo userInfo = new UserInfo();
                    userInfo.setId(userJson.getInt("id"));
                    userInfo.setName(userJson.getString("login"));
                    userInfo.setAvatar_url(userJson.getString("avatar_url"));
                    users.add(userInfo);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return users;
    }
}

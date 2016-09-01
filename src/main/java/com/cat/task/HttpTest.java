package com.cat.task;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HttpTest {

    /**
     * 生成签名
     * <p>
     * 排序前参数
     * array (size=5)
     * 'appId' => string 'XIAMEN999E589B47333819C48307B9B7D4D4F5' (length=38)
     * 'timestamp' => string '1472027689' (length=10)
     * 'nonceStr' => string 'tNN8fTIy7AuYtOf1VThXb5URHncqR2HIYSCk' (length=36)
     * 'lockId' => string 'DED53A4B-7361-7D0E-617E-6B26A547CED4' (length=36)
     * 'appSecret' => string 'D4C2D620743677AF96BCE8CE7F9976BEDFDC0654B71B66CA9DA9AD410C8B6A99' (length=64)
     * 排序后参数
     * array (size=5)
     * 'appId' => string 'XIAMEN999E589B47333819C48307B9B7D4D4F5' (length=38)
     * 'appSecret' => string 'D4C2D620743677AF96BCE8CE7F9976BEDFDC0654B71B66CA9DA9AD410C8B6A99' (length=64)
     * 'lockId' => string 'DED53A4B-7361-7D0E-617E-6B26A547CED4' (length=36)
     * 'nonceStr' => string 'tNN8fTIy7AuYtOf1VThXb5URHncqR2HIYSCk' (length=36)
     * 'timestamp' => string '1472027689' (length=10)
     *
     * @param array $params
     * @return string
     */
    public static void main(String[] args) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("appId", "XIAMEN999E589B47333819C48307B9B7D4D4F5");
        map.put("timestamp", 1472027689 + "");
        map.put("nonceStr", "tNN8fTIy7AuYtOf1VThXb5URHncqR2HIYSCk");
        map.put("lockId", "DED53A4B-7361-7D0E-617E-6B26A547CED4");
        map.put("appSecret", "D4C2D620743677AF96BCE8CE7F9976BEDFDC0654B71B66CA9DA9AD410C8B6A99");
        map.put("sign", "4b0f0b44c65ff11735523e77c00bd390f93b91f8");

        /*Map<String, Object> prop = new HashMap<>();
        prop.put("name", "cjj");
        prop.put("age", 33);
        prop.put("address", "beijing");
        map.put("person", prop);*/

        JSONObject json = new JSONObject(map);
        String params = json.toString();
        /*System.out.println(params);*/

       /* String input = "{\"address\":\"beijing\",\"name\":\"cjj\",\"age\":33}";
        System.out.println(new JSONObject(input).toString());*/

        String s = ConnectionUtil.post("http://localhost:8080/door/vanke/open", null, params);
        System.out.println(s);
    }
}

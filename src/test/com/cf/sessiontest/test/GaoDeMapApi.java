package com.cf.sessiontest.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class GaoDeMapApi {
    private static String API_URL = "http://restapi.amap.com/v3/geocode/geo";
    public static void main(String[] args) {
        String[] citys = {"中国","资阳市", "成都市", "11市辖区"};
        Map map = new HashMap();

        for (String city : citys){
            map.put("address",city);
            map.put("key","389880a06e3f893ea46036f030c94700");
            String re = sendPost(API_URL,map);
            System.out.println(re);
            JSONObject jsonObject = JSONObject.parseObject(re);
            String location = null;
            try {
                JSONArray geocodes = (JSONArray) jsonObject.get("geocodes");
                if(geocodes.size()>0){

                }
                location = (String)((JSONObject)geocodes.get(0)).get("location");
            } catch (Exception e) {
                continue;
            }
            if(location!=null){
                String []l = location.split(",");
                System.out.println(location);

//                System.out.println(city+":"+location.get("lng")+","+location.get("lat"));
            }
        }
    }

    private static String sendPost(String url, Map<String, String> params) {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            StringBuilder param = new StringBuilder();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (param.length() > 0) {
                    param.append("&");
                }
                param.append(entry.getKey());
                param.append("=");
                param.append(entry.getValue());
                // System.out.println(entry.getKey()+":"+entry.getValue());
            }

            URL realUrl = new URL(url+"?"+param.toString());
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // POST方法
            conn.setRequestMethod("GET");
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "application/JSON");
            conn.connect();

            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result.toString();
    }
}

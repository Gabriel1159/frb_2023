package com.frb.utils;

import java.util.ArrayList;
import java.util.HashMap;

public class StringUtils {
    public static String generateArgs(String start, String interval, String acc_max, String coefficient, String string, String total)
    {
        String res = "";
        res += start;
        res += " ";
        res += interval;
        res += " ";
        res += acc_max;
        res += " ";
        res += coefficient;
        res += " ";
        res += string;
        res += " ";
        res += total;
        res += " ";
        return res;
    }
    public static HashMap<String, String> toMap(String args)
    {
        HashMap<String, String> res = new HashMap<>();
        String[] infos = args.split(" ");
        res.put("start", infos[0]);
        res.put("interval", infos[1]);
        res.put("acc_max", infos[2]);
        res.put("coefficient", infos[3]);
        res.put("string", infos[4]);
        res.put("total", infos[5]);
        return res;
    }
}

package com.frb.controller;

import com.alibaba.fastjson.JSON;
import com.frb.DAO.TrainSample;
import com.frb.service.SampleService;
import com.frb.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class SampleController {
    @Autowired
    private SampleService sampleService;

    @RequestMapping(value="/run", method = RequestMethod.POST)
    @ResponseBody
    public void runPythonScript(HttpServletResponse response, @RequestBody Map<String, String> map) throws IOException, InterruptedException {
        String program_path = "./src/main/python/demo1.py";
        String model_path = map.get("model_path");
        String start = map.get("start");
        String interval = map.get("interval");
        String acc_max = map.get("acc_max");
        String coef = map.get("coefficient");
        String string = map.get("string");
        String total = map.get("total");
        ArrayList<String> commands = new ArrayList<>();
        commands.add("python");
        commands.add("-u");
        commands.add(program_path);
        commands.add("--model_path");
        commands.add(model_path);
        if(start!=null)
        {
            commands.add("--start");
            commands.add(start);
        }
        if(interval!=null)
        {
            commands.add("--interval");
            commands.add(interval);
        }
        if(acc_max!=null)
        {
            commands.add("--acc_max");
            commands.add(acc_max);
        }
        if(coef!=null)
        {
            commands.add("--coefficient");
            commands.add(coef);
        }
        if(string!=null)
        {
            commands.add("--string");
            commands.add(string);
        }
        if(total!=null)
        {
            commands.add("--total");
            commands.add(total);
        }
        System.out.println(commands.toString());
        ProcessBuilder processBuilder = new ProcessBuilder(commands);
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            response.getWriter().println(line);
            response.flushBuffer();
        }

        int exitCode = process.waitFor();
        System.out.println("Exit code is "+exitCode);

        String taskName = map.get("taskName");
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String subTime = time.format(System.currentTimeMillis());
        int status = 0;
        String args = StringUtils.generateArgs(start, interval, acc_max, coef, string, total);
        TrainSample t = new TrainSample(taskName, args, subTime, model_path, status, "");
        sampleService.addSample(t);
    }

    @RequestMapping(value="/getGPUState", method = RequestMethod.GET)
    @ResponseBody
    public Object getGPUState() throws IOException, SAXException, ParserConfigurationException {
        ProcessBuilder processBuilder = new ProcessBuilder("nvidia-smi", "-q", "-x");
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        String content = "";
        while ((line = reader.readLine()) != null) {
            content += line;
        }

        return content;
    }

    @RequestMapping(value="/test", method = RequestMethod.GET)
    @ResponseBody
    public void test(HttpServletResponse response) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder("pwd");
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            response.getWriter().println(line);
            response.flushBuffer();
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Python script execution failed with exit code: " + exitCode);
        }
    }

    @RequestMapping(value = "/getInfo", method = RequestMethod.POST)
    @ResponseBody
    public ArrayList<TrainSample> getInfo()
    {
        return sampleService.getInfo();
    }

    @RequestMapping(value = "/getSingleInfo", method = RequestMethod.POST)
    @ResponseBody
    public Object getSingleInfo(HttpServletResponse response, @RequestBody Map<String, String> map)
    {
        return sampleService.getSingleInfo(map.get("id"));
    }

    @RequestMapping(value = "/addSample", method = RequestMethod.POST)
    @ResponseBody
    public void addSample(TrainSample t)
    {
        sampleService.addSample(t);
    }

    @RequestMapping(value = "/stopTraining", method = RequestMethod.POST)
    @ResponseBody
    public void stopTraining(HttpServletResponse response, @RequestBody Map<String, String> map) throws IOException {
        String id = map.get("id");
        char c = (char)3;
        ProcessBuilder processBuilder = new ProcessBuilder(String.valueOf(c));
        processBuilder.redirectErrorStream(true);
        processBuilder.start();
    }

}
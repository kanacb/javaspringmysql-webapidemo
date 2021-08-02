package com.kcom.springstart1.webdemo.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
public class checkerController {

    private final String SITE_UP = "Site is UP";
    private final String SITE_DOWN = "Site is DOWN";
    private final String SITE_INVALID = "Site is Invalid";
    private final String SITE_ERROR = "Site is Error";


    @GetMapping("/index")
    public String index(){
        return "Hello Welcome to HL Takaful";
    }

    // GET /checkUrl?url=https://www.google.com
    @GetMapping("/checkUrl")
    public String checkUrl(@RequestParam String url){

        String returnMessage = "";

        try {
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int RequestCode = conn.getResponseCode() / 100;

            if(RequestCode != 2){
                returnMessage = SITE_ERROR + " " + RequestCode;
            }
            else {
                returnMessage = SITE_UP;
            }

        } catch (MalformedURLException e) {
            returnMessage = SITE_INVALID;
        } catch (IOException e) {
            returnMessage = SITE_DOWN;
        }

        return returnMessage;
    }
    
}

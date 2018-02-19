package com.tuenti.scandel.controller;

import com.tuenti.scandel.service.HandBallService;
import com.tuenti.scandel.service.impl.HandBallServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.LinkedList;

@RestController
@RequestMapping(value = "/handball")
public class HandBallController {

    private static final Logger log = LoggerFactory.getLogger(HandBallServiceImpl.class);

    @Autowired
    private HandBallService handBallService;


    @RequestMapping(value = "/match", method = RequestMethod.GET)
    public String processFile() throws FileNotFoundException {

        //TODO pasar ficheros por petición REST, no realizado por falta de tiempo
        return this.readSomeFiles();
    }

    private String readSomeFiles()throws FileNotFoundException  {

        File file = new File("src/handball_sample1.csv");
        InputStream targetStream = new FileInputStream(file);
        handBallService.parseMatch(targetStream);




        file = new File("src/handball_sample2.csv");
        targetStream = new FileInputStream(file);

        return "MVP at this moment:" + handBallService.parseMatch(targetStream);
    }

}

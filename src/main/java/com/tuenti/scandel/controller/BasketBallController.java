package com.tuenti.scandel.controller;

import com.tuenti.scandel.service.BasketBallService;
import com.tuenti.scandel.service.impl.BasketBallServiceImpl;
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

@RestController
@RequestMapping(value = "/basketball")
public class BasketBallController {

    private static final Logger log = LoggerFactory.getLogger(BasketBallServiceImpl.class);

    @Autowired
    private BasketBallService basketBallService;


    @RequestMapping(value = "/match", method = RequestMethod.GET)
    public String processFile() throws FileNotFoundException {

        //TODO pasar ficheros por petición REST, no realizado por falta de tiempo
        return this.readSomeFiles();
    }

    private String readSomeFiles()throws FileNotFoundException  {

        File file = new File("src/basketball_sample1.csv");
        InputStream targetStream = new FileInputStream(file);
        basketBallService.parseMatch(targetStream);

        file = new File("src/basketball_sample2.csv");
        targetStream = new FileInputStream(file);

        return "MVP at this moment:" + basketBallService.parseMatch(targetStream);
    }

}

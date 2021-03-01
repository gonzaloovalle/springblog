package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {
    @RequestMapping(path = "/add/{number1}/and/{number2}", method = RequestMethod.GET)
    @ResponseBody
    public String add(@PathVariable int number1, @PathVariable int number2) {
        return number1 + " plus " + number2 + " is " + (number1 + number2) + "!";
    }

    @RequestMapping(path = "/subtract/{number2}/from/{number1}", method = RequestMethod.GET)
    @ResponseBody
    public String subtract(@PathVariable int number2, @PathVariable int number1) {
        return number2 + " minus " + number1 + " is " + (number1 - number2) + "!";
    }

    @RequestMapping(path = "/multiply/{number1}/and/{number2}", method = RequestMethod.GET)
    @ResponseBody
    public String multiply(@PathVariable int number1, @PathVariable int number2) {
        return number1 + " times " + number2 + " is " + (number1 * number2) + "!";
    }

    @RequestMapping(path = "divide/{number1}/by/{number2}", method = RequestMethod.GET)
    @ResponseBody
    public String divide(@PathVariable int number2, @PathVariable int number1) {
        return number1 + " divided by " + number2 + " is " + (number1 / number2) + "!";
    }

}

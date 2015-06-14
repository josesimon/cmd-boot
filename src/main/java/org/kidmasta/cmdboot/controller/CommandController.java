package org.kidmasta.cmdboot.controller;

import org.kidmasta.cmdboot.service.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CommandController {

    @Autowired
    CommandService commandService;


    @RequestMapping(value = "/run", method = RequestMethod.POST)
    @ResponseBody
    String run(@RequestParam("cmd") String cmd){
        return commandService.executeCommand(cmd);
    }


}
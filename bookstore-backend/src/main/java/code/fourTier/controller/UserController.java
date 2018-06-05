package code.fourTier.controller;

import code.fourTier.services.appService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
public class UserController {


    @Autowired
    private appService appservice;

    @RequestMapping(value = "/AddUser",method = RequestMethod.GET)
    public
    @ResponseBody
    String AddUser(@RequestParam String name, @RequestParam String password, @RequestParam String phone, @RequestParam String email){
        return appservice.AddUser(name, password, phone, email);
    }

    @RequestMapping(value = "/CheckUser",method = RequestMethod.GET)
    public
    @ResponseBody
    String CheckUser(@RequestParam String username, @RequestParam String password){
        return appservice.CheckUser(username, password);
    }

    @RequestMapping(value="/ModifyUser", method = RequestMethod.GET)
    public
    @ResponseBody
    String ModifyUser(@RequestParam int id, @RequestParam String name, @RequestParam String password, @RequestParam String phone, @RequestParam String email){
        return appservice.ModifyUser(id, name, password, phone, email);
    }

}
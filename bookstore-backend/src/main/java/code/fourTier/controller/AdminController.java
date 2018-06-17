package code.fourTier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import code.fourTier.services.appService;

import javax.servlet.http.HttpServletResponse;

@CrossOrigin
@RestController
public class AdminController {
    @Autowired
    appService appservice;

    @RequestMapping(value = "/CheckAdmin",method = RequestMethod.GET)
    public
    @ResponseBody
    String AdminLogin(@RequestParam String adminname, HttpServletResponse httpServletResponse) {
        return this.appservice.AdminLogin(adminname);
    }


}

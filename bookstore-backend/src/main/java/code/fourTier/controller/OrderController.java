package code.fourTier.controller;

import code.fourTier.services.appService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class OrderController {

    @Autowired
    appService appservice;

    @RequestMapping(value = "/AddOrder",method = RequestMethod.GET)
    public
    @ResponseBody
    String AddOrder(@RequestParam int userId, @RequestParam Long bookId, @RequestParam int amount, @RequestParam double price){
        return appservice.AddOrder(userId, bookId, amount, price);
    }

    @RequestMapping(value = "/PrevOrder",method = RequestMethod.GET)
    public
    @ResponseBody
    String PrevOrder(@RequestParam int userId){
        return appservice.PrevOrder(userId);
    }
}

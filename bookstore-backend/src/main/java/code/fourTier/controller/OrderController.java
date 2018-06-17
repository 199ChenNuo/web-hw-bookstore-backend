package code.fourTier.controller;

import code.fourTier.services.appService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

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

    @RequestMapping(value = "/PrevOrderByDate",method = RequestMethod.GET)
    public
    @ResponseBody
    String PrevOrderByDate(@RequestParam int userId, @RequestParam Date beginDate, @RequestParam Date endDate){
        return appservice.PrevOrderByDate(userId, beginDate, endDate);
    }

    @RequestMapping(value = "/Sales",method = RequestMethod.GET)
    public
    @ResponseBody
    String GetAllOrders(){
        return appservice.GetAllOrders();
    }

    @RequestMapping(value = "/SalesByDate",method = RequestMethod.GET)
    public
    @ResponseBody
    String GetAllOrdersByDate(@RequestParam Date beginDate, @RequestParam Date endDate){
        return appservice.GetAllOrdersByDate(beginDate, endDate);
    }
}

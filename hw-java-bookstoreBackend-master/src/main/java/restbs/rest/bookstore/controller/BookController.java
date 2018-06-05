package restbs.rest.bookstore.controller;

import javax.json.JsonObject;

import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.*;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import restbs.rest.bookstore.services.appService;

@CrossOrigin
@RestController
public class BookController {

    @Autowired
    private appService appservice;

    @RequestMapping(value="/BookManager", method = RequestMethod.GET)
    public
    @ResponseBody
    String GetAllBooks(){return this.appservice.GetAllBooks();}

    @RequestMapping(value = "/AddBook",method = RequestMethod.GET)
    public
    @ResponseBody
    void AddBook(@RequestParam String name, @RequestParam String author, @RequestParam String price, @RequestParam String  year, @RequestParam String storage){
        this.appservice.AddBook(name, author, price, year, storage);
    }

    @RequestMapping(value = "/ModifyBooks",method = RequestMethod.GET)
    public
    @ResponseBody
    void ModifyBook(@RequestParam Long ID, @RequestParam String name, @RequestParam String author, @RequestParam String price, @RequestParam String  year, @RequestParam String storage){
        this.appservice.ModifyBook(ID, name, author, price, year, storage);
    }

}

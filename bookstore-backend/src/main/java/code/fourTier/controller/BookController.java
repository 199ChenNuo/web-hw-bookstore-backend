package code.fourTier.controller;

import org.springframework.web.bind.annotation.*;;
import org.springframework.beans.factory.annotation.Autowired;
import code.fourTier.services.appService;

@CrossOrigin
@RestController
public class BookController {

    @Autowired
    private appService appservice;

    @RequestMapping(value="/BookManager", method = RequestMethod.GET)
    public
    String GetAllBooks(){return this.appservice.GetAllBooks();}

    @RequestMapping(value = "/AddBook",method = RequestMethod.GET)
    public
    void AddBook(@RequestParam String name, @RequestParam String author, @RequestParam String price, @RequestParam String  year, @RequestParam String storage){
        this.appservice.AddBook(name, author, price, year, storage);
    }

    @RequestMapping(value = "/ModifyBooks",method = RequestMethod.GET)
    public
    void ModifyBook(@RequestParam Long ID, @RequestParam String name, @RequestParam String author, @RequestParam String price, @RequestParam String  year, @RequestParam String storage){
        this.appservice.ModifyBook(ID, name, author, price, year, storage);
    }

    @RequestMapping(value = "/DeleteBook",method = RequestMethod.GET)
    public
    void AddBook(@RequestParam Long ID){
        this.appservice.DeleteBook(ID);
    }

}
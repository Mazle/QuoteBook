package hello;

import hello.dao.QuoteCRUDRepository;
import hello.entity.Quote;
import hello.services.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class GreetingController {

    @Autowired
    private QuoteCRUDRepository repository;

    @GetMapping("/")
    public String quotebook(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        Iterable<Quote> quotes;
        quotes = repository.findAll();
        model.addAttribute("quotes", quotes);
        return "quotebook";
    }

}

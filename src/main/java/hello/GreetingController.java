package hello;

import hello.dao.QuoteCRUDRepository;
import hello.entity.Quote;
import hello.services.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class GreetingController {

    @Autowired
    private QuoteCRUDRepository quoteRepository;

    @GetMapping("/")
    public String quotebook(
            @RequestParam(name="page", required=false, defaultValue="1") Integer pageNumb,
            @RequestParam(name="sortBy",required = false, defaultValue = "date") String sortBy,
            @RequestParam(name="order", required=false, defaultValue="desc") String order,
            Model model,
            Pageable pageable
    ) {
        Sort sort;
        if (order.equals("desk")) sort = new Sort(Sort.Direction.DESC, sortBy);
            else sort = new Sort(Sort.Direction.ASC, sortBy);
        Integer pageNumber = (pageNumb > 0) ? pageNumb - 1 : 0;
        PageRequest pageRequest = PageRequest.of(pageNumber,5,sort);

        Page<Quote> page;
        page = quoteRepository.findAll(pageable);
        model.addAttribute("quotes", page);
        return "quotebook";
    }

}

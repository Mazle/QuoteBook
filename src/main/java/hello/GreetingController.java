package hello;

import hello.dao.QuoteCRUDRepository;
import hello.model.DTO.QuoteDTO;
import hello.model.entity.Author;
import hello.model.entity.Quote;
import hello.services.impl.AuthorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

//TODO: #REWRITE: сделать динамическую пагинацию
//todo:#ADD: добавить валидацию данных, введенных в форму.
@Controller
public class GreetingController {

    @Autowired
    private QuoteCRUDRepository quoteRepository;
    private AuthorServiceImpl authorService;

    @GetMapping("/")
    public String quotebook(
            @RequestParam(name="page", required=false, defaultValue="1") Integer pageNumb,
            @RequestParam(name="sortBy",required = false, defaultValue = "date") String sortBy,
            @RequestParam(name="order", required=false, defaultValue="desc") String order,
            Model model
    ) {
        Sort sort;
        if (order.equals("desc")) sort = new Sort(Sort.Direction.DESC, sortBy);
            else sort = new Sort(Sort.Direction.ASC, sortBy);
        Integer pageNumber = (pageNumb > 0) ? pageNumb - 1 : 0;
        PageRequest pageRequest = PageRequest.of(pageNumber,5,sort);
        Page<Quote> page;
        page = quoteRepository.findAll(pageRequest);
        model.addAttribute("quotes", page);
        return "quotebook";
    }

    @GetMapping("/newQuote")
    public String showAuthors(Model model
                                ,@RequestParam(name = "contentAlert", required=false, defaultValue = "false") Boolean contentAlert
                                ,@RequestParam(name="nickNameAlert", required=false,defaultValue = "false") Boolean nickNameAlert){
        model.addAttribute("allAuthors",authorService.findAll());
        Quote quote = new Quote();
        quote.setAuthor(new Author());
        model.addAttribute("quote", new QuoteDTO());
        if (contentAlert==true) model.addAttribute("contentAlert",contentAlert);
        if (nickNameAlert==true) model.addAttribute("nickNameAlert",nickNameAlert);
        //model.addAttribute("author", new Author());
        return "newQuote";
    }

    @PostMapping("/newQuote")
    public String addQuote (
            @ModelAttribute QuoteDTO quote
           // ,@ModelAttribute Author author
    ) {
        Quote quoteEntity = new Quote();
        if (formHasQuoteInvalidValues(quote)) return "redirect:/newQuote"+prepareRedirectParams(quote);
        quoteEntity.setAuthor(prepareAuthorOfQuoteToPersist(quote));
        quoteEntity.setDate(LocalDate.now());
        quoteEntity.setContent(quote.getContent());
        quoteRepository.save(quoteEntity);
        return "redirect:/";
    }

    @Autowired
    public void setAuthorService(AuthorServiceImpl authorService) {
        this.authorService = authorService;
    }

    private Author prepareAuthorOfQuoteToPersist(QuoteDTO quote){
        Author author = authorService.findByNickName(quote.getAuthorNickName());
        if (author==null) {
            author = new Author();
            author.setNickName(quote.getAuthorNickName());
        }
        return author;
    }
/*
Вместо своего велосипеда, нужно было прибегнуть к валидации, доступной из коробки для спринга.
Но код уже написан и работает. Хоть и избыточен. Если останется время - исправлю на кофетку.
 */
    private boolean formHasQuoteInvalidValues(QuoteDTO quoteDTO){
        return quoteDTO.getContent()==null||quoteDTO.getContent().equals("")
                ||quoteDTO.getAuthorNickName()==null||quoteDTO.getAuthorNickName().equals("");
    }
    private String prepareRedirectParams(QuoteDTO quoteDTO) {
        //todo:#TO_FINISH: сделать сохранение корректно введенных полей.
        StringBuilder redirectParams = new StringBuilder();
        redirectParams.append("?");
        if (quoteDTO.getContent()==null||quoteDTO.getContent().equals("")){
            redirectParams.append("contentAlert=true");
        }
        if (quoteDTO.getAuthorNickName()==null||quoteDTO.getAuthorNickName().equals("")){
            if (redirectParams.toString().endsWith("true")) redirectParams.append("&");
            redirectParams.append("nickNameAlert=true");
        }
        return redirectParams.toString();
    }
}

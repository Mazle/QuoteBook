package hello.controllers;

import hello.model.DTO.QuoteDTO;
import hello.model.entity.Author;
import hello.model.entity.Quote;
import hello.services.QuoteService;
import hello.services.impl.AuthorServiceImpl;
import hello.services.impl.BashOrgParsingService;
import hello.services.impl.ElasticQuoteServiceImpl;
import hello.services.impl.QuoteServiceImpl;
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

//TODO: подправить дизайн кнопок и полей

@Controller
public class GreetingController {

    @Autowired
    private QuoteServiceImpl quotePostgresService;
    private AuthorServiceImpl authorService;
    private BashOrgParsingService snatchService;
    private QuoteService elasticQuoteService;

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
        PageRequest pageRequest = PageRequest.of(pageNumber,50,sort);
        //TODO сделать quoteDto
        Page<QuoteDTO> page;
        page = elasticQuoteService.getPage(pageRequest);
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
        model.addAttribute("quoteDto", new QuoteDTO());
        if (contentAlert==true) model.addAttribute("contentAlert",contentAlert);
        if (nickNameAlert==true) model.addAttribute("nickNameAlert",nickNameAlert);
        //model.addAttribute("author", new Author());
        return "newQuote";
    }

    @PostMapping("/newQuote")
    public String addQuote (
            @ModelAttribute QuoteDTO quoteDto
           // ,@ModelAttribute Author author
    ) {
        Quote quoteEntity = new Quote();
        if (formHasQuoteInvalidValues(quoteDto)) return "redirect:/newQuote"+prepareRedirectParams(quoteDto);
        quoteEntity.setAuthor(prepareAuthorOfQuoteToPersist(quoteDto));
        quoteEntity.setContent(quoteDto.getContent());
        //add quote to Postgres and then to elastic
        elasticQuoteService.addQuote(new QuoteDTO(quotePostgresService.addQuote(quoteEntity)));
        return "redirect:/";
    }

    @GetMapping("/snatchingQuotes")
    public String snatchQuotes(){
        snatchService.snatchFiftyQuotes();
        return "redirect:/";
    }
    @GetMapping("/clearAll")
    public String clearAll() {
        quotePostgresService.deleteAll();
        elasticQuoteService.deleteAll();
        return "redirect:/";
    }
    @Autowired
    public void setAuthorService(AuthorServiceImpl authorService) {
        this.authorService = authorService;
    }

    @Autowired
    public void setSnatchService(BashOrgParsingService snatchService) {
        this.snatchService = snatchService;
    }

    @Autowired
    public void setElasticQuoteService(ElasticQuoteServiceImpl elasticQuoteService) {
        this.elasticQuoteService = elasticQuoteService;
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

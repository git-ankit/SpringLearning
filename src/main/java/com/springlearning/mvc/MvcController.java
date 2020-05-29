package com.springlearning.mvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MvcController {
    @Autowired
    PublicationRepository publicationRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    ContributorRepository contributorRepository;

    @Autowired
    ChapterRepository chapterRepository;

    @Autowired
    PaymentRepository paymentRepository;

    @RequestMapping("/")
    public String wolfPubDB(){
        return "WolfPubDB";
    }

    @RequestMapping("/Admin")
    public String admin(){
        return "Admin";
    }

    @RequestMapping("/Contributors")
    public String contributor(){
        return "Contributors";
    }

    @RequestMapping("/Admin/EditingAndPublishing")
    public String editingAndPublishing(Model model){
        model.addAttribute("publications", publicationRepository.findAll());
        return "EditingAndPublishing";
    }

    @RequestMapping("/Admin/BookOrIssue")
    public String bookOrIssue(){
        return "BookOrIssue";
    }

    @RequestMapping("/Admin/Reports")
    public String reports(){
        return "Reports";
    }

    @RequestMapping(value = "/Admin/EditingAndPublishing/NewPublication", method = RequestMethod.GET)
    public String newPublication(){
        return "NewPublication";
    }

    @RequestMapping(value = "/Admin/EditingAndPublishing/Publication/{id}")
    public String showPublication(@PathVariable long id, Model model){

        model.addAttribute("publication", publicationRepository.findById(id).orElse(new Publication("this", "is", "wrong", "entry")));
        return "Publication";
    }

    @RequestMapping(value = "/Admin/EditingAndPublishing/NewPublication", method = RequestMethod.POST)
    public String newPublication(@RequestParam String title,@RequestParam String typicalTopics,@RequestParam String type,@RequestParam String periodicity){
        Publication pub = new Publication(title, typicalTopics, type, periodicity);
        publicationRepository.save(pub);
        return "redirect:/Admin/EditingAndPublishing/Publication/"+pub.getId();

    }

}

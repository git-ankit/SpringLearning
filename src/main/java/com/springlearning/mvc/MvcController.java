package com.springlearning.mvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;


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

    @RequestMapping(value = "/Admin/EditingAndPublishing/NewPublication/", method = RequestMethod.GET)
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

    @RequestMapping(value = "/Admin/EditingAndPublishing/UpdatePublication/{id}", method = RequestMethod.GET)
    public String updatePublication(@PathVariable long id, Model model){
        Optional<Publication> publication = publicationRepository.findById(id);
        if (publication.isPresent() == false){
            return "Error";
        } else{
            model.addAttribute("publication", publication.get());
            return "UpdatePublication";
        }
    }

    @RequestMapping(value = "/Admin/EditingAndPublishing/UpdatePublication", method = RequestMethod.POST)
    public String updatePublication(@RequestParam long id, @RequestParam String title,@RequestParam String typicalTopics,@RequestParam String type,@RequestParam String periodicity){
        Optional<Publication> publication = publicationRepository.findById(id);
        if (publication.isPresent() == false){
            return "Error";
        } else{
            publication.get().setTitle(title);
            publication.get().setPeriodicity(periodicity);
            publication.get().setType(type);
            publication.get().setTypicalTopics(typicalTopics);
            publicationRepository.save(publication.get());
            return "redirect:/Admin/EditingAndPublishing/Publication/"+publication.get().getId();
        }
    }

    @RequestMapping(value = "/Admin/EditingAndPublishing/EditorsOfPublication/Editors")
    public String showEditors(Model model){
        model.addAttribute("editors", contributorRepository.findAll());
        return "Editors";
    }
}

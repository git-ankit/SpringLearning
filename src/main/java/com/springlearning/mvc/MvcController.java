package com.springlearning.mvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
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
        Optional<Publication> pubInfo= publicationRepository.findById(id);
        if (pubInfo.isPresent()){
            model.addAttribute("publication", pubInfo.get());
            List<Contributor> restOfTheEditors= (List<Contributor>) contributorRepository.findAll();
            restOfTheEditors.removeAll(pubInfo.get().getContributors());
            model.addAttribute(model.addAttribute("restOfTheEditors", restOfTheEditors));
        } else {
            model.addAttribute("publication", new Publication("this", "is", "wrong", "entry"));
            model.addAttribute("restOfTheEditors", contributorRepository.findAll()); //must be passed an empty list
        }
        return "Publication";
    }

    @RequestMapping(value = "/Admin/EditingAndPublishing/NewPublication", method = RequestMethod.POST)
    public String newPublication(@RequestParam String title,@RequestParam String typicalTopics,@RequestParam String type,@RequestParam String periodicity){
        title = title.strip();
        if (title == "") {
            return "Error";
        }
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
            title = title.strip();
            if (title == "") {
                return "Error";
            }
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

    @RequestMapping(value = "/Admin/EditingAndPublishing/Publication/DeletingEditorsFromPublication/", method = RequestMethod.POST)
    public String deletingEditorsFromPublication(@RequestParam long publicationId, @RequestParam long editorId){

        Optional<Contributor> updatedEditor = contributorRepository.findById(editorId);
        if (updatedEditor.get().deleteAPublication(publicationRepository.findById(publicationId).get())){
            contributorRepository.save(updatedEditor.get());
            return "redirect:/Admin/EditingAndPublishing/Publication/"+publicationId;
        } else {
            return "Error";
        }
    }

    @RequestMapping(value = "/Admin/EditingAndPublishing/Publication/AddingEditorsForPublication/", method = RequestMethod.POST)
    public String addingEditorsForPublication(@RequestParam long publicationId, @RequestParam long editorId){
        Optional<Contributor> updatedEditor = contributorRepository.findById(editorId);
        if (updatedEditor.get().addAPublication(publicationRepository.findById(publicationId).get())){
            contributorRepository.save(updatedEditor.get());
            return "redirect:/Admin/EditingAndPublishing/Publication/"+publicationId;
        } else {
            return "Error";
        }
    }

    @RequestMapping("/Admin/BookOrIssue/Books")
    public String books(Model model){
        model.addAttribute("books", bookRepository.findAll());
        return "Books";
    }

    @RequestMapping(value = "/Admin/BookOrIssue/NewBook/", method = RequestMethod.GET)
    public String newBook(Model model) {
        model.addAttribute("publications", publicationRepository.findAll());
        return "NewBook";
    }

    @RequestMapping(value = "/Admin/BookOrIssue/NewBook/", method = RequestMethod.POST)
    public String newBook(@RequestParam String isbn, @RequestParam String dateOfCreation, @RequestParam String dateOfPublication, @RequestParam int editionNo, @RequestParam long publicationId){
        Optional<Publication> pub = publicationRepository.findById(publicationId);
        if (pub.isPresent()) {
            Calendar doC = Calendar.getInstance();
            doC.set(Integer.valueOf(dateOfCreation.substring(0,4)), Integer.valueOf(dateOfCreation.substring(5,7)) + 1, Integer.valueOf(dateOfCreation.substring(8,10)));
            Calendar doP = Calendar.getInstance();
            doP.set(Integer.valueOf(dateOfPublication.substring(0,4)), Integer.valueOf(dateOfPublication.substring(5,7)) + 1, Integer.valueOf(dateOfPublication.substring(8,10)));
            Book newBook = new Book(isbn, doC, doP, editionNo, pub.get());
            bookRepository.save(newBook);
            return "redirect:/Admin/BookOrIssue/Books"; //to be changed to view the new created book
        } else {
            return "Error";
        }
    }

    @RequestMapping(value = "/Admin/BookOrIssue/UpdateBook/{bookId}", method = RequestMethod.GET)
    public String UpdateBook(@PathVariable long bookId, Model model){
        Optional<Book> bookToUpdate= bookRepository.findById(bookId);
        if (bookToUpdate.isPresent()){
            String isbn = bookToUpdate.get().getIsbn();
            Calendar doC = bookToUpdate.get().getDateOfCreation();
            Calendar doP = bookToUpdate.get().getDateOfPublication();
            int editionNo = bookToUpdate.get().getEditionNo();
            Publication publication = bookToUpdate.get().getPublication();
            List<Contributor> authors = bookToUpdate.get().getAuthors();
            List<Contributor> restOfTheAuthors= (List<Contributor>) contributorRepository.findAll();
            restOfTheAuthors.removeAll(bookToUpdate.get().getAuthors());
            model.addAttribute("id", bookToUpdate.get().getId());
            model.addAttribute("isbn",isbn);
            model.addAttribute("doC", doC);
            model.addAttribute("doP", doP);
            model.addAttribute("editionNo", editionNo);
            model.addAttribute("publication", publication);
            model.addAttribute("authors", authors);
            model.addAttribute(model.addAttribute("restOfTheAuthors", restOfTheAuthors));
            model.addAttribute("publications", publicationRepository.findAll());
            return "UpdateBook";

        }else {
            return "Error";
        }
    }

    @RequestMapping(value = "/Admin/BookOrIssue/UpdateBook/", method = RequestMethod.POST)
    public String UpdateBook(@RequestParam long bookId, @RequestParam String isbn, @RequestParam int editionNo, @RequestParam long publicationId, @RequestParam long authorId){
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()){
            if (authorId != 0){
                book.get().addAuthor(contributorRepository.findById(authorId).get());
            }

            book.get().setIsbn(isbn);
            book.get().setEditionNo(editionNo);
            book.get().setPublication(publicationRepository.findById(publicationId).get());
            bookRepository.save(book.get());
            return "redirect:/Admin/BookOrIssue/UpdateBook/"+bookId;
        } else {
            return "Error";
        }
    }
}

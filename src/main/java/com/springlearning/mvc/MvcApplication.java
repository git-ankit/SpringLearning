package com.springlearning.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.channels.Pipe;
import java.util.*;

@SpringBootApplication
public class MvcApplication implements CommandLineRunner {
    @Autowired
    PublicationRepository publicationRepository;

    @Autowired
    ContributorRepository contributorRepository;

    @Autowired
    BookRepository bookRepository;


    public static void main(String[] args) {

        SpringApplication.run(MvcApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
//        Skill javascript = new Skill("javascript", "Javascript language skill");
//        Skill ruby = new Skill("ruby", "Ruby language skill");
//        Skill emberjs = new Skill("emberjs", "Emberjs framework");
//        Skill angularjs = new Skill("angularjs", "Angularjs framework");
//        skillRep.save(javascript);
//        skillRep.save(ruby);
//        skillRep.save(emberjs);
//        skillRep.save(angularjs);
//
//        List<Developer> developers = new LinkedList<Developer>();
//        developers.add(new Developer("John", "Smith", "john.smith@example.com",
//                Arrays.asList(new Skill[] { javascript, ruby })));
//        developers.add(new Developer("Mark", "Johnson", "mjohnson@example.com",
//                Arrays.asList(new Skill[] { emberjs, ruby })));
//        developers.add(new Developer("Michael", "Williams", "michael.williams@example.com",
//                Arrays.asList(new Skill[] { angularjs, ruby })));
//        developers.add(new Developer("Fred", "Miller", "f.miller@example.com",
//                Arrays.asList(new Skill[] { emberjs, angularjs, javascript })));
//        developers.add(new Developer("Bob", "Brown", "brown@example.com",
//                Arrays.asList(new Skill[] { emberjs })));
//        developerRep.saveAll(developers);
        List<Publication> publications= new LinkedList<>();
        publications.add(new Publication("Harry Potter", "Fiction", "Book", "Once"));
        publications.add(new Publication("Vogue", "Fashion", "Issue", "Monthly"));
        publicationRepository.saveAll(publications);

        List<Contributor> contributors = new LinkedList<>();
        contributors.add(new Contributor("JK Rowling", "Author", "Guest", 950000, "once" ));
        contributors.add(new Contributor("Amit Ghetiya", "Photographer", "Permanent", 12000, "monthly" ));
        contributors.add(new Contributor("Arjun Madhusudan", "Intern", "Temporary", 39, "hourly" ));

        contributors.get(0).setWorksFor(publications.subList(0,1));
        contributors.get(1).setWorksFor(publications.subList(1,2));

        contributorRepository.saveAll(contributors);

        List<Book> books = new LinkedList<>();

        Calendar dateOfCreation1 = Calendar.getInstance();
        Calendar dateOfPublication1 = Calendar.getInstance();
        Calendar dateOfCreation2 = Calendar.getInstance();
        Calendar dateOfPublication2 = Calendar.getInstance();

        dateOfCreation1.set(2002,Calendar.DECEMBER,01);
        dateOfPublication1.set(2003, Calendar.FEBRUARY,28);
        books.add(new Book("9780807286005",  dateOfCreation1, dateOfPublication1, 1, publications.get(0), contributors.subList(0,1)));

        dateOfCreation2.set(2003,Calendar.JANUARY, 31);
        dateOfPublication2.set(2004, Calendar.JANUARY, 01);
        books.add(new Book("9780439554930", dateOfCreation2, dateOfPublication2, 2, publications.get(0), contributors.subList(0,1)));

        //Code where i was trying to add issues to books. stupid me
//        dateOfCreation.set(2019,12,31);
//        dateOfPublication.set(2020, 1,1);
//        books.add(new Book(dateOfCreation, dateOfPublication, 145, publications.get(1), contributors.subList(1,3)));
//
//        dateOfCreation.set(2020,1,31);
//        dateOfPublication.set(2020, 2,1);
//        books.add(new Book(dateOfCreation, dateOfPublication, 146, publications.get(1), contributors.subList(1,3)));

        bookRepository.saveAll(books);






    }
}

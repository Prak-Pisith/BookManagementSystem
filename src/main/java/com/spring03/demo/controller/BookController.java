package com.spring03.demo.controller;

import com.spring03.demo.model.Book;
import com.spring03.demo.model.Category;
import com.spring03.demo.services.BookService;
import com.spring03.demo.services.impl.BookServiceImpl;
import com.spring03.demo.services.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
public class BookController {

    private BookServiceImpl bookService;
    private CategoryServiceImpl categoryService;

    @Autowired
    public BookController(BookServiceImpl bookService, CategoryServiceImpl categoryService) {

        this.bookService = bookService;
        this.categoryService=categoryService;
    }

    @GetMapping("/index")
    public String indexPage(Model model){

        List<Book> bookLists = this.bookService.getAllBook();
        System.out.println(bookLists.get(0).getCategory().getId());
        System.out.println(bookLists.get(0).getCategory().getName());
        model.addAttribute("books", bookLists);

        return "Book/index";
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable Integer id, Model model){
        System.out.println("ID : " + id);

        Book book = this.bookService.findOneBook(id);
        model.addAttribute("book",book);
        return "Book/view";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Integer id, Model model){
        List<Category> categories = this.categoryService.getAll();
        System.out.println("Update ID " + id);

        Book book = this.bookService.findOneBook(id);
        model.addAttribute("book",book);
        model.addAttribute("categories", categories);
        return "Book/update";
    }

    @PostMapping("/update/submit")
    public String updateSubmit(@ModelAttribute Book book, MultipartFile file){
        System.out.println(book);

//        if(file == null){
//            return null;
//        }

        File path = new File("/btb");

        if(!path.exists()){
            path.mkdirs();
        }

        String filename = file.getOriginalFilename();
        String extension = filename.substring(filename.lastIndexOf(".")+1);
        System.out.println(extension);
        //Generate new name
        filename = UUID.randomUUID() + "." + extension;

        //moving file to folder btb
        try {
            Files.copy(file.getInputStream(), Paths.get(("/btb"), filename));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(!file.isEmpty()){
            book.setThumbnail("/images-btb/" + filename);
        }

//        book.setThumbnail("/images-btb/" + filename);
        System.out.println(book);
        this.bookService.update(book);
        //call methiod (indexPage) by url
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        System.out.println("Delete at ID : " + id);
        this.bookService.dalete(id);
        return "redirect:/index";
    }

    @GetMapping("/create")
    public String create(Model model){
        List<Category> categories = this.categoryService.getAll();
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categories);
        return "Book/create";
    }
//    @PostMapping("/create/submit")
//    public String create(@ModelAttribute Book book){
//        System.out.println(book);
//        this.bookService.create(book);
//        return "redirect:/index";
//    }

    @PostMapping("/create/submit")
    public String create( Book book, BindingResult bindingResult, MultipartFile file){
        System.out.println(book);
        if(file == null){
            return null;
        }

        File path = new File("/btb");

        if(!path.exists()){
            path.mkdirs();
        }

        String filename = file.getOriginalFilename();
        String extension = filename.substring(filename.lastIndexOf(".")+1);
        System.out.println(extension);
        //Generate new name
        filename = UUID.randomUUID() + "." + extension;

        //moving file to folder btb
        try {
            Files.copy(file.getInputStream(), Paths.get(("/btb"), filename));
        } catch (IOException e) {
            e.printStackTrace();
        }

        book.setThumbnail("/images-btb/" + filename);

        if(!file.isEmpty()){
            book.setThumbnail("/images-btb/" + filename);
        }

        if(bindingResult.hasErrors()){
            return "Book/create";
        }
        System.out.println(book);
        this.bookService.create(book);
        return "redirect:/index";
    }
}

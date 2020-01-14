package cn.smartcity.helloworldapplication.controller;

import cn.smartcity.helloworldapplication.domain.entity.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lipoGiser
 * @date 2019/12/30 13:13
 * @Version 0.1
 */
@RestController
@RequestMapping("/api")
public class BookController {
    private List<Book> books = new ArrayList<>();

    @PostMapping("/book")
    public ResponseEntity<List<Book>> addBook(@RequestBody Book book) {
        books.add(book);
        return ResponseEntity.ok(books);
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity deleteBookById(@PathVariable("id") int id) throws Exception {
        if (id > books.size()) {
            throw new Exception("没有改id");
        }
        books.remove(id);
        return ResponseEntity.ok(books);
    }


    @GetMapping("/book")
    public ResponseEntity getBookByName(@RequestParam("name") String name) {
        List<Book> results = books.stream().filter(book -> book.getName().equals(name)).collect(Collectors.toList());
        return ResponseEntity.ok(results);
    }
}

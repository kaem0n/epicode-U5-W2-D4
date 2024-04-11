package kaem0n.u5w2d4.controllers;

import kaem0n.u5w2d4.entities.Author;
import kaem0n.u5w2d4.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService as;

    @GetMapping
    private Page<Author> getAllAuthors(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size,
                                       @RequestParam(defaultValue = "id") String sortBy) {
        return as.findAll(page, size, sortBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Author saveAuthor(@RequestBody Author body) {
        return as.save(body);
    }

    @GetMapping("/{id}")
    private Author getAuthorById(@PathVariable long id) {
        return as.findById(id);
    }

    @PutMapping("/{id}")
    private Author updateAuthor(@PathVariable long id, @RequestBody Author body) {
        return as.update(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteAuthor(@PathVariable long id) {
        as.delete(id);
    }
}

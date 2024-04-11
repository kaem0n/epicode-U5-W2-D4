package kaem0n.u5w2d4.controllers;

import kaem0n.u5w2d4.entities.BlogPost;
import kaem0n.u5w2d4.exceptions.BadRequestException;
import kaem0n.u5w2d4.payloads.NewBlogPostDTO;
import kaem0n.u5w2d4.services.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blogposts")
public class BlogPostController {
    @Autowired
    private BlogPostService bps;

    @GetMapping
    private Page<BlogPost> getAllPosts(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size,
                                       @RequestParam(defaultValue = "id") String sortBy) {
        return bps.findAll(page, size, sortBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private BlogPost savePost(@RequestBody @Validated NewBlogPostDTO body, BindingResult validation) {
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return bps.save(body);
    }

    @GetMapping("/{id}")
    private BlogPost getPostById(@PathVariable long id) {
        return bps.findById(id);
    }

    @PutMapping("/{id}")
    private BlogPost updatePost(@PathVariable long id, @RequestBody NewBlogPostDTO body, BindingResult validation) {
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return bps.update(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deletePost(@PathVariable long id) {
        bps.delete(id);
    }
}

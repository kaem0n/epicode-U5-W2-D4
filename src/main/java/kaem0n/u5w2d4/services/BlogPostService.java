package kaem0n.u5w2d4.services;

import kaem0n.u5w2d4.entities.BlogPost;
import kaem0n.u5w2d4.exceptions.NotFoundException;
import kaem0n.u5w2d4.payloads.NewBlogPostDTO;
import kaem0n.u5w2d4.repositories.BlogPostDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BlogPostService {
    @Autowired
    private BlogPostDAO bpd;
    @Autowired
    private AuthorService as;

    public Page<BlogPost> findAll(int page, int size, String sortBy) {
        if (size > 50) size = 50;
        Pageable p = PageRequest.of(page, size, Sort.by(sortBy));
        return bpd.findAll(p);
    }

    public BlogPost save(NewBlogPostDTO body) {
        BlogPost newPost = new BlogPost(body.category(), body.title(), body.content(), body.coverUrl(), body.readingTime(), as.findById(body.authorId()));
        return bpd.save(newPost);
    }

    public BlogPost findById(long id) {
        return bpd.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public BlogPost update(long id, NewBlogPostDTO updatedBody) {
        BlogPost found = this.findById(id);
        found.setCategory(updatedBody.category());
        found.setTitle(updatedBody.title());
        found.setContent(updatedBody.content());
        found.setCoverUrl(updatedBody.coverUrl());
        found.setReadingTime(updatedBody.readingTime());
        found.setAuthor(as.findById(updatedBody.authorId()));
        bpd.save(found);
        return found;
    }

    public void delete(long id) {
        BlogPost found = this.findById(id);
        this.bpd.delete(found);
    }
}

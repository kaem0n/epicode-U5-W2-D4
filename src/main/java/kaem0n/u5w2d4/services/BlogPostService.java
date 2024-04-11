package kaem0n.u5w2d4.services;

import kaem0n.u5w2d4.entities.BlogPost;
import kaem0n.u5w2d4.exceptions.NotFoundException;
import kaem0n.u5w2d4.payloads.BlogPostPayload;
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

    public BlogPost save(BlogPostPayload body) {
        BlogPost newPost = new BlogPost(body.getCategory(), body.getTitle(), body.getContent(), body.getCoverUrl(), body.getReadingTime(), as.findById(body.getAuthorId()));
        return bpd.save(newPost);
    }

    public BlogPost findById(long id) {
        return bpd.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public BlogPost update(long id, BlogPostPayload updatedBody) {
        BlogPost found = this.findById(id);
        found.setCategory(updatedBody.getCategory());
        found.setTitle(updatedBody.getTitle());
        found.setContent(updatedBody.getContent());
        found.setCoverUrl(updatedBody.getCoverUrl());
        found.setReadingTime(updatedBody.getReadingTime());
        found.setAuthor(as.findById(updatedBody.getAuthorId()));
        bpd.save(found);
        return found;
    }

    public void delete(long id) {
        BlogPost found = this.findById(id);
        this.bpd.delete(found);
    }
}

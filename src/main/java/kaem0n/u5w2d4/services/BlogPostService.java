package kaem0n.u5w2d4.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import kaem0n.u5w2d4.entities.Author;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class BlogPostService {
    @Autowired
    private BlogPostDAO bpd;
    @Autowired
    private AuthorService as;
    @Autowired
    private Cloudinary c;

    public Page<BlogPost> findAll(int page, int size, String sortBy) {
        if (size > 50) size = 50;
        Pageable p = PageRequest.of(page, size, Sort.by(sortBy));
        return bpd.findAll(p);
    }

    public BlogPost save(NewBlogPostDTO payload) {
        BlogPost newPost = new BlogPost(payload.category(), payload.title(), payload.content(), payload.coverUrl(), payload.readingTime(), as.findById(payload.authorId()));
        return bpd.save(newPost);
    }

    public BlogPost findById(long id) {
        return bpd.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public BlogPost update(long id, NewBlogPostDTO payload) {
        BlogPost found = this.findById(id);
        found.setCategory(payload.category());
        found.setTitle(payload.title());
        found.setContent(payload.content());
        if(found.getCoverUrl().contains("picsum")) found.setCoverUrl(payload.coverUrl());
        found.setReadingTime(payload.readingTime());
        found.setAuthor(as.findById(payload.authorId()));
        bpd.save(found);
        return found;
    }

    public void delete(long id) {
        BlogPost found = this.findById(id);
        this.bpd.delete(found);
    }

    public BlogPost updateCoverUrl(long id, MultipartFile img) throws IOException {
        BlogPost found = this.findById(id);
        String url = (String) c.uploader().upload(img.getBytes(), ObjectUtils.emptyMap()).get("url");
        found.setCoverUrl(url);
        bpd.save(found);
        return found;
    }
}

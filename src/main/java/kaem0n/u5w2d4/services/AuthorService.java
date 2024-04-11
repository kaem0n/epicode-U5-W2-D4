package kaem0n.u5w2d4.services;

import kaem0n.u5w2d4.entities.Author;
import kaem0n.u5w2d4.exceptions.BadRequestException;
import kaem0n.u5w2d4.exceptions.NotFoundException;
import kaem0n.u5w2d4.repositories.AuthorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    private AuthorDAO ad;

    public Page<Author> findAll(int page, int size, String sortBy) {
        if (size > 50) size = 50;
        Pageable p = PageRequest.of(page, size, Sort.by(sortBy));
        return ad.findAll(p);
    }

    public Author save(Author body) {
        if (!ad.existsByEmail(body.getEmail())) {
            body.setAvatar("https://ui-avatars.com/api/?name=" + body.getName() + "+" + body.getSurname());
            ad.save(body);
            return body;
        } else throw new BadRequestException("Email '" + body.getEmail() + "' is already taken.");
    }

    public Author findById(long id) {
        return ad.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Author update(long id, Author updatedBody) {
        Author found = this.findById(id);
        found.setAvatar("https://ui-avatars.com/api/?name=" + updatedBody.getName() + "+" + updatedBody.getSurname());
        found.setName(updatedBody.getName());
        found.setSurname(updatedBody.getSurname());
        found.setEmail(updatedBody.getEmail());
        found.setBirthday(updatedBody.getBirthday());
        this.save(found);
        return found;
    }

    public void delete(long id) {
        Author found = this.findById(id);
        ad.delete(found);
    }
}

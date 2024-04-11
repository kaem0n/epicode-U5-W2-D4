package kaem0n.u5w2d4.services;

import kaem0n.u5w2d4.entities.Author;
import kaem0n.u5w2d4.exceptions.BadRequestException;
import kaem0n.u5w2d4.exceptions.NotFoundException;
import kaem0n.u5w2d4.payloads.NewAuthorDTO;
import kaem0n.u5w2d4.repositories.AuthorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthorService {
    @Autowired
    private AuthorDAO ad;

    public Page<Author> findAll(int page, int size, String sortBy) {
        if (size > 50) size = 50;
        Pageable p = PageRequest.of(page, size, Sort.by(sortBy));
        return ad.findAll(p);
    }

    public Author save(NewAuthorDTO payload) {
        if (!ad.existsByEmail(payload.email())) {
            Author newAuthor = new Author(payload.name(), payload.surname(), payload.email(), payload.birthday());
            newAuthor.setAvatar("https://ui-avatars.com/api/?name=" + payload.name() + "+" + payload.surname());
            ad.save(newAuthor);
            return newAuthor;
        } else throw new BadRequestException("Email '" + payload.email() + "' is already taken.");
    }

    public Author findById(long id) {
        return ad.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Author update(long id, NewAuthorDTO payload) {
        if (!ad.existsByEmail(payload.email())) {
            Author found = this.findById(id);
            found.setAvatar("https://ui-avatars.com/api/?name=" + payload.name() + "+" + payload.surname());
            found.setName(payload.name());
            found.setSurname(payload.surname());
            if (!Objects.equals(found.getEmail(), payload.email())) found.setEmail(payload.email());
            found.setBirthday(payload.birthday());
            ad.save(found);
            return found;
        } else throw new BadRequestException("Email '" + payload.email() + "' is already taken.");
    }

    public void delete(long id) {
        Author found = this.findById(id);
        ad.delete(found);
    }
}

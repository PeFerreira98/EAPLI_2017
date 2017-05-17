/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.rating.Comment;
import eapli.ecafeteria.persistence.CommentRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;

/**
 *
 * @author Pedro Pereira
 */
public class InMemoryCommentRepository extends InMemoryRepositoryWithLongPK<Comment> implements CommentRepository {

    @Override
    public Iterable<Comment> findByText(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

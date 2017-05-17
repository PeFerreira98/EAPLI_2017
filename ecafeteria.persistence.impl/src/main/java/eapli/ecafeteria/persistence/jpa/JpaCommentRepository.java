/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.rating.Comment;
import eapli.ecafeteria.persistence.CommentRepository;
import javax.persistence.Query;

/**
 *
 * @author Pedro Pereira
 */
public class JpaCommentRepository extends CafeteriaJpaRepositoryBase<Comment, Long> implements CommentRepository {

    @Override
    public Iterable<Comment> findByText(String text) {
        Query createQuery = entityManager().createQuery("SELECT c FROM Comment c WHERE c.text=:text");
        createQuery.setParameter("text", text);
        
        return createQuery.getResultList();
    }
    
}

package com.workintech.fswebs18challengemaven.repository;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.exceptions.CardException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class CardRepositoryImpl implements CardRepository {

    private final EntityManager entityManager;

    @Autowired
    public CardRepositoryImpl(EntityManager entityManager) {
        super();
        this.entityManager = entityManager;
    }


    @Transactional
    @Override
    public Card save(Card card) {
        entityManager.persist(card);
        return card;
    }

    @Override
    public Card findById(Long id) {
        return entityManager.find(Card.class, id);
    }

    @Override
    public List<Card> findByColor(String color) {
        TypedQuery<Card> cardTypedQuery = entityManager.createQuery("SELECT c FROM Card c WHERE c.color = :color", Card.class);
        cardTypedQuery.setParameter("color", color);

        List<Card> cards = cardTypedQuery.getResultList();

        if (cards.isEmpty()) {
            throw new CardException("Card not found " + color, HttpStatus.NOT_FOUND);
        }

        return cards;
    }

    @Override
    public List<Card> findAll() {
        TypedQuery<Card>allCards = entityManager.createQuery("SELECT c FROM Card c", Card.class);
        return allCards.getResultList();
    }

    @Override
    public List<Card> findByValue(Integer value) {
        TypedQuery<Card> cardTypedQuery = entityManager.createQuery("SELECT c FROM Card c WHERE c.value = :value", Card.class);
        cardTypedQuery.setParameter("value", value);
        return cardTypedQuery.getResultList();
    }

    @Override
    public List<Card> findByType(String type) {
        TypedQuery<Card> cardTypedQuery = entityManager.createQuery("SELECT c FROM Card c WHERE c.type = :type", Card.class);
        cardTypedQuery.setParameter("type", type);
        return cardTypedQuery.getResultList();
    }

    @Transactional
    @Override
    public Card update(Card card) {
        return entityManager.merge(card);
    }

    @Transactional
    @Override
    public Card remove(Long id) {
        Card foundCard = findById(id);
        return foundCard;
    }
}

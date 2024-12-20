package com.workintech.fswebs18challengemaven.controller;

import com.workintech.fswebs18challengemaven.repository.CardRepository;
import com.workintech.fswebs18challengemaven.entity.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {


    private final CardRepository cardRepository;

    @Autowired
    public CardController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @PostMapping
    public Card save (@RequestBody Card card) {
        cardRepository.save(card);
        return card;
    }

    @GetMapping
    public List<Card> getAll() {
        return cardRepository.findAll();
    }

    @GetMapping("/byColor/{color}")
    public List<Card> getByColor(@PathVariable("color") String color) {
        return cardRepository.findByColor(color);
    }
    @GetMapping("/byValue/{value}")
    public List<Card> getByValue(@PathVariable("value") Integer value) {
        return cardRepository.findByValue(value);
    }
    @GetMapping("/byType/{type}")
    public List<Card> getByType(@PathVariable("type") String type) {
        return cardRepository.findByType(type);
    }

    @PutMapping
    public Card updateCard(@RequestBody Card card) {
        return cardRepository.update(card);
    }

    @DeleteMapping("/{id}")
    public Card removeCard(@PathVariable Long id) {
        return cardRepository.remove(id);
    }
}

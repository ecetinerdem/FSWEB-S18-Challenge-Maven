package com.workintech.fswebs18challengemaven.util;


import com.workintech.fswebs18challengemaven.exceptions.CardException;
import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.entity.Type;
import org.springframework.http.HttpStatus;

public class CardValidation {
    public static void validate(Card card) {
        // JOKER kontrolü
        if (card.getType() == Type.JOKER) {
            validateJokerCard(card);
        } else {
            validateNonJokerCard(card);
        }
    }

    private static void validateJokerCard(Card card) {
        if (card.getValue() != null || card.getColor() != null) {
            throw new CardException("JOKER kartın value ve color'u null olmalıdır.", HttpStatus.BAD_REQUEST);
        }
    }

    private static void validateNonJokerCard(Card card) {
        // Value ve Type birlikte olamaz
        if (card.getValue() != null && card.getType() != null) {
            throw new CardException("Bir kartta hem value hem type olamaz.", HttpStatus.BAD_REQUEST);
        }

        // Value veya Type mutlaka olmalı
        if (card.getValue() == null && card.getType() == null) {
            throw new CardException("Kartta ya value ya da type olmalıdır.", HttpStatus.BAD_REQUEST);
        }
    }
}
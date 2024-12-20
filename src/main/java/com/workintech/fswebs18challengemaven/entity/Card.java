package com.workintech.fswebs18challengemaven.entity;


import com.workintech.fswebs18challengemaven.util.CardValidation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer value;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Enumerated(EnumType.STRING)
    private Color color;

    public Card (Integer value, Type type, Color color) {
        this.value = value;
        this.type = type;
        this.color = color;
        CardValidation.validate(this);
    }

}

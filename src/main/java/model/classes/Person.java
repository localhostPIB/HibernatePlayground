package model.classes;

import lombok.*;
import model.interfaces.IPerson;

import javax.persistence.*;

/**
 * Eine Model-Klasse Person mit id und Namen.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "Person")
public class Person implements IPerson {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(name = "Bandmitglied", nullable = false)
    private String name;
}

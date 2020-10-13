package model.classes;

import lombok.Getter;
import lombok.Setter;
import model.interfaces.IBand;


import javax.persistence.*;

/**
 * Eine Band in der Personen drin sind.
 */
@Getter
@Setter
@Entity
public class Band implements IBand {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String name;

    @ManyToOne
    private Person person;

}

package model.classes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import model.interfaces.IBand;
import model.interfaces.IPerson;


import javax.persistence.*;
import java.util.List;

/**
 * Eine Band in der Personen drin sind.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Band implements IBand {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(name = "Bandname")
    private String name;

    @OneToMany(targetEntity = Person.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    //EAGER loest org.hibernate.LazyInitializationException: could not initialize proxy problem, aber nur mit bedachtsetzen.
    @JoinColumn(name = "band_id")
    private List<IPerson> person;

}

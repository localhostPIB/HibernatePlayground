package model.classes;

import lombok.*;
import model.interfaces.IBand;
import model.interfaces.IPerson;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Eine Band in der Personen drin sind.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Band")
public class Band implements IBand {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(name = "Bandname", nullable = false)
    private String name;

    @OneToMany(targetEntity = Person.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    //EAGER loest org.hibernate.LazyInitializationException: could not initialize proxy problem, aber nur mit bedachtsetzen.
    @JoinColumn(name = "band_id")
    private List<IPerson> personList = new ArrayList<>();

    @Override
    public List<IPerson> getPerson() {
        return this.personList;
    }

    @Override
    public void setPerson(List<IPerson> person) {
        this.personList = person;
    }

    @Override
    public void add(IPerson person){
        personList.add(person);
    }
}

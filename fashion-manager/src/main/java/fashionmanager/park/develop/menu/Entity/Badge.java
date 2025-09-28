package fashionmanager.park.develop.menu.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "badge")
public class Badge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num")
    private int badgeNum;

    @Column(name = "name")
    private String badgeName;

    @ManyToMany(mappedBy = "badges")
    private List<User> users = new ArrayList<>();
}
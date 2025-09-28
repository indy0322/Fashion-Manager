package fashionmanager.park.develop.menu.Entity;

import jakarta.persistence.*;

import java.util.*;

@Entity
public class Badge {
    @Id
    @GeneratedValue
    @Column(name="num")
    private int badgeNum;



    @ManyToMany(mappedBy = "badges")  // 주인은 User, Badge는 거울 역할
    private Set<User> users = new HashSet<>();

    // getter
    public Set<User> getUsers() {
        return users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Badge)) return false;
        Badge badge = (Badge) o;
        return badgeNum == badge.badgeNum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(badgeNum);
    }

}
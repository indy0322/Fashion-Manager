package fashionmanager.baek.develop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "fashion_post")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class FashionPostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num")
    private int num;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "good")
    private int good;

    @Column(name = "cheer")
    private int cheer;

    @Column(name = "temp")
    private double temp;

    @Column(name = "member_num")
    private int member_num;

    @OneToMany(mappedBy = "fashionPost", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhotoEntity> photos = new ArrayList<>();

    public void addPhoto(PhotoEntity photo) {
        this.photos.add(photo);
        photo.setFashionPost(this);
    }

    @PreRemove
    public void deletePhotos() {
        for (PhotoEntity photo : photos) {
            File file = new File(photo.getPath() + File.separator + photo.getName());
            if (file.exists()) {
                file.delete();
            }
        }
    }
}

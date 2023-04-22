package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Rooms {
    @Id
    @Column(name = "id", length = 45, nullable = false)
    private String id;
    @ManyToOne()
    @JoinColumn(name = "roomType")
    private RoomTypes roomType;

    @OneToOne(cascade = CascadeType.ALL )
    @JoinColumn(name = "student")
    private Student student;
}

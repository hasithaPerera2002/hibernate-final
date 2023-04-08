package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Rooms {
    @Id
    @Column(name = "id", length = 45, nullable = false)
    private String id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "roomType")
    private RoomTypes roomType;

    @OneToOne(cascade = CascadeType.ALL,targetEntity = Student.class)
    private Student student;
}

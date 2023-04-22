package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import util.RoomType;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomTypes {
    @Id
    @Column(length = 45,nullable = false)
    private String roomId;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;
    private double keyMoney;
    @ToString.Exclude
    @OneToMany(mappedBy = "roomType",targetEntity = Rooms.class,cascade = CascadeType.ALL)
    private List<Rooms> roomsList;
}

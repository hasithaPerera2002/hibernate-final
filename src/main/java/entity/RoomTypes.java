package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    @OneToMany(mappedBy = "roomType",targetEntity = Rooms.class)
    private List<Rooms> roomsList;
}

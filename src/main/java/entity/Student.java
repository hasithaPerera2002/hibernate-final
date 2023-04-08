package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @Column(name = "id",length = 45,nullable = false)
    private String id;
    @Column(name = "fname",length = 45,nullable = false)
    private String fname;
    @Column(name = "sname",length = 45,nullable = false)
    private String sname;
    @Column(name = "contact",length = 45,nullable = false)
    private String contact;
    @Column(name = "address",length = 45,nullable = false)
    private String address;
    @Column(name = "email",length = 45,nullable = false)
    private String email;
    @Column(name = "nic",length = 20,nullable = false)
    private String nic;

    @OneToOne(targetEntity = Rooms.class)
    private Rooms rooms;
}

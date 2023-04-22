package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import util.Paid;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @Column(name = "s_id", length = 45, nullable = false)
    private String id;
    @Column(name = "fname", length = 45, nullable = false)
    private String fname;
    @Column(name = "sname", length = 45, nullable = false)
    private String sname;
    @Column(name = "contact", length = 45, nullable = false)
    private String contact;
    @Column(name = "address", length = 45, nullable = false)
    private String address;

    @Column(name = "nic", length = 45,nullable = false)
    private String nic;
    @Column(name = "fromDate",  nullable = false)
    private Date fromDate;
    @Column(name = "toDate", nullable = false)
    private Date toDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "paid")

    private Paid paid;

    @OneToOne(mappedBy = "student",targetEntity = Rooms.class)
    @JoinColumn(name = "roomId")
    @ToString.Exclude
    private Rooms rooms;


}

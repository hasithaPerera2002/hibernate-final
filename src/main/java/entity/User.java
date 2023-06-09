package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "id",length = 45,nullable = false)
    private String id;

    @Column(name = "fname",length = 45,nullable = false)
    private String fname;

    @Column(name = "sname",length = 45,nullable = false)
    private String sname;

    @Column(name = "password",length = 45,nullable = false)
    private String password;

    @Column(name = "address",length = 45,nullable = false)
    private String address;

    @Column(name = "email",length = 30,nullable = false)
    private String email;

    @Column(name = "contact",length = 20,nullable = false)
    private String contact;

    @Column(name = "nic",length = 12,nullable = false)
    private String nic;

}

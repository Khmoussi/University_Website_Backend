package com.example.issatc.Infrastructure.EntityMappers;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity(name = "student")
@Data
public class StudentMapper extends  UserMapper  {

    public StudentMapper() {
    }

    @Column(unique = true)
    long numInscription;

    @ManyToOne
    @JoinColumn(name = "sector_id")
    private SectorMapper sector;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupMapper group;

    @OneToMany(mappedBy = "student")
    List<RecordMapper> records;






    public StudentMapper(String email, String firstName, String lastName, String password, Role role, long phoneNumb, long numInscription) {
        super(email, firstName, lastName, password, role, phoneNumb);
        this.numInscription = numInscription;
    }

    public StudentMapper(String email, String firstName, String lastName, Role role, long numInscription) {
        super(email, firstName, lastName,  role );
        this.numInscription = numInscription;
    }
    public StudentMapper(String email, String firstName, String lastName, Role role, long numInscription,SectorMapper sector) {
        super(email, firstName, lastName,  role );
        this.numInscription = numInscription;
        this.sector=sector;
    }


}

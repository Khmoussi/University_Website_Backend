package com.example.issatc.Entities;

public class Student extends  User{
long numInscription;
int groupId;
String SectorId;
    public Student(String email, String nom, String prenom) {
        super(email, nom, prenom);
    }

    public Student(String email, String nom, String prenom, long numInscription, int groupId, String sectorId) {
        super(email, nom, prenom);
        this.numInscription = numInscription;
        this.groupId = groupId;
        SectorId = sectorId;
    }
}

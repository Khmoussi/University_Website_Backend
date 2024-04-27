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
        this.SectorId = sectorId;
    }

    public Student(String email, String nom, String prenom, long numInscription,  String sectorId) {
        super(email, nom, prenom);
        this.numInscription = numInscription;
        this.SectorId = sectorId;
    }

    public long getNumInscription() {
        return numInscription;
    }

    public int getGroupId() {
        return groupId;
    }



    public String getSectorId() {
        return SectorId;
    }

    public void setNumInscription(long numInscription) {
        this.numInscription = numInscription;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public void setSectorId(String sectorId) {
        SectorId = sectorId;
    }
}

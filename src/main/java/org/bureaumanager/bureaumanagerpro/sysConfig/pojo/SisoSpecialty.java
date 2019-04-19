package org.bureaumanager.bureaumanagerpro.sysConfig.pojo;

public class SisoSpecialty {
    private String id;

    private String specialtyName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSpecialtyName() {
        return specialtyName;
    }

    public void setSpecialtyName(String specialtyName) {
        this.specialtyName = specialtyName == null ? null : specialtyName.trim();
    }
}
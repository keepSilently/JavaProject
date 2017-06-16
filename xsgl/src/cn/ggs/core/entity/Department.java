package cn.ggs.core.entity;

import java.util.Set;

/**
 * Created by silent on 2017-05-28/028.
 */
public class Department {
    private String name;
    private String departmentId;
    private Set<Classes> classesSet;
    private Profession profession;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Department that = (Department) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (departmentId != null ? !departmentId.equals(that.departmentId) : that.departmentId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (departmentId != null ? departmentId.hashCode() : 0);
        return result;
    }

    public Set<Classes> getClassesSet() {
        return classesSet;
    }

    public void setClassesSet(Set<Classes> classesSet) {
        this.classesSet = classesSet;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }
}

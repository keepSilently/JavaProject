package cn.ggs.core.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by silent on 2017-05-28/028.
 */
public class Classes implements Serializable {
    private String classesName;
    private String classesId;
    private Department department;
    private Set<Student> studentSet =new HashSet<Student>();

    public String getClassesName() {
        return classesName;
    }

    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    public String getClassesId() {
        return classesId;
    }

    public void setClassesId(String classesId) {
        this.classesId = classesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Classes classes = (Classes) o;

        if (classesName != null ? !classesName.equals(classes.classesName) : classes.classesName != null) return false;
        if (classesId != null ? !classesId.equals(classes.classesId) : classes.classesId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = classesName != null ? classesName.hashCode() : 0;
        result = 31 * result + (classesId != null ? classesId.hashCode() : 0);
        return result;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Student> getStudentSet() {
        return studentSet;
    }

    public void setStudentSet(Set<Student> studentSet) {
        this.studentSet = studentSet;
    }


}

package cn.ggs.core.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by silent on 2017-05-28/028.
 */
public class Student implements Serializable {
    private String name;
    private String schoolId;
    private String mobile;
    private String dormitory;
    private String dormitoryNum;
    private Date birthday;
    private String img;
    private String studentId;
    private Classes classes;
    private  Boolean gender;

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDormitory() {
        return dormitory;
    }

    public void setDormitory(String dormitory) {
        this.dormitory = dormitory;
    }

    public String getDormitoryNum() {
        return dormitoryNum;
    }

    public void setDormitoryNum(String dormitoryNum) {
        this.dormitoryNum = dormitoryNum;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (name != null ? !name.equals(student.name) : student.name != null) return false;
        if (schoolId != null ? !schoolId.equals(student.schoolId) : student.schoolId != null) return false;
        if (mobile != null ? !mobile.equals(student.mobile) : student.mobile != null) return false;
        if (dormitory != null ? !dormitory.equals(student.dormitory) : student.dormitory != null) return false;
        if (dormitoryNum != null ? !dormitoryNum.equals(student.dormitoryNum) : student.dormitoryNum != null)
            return false;
        if (birthday != null ? !birthday.equals(student.birthday) : student.birthday != null) return false;
        if (img != null ? !img.equals(student.img) : student.img != null) return false;
        if ( gender!= null ? !gender.equals(student.gender) : student.gender != null) return false;


        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (schoolId != null ? schoolId.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (dormitory != null ? dormitory.hashCode() : 0);
        result = 31 * result + (dormitoryNum != null ? dormitoryNum.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (img != null ? img.hashCode() : 0);
        result = 31 * result + (studentId != null ? studentId.hashCode() : 0);
        return result;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }


}

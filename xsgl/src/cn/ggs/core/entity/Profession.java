package cn.ggs.core.entity;



import java.io.Serializable;
import java.util.Set;

/**
 * Created by silent on 2017-05-28/028.
 */
public class Profession implements Serializable {
    private String professionName;
    private String professionId;
    private Set<Department> department;

    public String getProfessionName() {
        return professionName;
    }

    public void setProfessionName(String professionName) {
        this.professionName = professionName;
    }

    public String getProfessionId() {
        return professionId;
    }

    public void setProfessionId(String professionId) {
        this.professionId = professionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Profession that = (Profession) o;

        if (professionName != null ? !professionName.equals(that.professionName) : that.professionName != null)
            return false;
        if (professionId != null ? !professionId.equals(that.professionId) : that.professionId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = professionName != null ? professionName.hashCode() : 0;
        result = 31 * result + (professionId != null ? professionId.hashCode() : 0);
        return result;
    }

    public Set<Department> getDepartment() {
        return department;
    }

    public void setDepartment(Set<Department> department) {
        this.department = department;
    }


}

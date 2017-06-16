package cn.ggs.core.action;

import cn.ggs.core.utils.PageResult;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by silent on 2017-05-29/029.
 */
public abstract class BaseAction  extends ActionSupport{

    protected String selectedRows [];
    protected int pageNo;
    protected int pageSize;
    //默认页大小
    public static int DEFAULT_PAGE_SIZE = 10;
    public String listTemplate() {
        return "listTemplate";
    }
    public String addUi() {
        return "addUi";
    }
    public String listUi() {
        return "listUi";
    }
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public void setSelectedRows(String[] selectedRows) {
        this.selectedRows = selectedRows;
    }
}

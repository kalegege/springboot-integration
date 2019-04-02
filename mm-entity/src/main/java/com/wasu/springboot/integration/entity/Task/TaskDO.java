package com.wasu.springboot.integration.entity.Task;

public class TaskDO {

    private String name;

    private Integer parentid;

    private Integer parentids;

    private Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Integer getParentids() {
        return parentids;
    }

    public void setParentids(Integer parentids) {
        this.parentids = parentids;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

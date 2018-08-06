package gd.com.pojo;

import javax.persistence.*;

@Table(name = "t_category")
public class Category {

    @Id
    @Column(name="id") //mapper才使用
    @GeneratedValue(strategy=GenerationType.IDENTITY) //主键
    private Long id;

    @Column(name="name")
    private String name;  //分类名称

    @Column(name="parent_id")
    private Long parentId; //上级分类标识

    @Column(name="sort")
    private Integer sort;  //排序(数值越大排前面)

    @Column(name="status")
    private Integer status;  //状态（0表示禁用，1表示在用，2表示删除）

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
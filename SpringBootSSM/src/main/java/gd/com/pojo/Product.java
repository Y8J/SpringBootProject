package gd.com.pojo;

import javax.persistence.*;

@Table(name = "t_product")
public class Product {
    @Id
    @Column(name="id") //mapper才使用
    @GeneratedValue(strategy=GenerationType.IDENTITY) //主键
    private Long id;

    @Column(name="code")
    private String code;  //商品编号

    @Column(name="name")
    private String name;  //商品名称

    @Column(name="unit")
    private String unit;  //商品单位

    @Column(name="category_id")
    private Long categoryId;  //商品分类标识（外键）

    @Column(name="status")
    private Integer status;  //状态（0表示下架、1表示上架、2表示删除

    @Column(name="note")
    private String note;  //备注

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}
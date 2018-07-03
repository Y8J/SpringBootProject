package gd.com.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 权限表
 * @author yangjing
 *
 */
@Table(name = "t_permission")
public class Permission implements Serializable{
	@Id
	@Column(name="id") //mapper才使用  
	@GeneratedValue(strategy=GenerationType.IDENTITY) //主键
    private Long id;
	
	@Column(name="parent_id") //mapper才使用  
	private Long  parentId; //父级ID
	
	@Column(name="menu_name") //mapper才使用  
	private  String menuName; //权限名称
	
	@Column(name="menu_url") //mapper才使用  
	private String menuUrl; //菜单链接
	
	@Column(name="menu_icon") //mapper才使用  
	private String menuIcon ;//菜单图片
	
	@Column(name="sort_num") //mapper才使用  
    private Integer sortNum; //排序
	
	@Column(name="user_id") //mapper才使用  
    private Integer userId;//创建这个菜单的用户id
	
	@Column(name="is_del") //mapper才使用  
    private Integer isDel; //1-- 删除状态，0 -- 正常
	
	@Column(name="update_time") //mapper才使用  
    private Date updateTime;//修改时间
	
	@Column(name="create_time") //mapper才使用  
    private Date createTime; //创建时间

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Permission [id=" + id + ", parentId=" + parentId
				+ ", menuName=" + menuName + ", menuUrl=" + menuUrl
				+ ", menuIcon=" + menuIcon + ", sortNum=" + sortNum
				+ ", userId=" + userId + ", isDel=" + isDel + ", updateTime="
				+ updateTime + ", createTime=" + createTime + "]";
	}
	
}
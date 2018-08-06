package gd.com.service;


import gd.com.pojo.Category;

import java.util.List;

/**
 * 商品类别业务类
 */
public interface CategoryService {

    /**
     * 添加同级别的商品类别
     * @param bean
     */
    public Integer save(Category bean);

    /**
     * 根据商品类别Id 查询商品类别
     * @param id
     * @return
     */
    public Category queryBeanById(Long id);

    /**
     * 根据商品类别名称查询商品类别
     * @param name
     * @return
     */
    public Category queryBeanByName(String name);

    /**
     * 修改商品类别
     */
    public Integer update(Category bean);

    /**
     * 查询商品类别list集合
     * @param bean
     * @return
     */
    public List<Category> queryListAll(Category bean);

    /**
     * 根据父类id查询商品类别值
     * @param parentId
     * @return
     */
    public List<Category> queryListByParentId(Long parentId);
}

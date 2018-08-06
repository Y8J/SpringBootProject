package gd.com.service.impl;


import gd.com.mapper.CategoryMapper;
import gd.com.pojo.Category;
import gd.com.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 商品类别业务实现类
 */
@Service
public class CategoryServiceImpl implements CategoryService {

   @Autowired
   private CategoryMapper categoryMapper;


    @Override
    public Integer save(Category bean) {
        //表示新增同级商品类别
        int insert = categoryMapper.insert(bean);
        return insert;
    }

    @Override
    public Category queryBeanById(Long id) {
        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", id);
        List<Category> Categorys = categoryMapper.selectByExample(example);
        return Categorys!=null&&(!Categorys.isEmpty())?Categorys.get(0):null;
    }

    @Override
    public Category queryBeanByName(String name) {
        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name", name);
        List<Category> Categorys = categoryMapper.selectByExample(example);
        return Categorys!=null&&(!Categorys.isEmpty())?Categorys.get(0):null;
    }

    @Override
    public Integer update(Category bean) {
        int i = categoryMapper.updateByPrimaryKey(bean);
        return i;
    }

    @Override
    public List<Category> queryListAll(Category bean) {
        List<Category> select = categoryMapper.select(bean);
        return select;
    }

    @Override
    public List<Category> queryListByParentId(Long parentId) {
        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("parentId", parentId);
        List<Category> lsit = categoryMapper.selectByExample(example);
        return lsit;
    }
}

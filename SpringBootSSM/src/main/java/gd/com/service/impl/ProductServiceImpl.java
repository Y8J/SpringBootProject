package gd.com.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import gd.com.mapper.ProductMapper;
import gd.com.pojo.Product;
import gd.com.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public PageInfo<Product> queryProductPage(Product bean, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        Example example = new Example(Product.class);
        // where 条件
        Example.Criteria criteria = example.createCriteria();

        //模糊名字查询
        criteria.orLike("name", "%" + bean.getName() + "%");
        criteria.andEqualTo("categoryId",bean.getCategoryId());
        example.and(criteria);
        List<Product> list = productMapper.selectByExample(example);

        return new PageInfo<Product>(list);
    }
}

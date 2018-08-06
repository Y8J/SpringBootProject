package gd.com.service;

import com.github.pagehelper.PageInfo;
import gd.com.pojo.Product;

/**
 * 商品管理业务层接口
 */
public interface ProductService {

    /**
     * 根据查询条件，查询商品分页信息
     *
     * @param bean
     *            封装查询条件
     * @param pageNum
     *            查询第几页数据
     * @param pageSize
     *            每页显示多少条记录
     * @return 查询结果
     */
    public PageInfo<Product> queryProductPage(Product bean, Integer pageNum, Integer pageSize);
}

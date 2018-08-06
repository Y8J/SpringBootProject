package gd.com.controller.admin;

import gd.com.pojo.Category;
import gd.com.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("category")
@Controller
public class CategoryAction {

    @Autowired
    private CategoryService categoryService;

    /**
     * 查询所有节点返回
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/queryLevelCategoryAll.do")
    public ResponseEntity<Map<String,Object>> queryLevelCategoryAll(){

        try {
            //查询一级菜单
            List<Category> categorys = categoryService.queryListAll(null);
            //存放转换后数据的集合
            List<Map<String,Object>> comboTreeList  =new ArrayList<Map<String,Object>>();
            createComboTreeTree(comboTreeList,categorys,0L);
            if (comboTreeList.isEmpty()) {
                // 资源不存在，响应404
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("data",comboTreeList);
            // 200 return ResponseEntity.status(HttpStatus.OK).body(user);
            return ResponseEntity.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

    }

    /**
     * 将封装成树开始
     * @param list
     * @param fid 父id
     */
    private void createComboTreeTree(List<Map<String,Object>> comboTreeList,List<Category> list, Long fid) {
        for (int i = 0; i < list.size(); i++) {
            Category role = (Category) list.get(i);
            if (role.getParentId()==0) {
                Map<String, Object> map = new HashMap<String, Object>();
                //这里必须要将对象角色的id、name转换成ComboTree在页面的显示形式id、text
                //ComboTree,不是数据表格，没有在页面通过columns转换数据的属性
                map.put("id", list.get(i).getId());         //id
                map.put("text",list.get(i).getName());      //角色名
                List<Map<String, Object>> pangList = createComboTreeChildren(list, role.getId());
                if(pangList!=null && (!pangList.isEmpty())){
                    map.put("children",pangList);
                }
                comboTreeList.add(map);
                System.out.println(map);
            }

        }
    }


    /**
     * 递归设置role树
     * @param list
     * @param fid
     * @return
     */
    private List<Map<String, Object>> createComboTreeChildren(List<Category> list, Long fid) {
        List<Map<String, Object>> childList = new ArrayList<Map<String, Object>>();
        for (int j = 0; j < list.size(); j++) {

            Category treeChild = (Category) list.get(j);
            if (treeChild.getParentId()==fid) {
                Map<String, Object> map  = new HashMap<String, Object>();
                //这里必须要将对象角色的id、name转换成ComboTree在页面的显示形式id、text
                //ComboTree,不是数据表格，没有在页面通过columns转换数据的属性
                map.put("id", list.get(j).getId());
                map.put("text", list.get(j).getName());

                List<Map<String, Object>> pangList = createComboTreeChildren(list, treeChild.getId());
                if(pangList!=null && (!pangList.isEmpty())){
                    map.put("children", createComboTreeChildren(list, treeChild.getId()));
                }
                childList.add(map);
                System.out.println(map);
            }


        }
        return childList;
    }


    /**
     * 查询1级菜单
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/queryLevelCategory.do")
    public ResponseEntity<List<Category>> queryLevelCategory(Long parentId){
        if (parentId == null) {
            parentId = 0L;
        }
        try {
            //查询一级菜单
            List<Category> categorys = categoryService.queryListByParentId(parentId);
            if (categorys.isEmpty()) {
                // 资源不存在，响应404
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            // 200 return ResponseEntity.status(HttpStatus.OK).body(user);
            return ResponseEntity.ok(categorys);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

    }


    /**
     * 迭代全部相关的商品类别
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/queryAllCategory.do")
    public ResponseEntity<List<Category>> queryAllCategory(){

        try {
            //查询一级菜单
            List<Category> list = new ArrayList<Category>();
            Category bean = new Category();
            //查询一级菜单
            List<Category> categorys = categoryService.queryListAll(bean);
            sortList(list,categorys,0L);

            if (null == categorys && categorys.isEmpty()) {
                // 资源不存在，响应404
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            // 200 return ResponseEntity.status(HttpStatus.OK).body(user);
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     *  List<SysMenu> list = Lists.newArrayList(); 获取容器
     *  List<SysMenu> listAll = sysMenuMng.getList(); 查询全部
     * @param list
     * @param listAll
     * @param id=1
     */
    public static void sortList(List<Category> list, List<Category> listAll, Long id){
        for (int i=0; i<listAll.size(); i++){
            Category e = listAll.get(i);
            if (e.getParentId()!=null && e.getParentId().equals(id)){
                list.add(e);
                // 判断是否还有子节点, 有则继续获取子节点
                for (int j=0; j<listAll.size(); j++){
                    Category child = listAll.get(j);
                    if (child.getParentId()!=null && child.getParentId().equals(e.getId())){
                        Long eid = e.getId();
                        sortList(list, listAll,e.getId());
                        break;
                    }
                }
            }
        }
    }


    /**
     * 新增
     * @return商品类别
     */
    @ResponseBody
    @RequestMapping(value="/saveCategory.do",method=RequestMethod.POST)
    public ResponseEntity<Void> saveCategory(@RequestBody Category bean){
        if(bean.getParentId()==null){
            bean.setParentId(0L);
        }
        try {
            this.categoryService.save(bean);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     * 根据id查询商品类别
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/queryCategoryById.do",method=RequestMethod.GET)
    public ResponseEntity<Category> queryCategoryById(Long id){
        try {
            Category bean = this.categoryService.queryBeanById(id);
            if (null == bean) {
                // 资源不存在，响应404
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            // 200
            // return ResponseEntity.status(HttpStatus.OK).body(user);
            return ResponseEntity.ok(bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     * 更新商品类别
     * @param bean
     * @return
     */
    @RequestMapping(value="/updateCategory.do",method = RequestMethod.POST)
    public ResponseEntity<Void> updateCategory(@RequestBody Category bean) {
        try {
            if (null==bean.getId()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            this.categoryService.update(bean);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     * 商品类别设置为停用
     * @param bean
     * @return
     */
    @RequestMapping(value="/updateCategoryDisable.do",method = RequestMethod.POST)
    public ResponseEntity<String> updateCategoryDisable(@RequestBody Category bean) {
        try {
            if (null==bean.getId()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("商品类别Id不能为null");
            }
            if(null==bean.getStatus()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("商品类别状态不能为null");
            }
            Category Category = categoryService.queryBeanById(bean.getId());
            if(null == Category){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            //更新状态码
            Category.setStatus(bean.getStatus());
            this.categoryService.update(Category);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }



}

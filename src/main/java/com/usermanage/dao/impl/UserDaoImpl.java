package com.usermanage.dao.impl;

import com.usermanage.dao.UserDao;
import com.usermanage.domain.UserBean;
import com.usermanage.util.JDBCUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDaoImpl implements UserDao {
    private static JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtil.getDataSource());

    /*** 查训所有用户
     * @return */
    @Override
    public List<UserBean> findAll() {
        String Sql_findAll = "select * from user";
        /* List<Map<String, Object>> mapList = jdbcTemplate.queryForList(findAll_Sql);*/
        List<UserBean> userBeans = jdbcTemplate.query(Sql_findAll, new BeanPropertyRowMapper<>(UserBean.class));
        return userBeans;
    }

    /*** 用户登录
     * @param username
     * @param password
     * */
    @Override
    public UserBean findByUsernameAndPassword(String username, String password) {
        String Sql_findByNameAndPass = "select * from user where username =? and password = ? ";
        UserBean userBean = jdbcTemplate.queryForObject(Sql_findByNameAndPass, new BeanPropertyRowMapper<>(UserBean.class), username, password);
        return userBean;
    }

    /*** 根据id查询用户
     *
     * @return */
    @Override
    public UserBean findById(int id) {
        String Sql_findById = "select * from user where id= ? ";
        UserBean userBean = jdbcTemplate.queryForObject(Sql_findById, new BeanPropertyRowMapper<>(UserBean.class), id);
        return userBean;
    }

    /*** 新增用户
     * @param user
     * */
    @Override
    public void addUser(UserBean user) {
        String Sql_addUser = "insert into user values(null,?,?,?,?,?,?,null,null)";
        jdbcTemplate.update(Sql_addUser, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail());
    }

    /***
     * 用户信息更新
     * @param user
     */
    @Override
    public void updateUser(UserBean user) {
        String sql = "update user set name = ?,gender = ? ,age = ? , address = ? , qq = ?, email = ? where id = ?";
        jdbcTemplate.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail(), user.getId());
    }

    /***
     * 删除用户
     * @param id
     */
    @Override
    public void delUser(int id) {
        //1.定义sql
        String sql = "delete from user where id = ?";
        //2.执行sql
        jdbcTemplate.update(sql, id);
    }

    /***
     * 模糊查询
     * 根据多个条件查询 返回总的结果记录数
     * @param condition
     * @return
     */
    @Override
    public int findtotalcount(Map<String, String[]> condition) {
        //1.定义模板初始化sql
        String sql = "select count(*) from user where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        //2.遍历map
        Set<String> keySet = condition.keySet();
        //定义参数的集合
        List<Object> params = new ArrayList<Object>();
        for (String key : keySet) {

            //排除分页条件参数
            if("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }

            //获取value
            String value = condition.get(key)[0];
            //判断value是否有值
            if(value != null && !"".equals(value)){
                //有值
                sb.append(" and "+key+" like ? ");
                params.add("%"+value+"%");//？条件的值
            }
        }
        System.out.println(sb.toString());
        System.out.println(params);

        return jdbcTemplate.queryForObject(sb.toString(),Integer.class,params.toArray());
    }

    /***
     * 分页查询
     * @param start
     * @param rows
     * @param condition
     * @return
     */
    @Override
    public List findByPage(int start, int rows, Map<String, String[]> condition) {
        String sql = "select * from user  where 1 = 1 ";

        StringBuilder sb = new StringBuilder(sql);
        //2.遍历map
        Set<String> keySet = condition.keySet();
        //定义参数的集合
        List<Object> params = new ArrayList<Object>();
        for (String key : keySet) {

            //排除分页条件参数
            if("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }

            //获取value
            String value = condition.get(key)[0];
            //判断value是否有值
            if(value != null && !"".equals(value)){
                //有值
                sb.append(" and "+key+" like ? ");
                params.add("%"+value+"%");//？条件的值
            }
        }

        //添加分页查询
        sb.append(" limit ?,? ");
        //添加分页查询参数值
        params.add(start);
        params.add(rows);
        sql = sb.toString();
        System.out.println(sql);
        System.out.println(params);

        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<UserBean>(UserBean.class),params.toArray());
    }


}

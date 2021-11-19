package com.usermanage.dao.impl;

import com.usermanage.dao.UserDao;
import com.usermanage.domain.UserBean;
import com.usermanage.util.JDBCUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

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


}

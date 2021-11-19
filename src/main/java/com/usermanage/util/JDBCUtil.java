package com.usermanage.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JDBCUtil {
    private static DataSource dataSource=null;
    static {
        try {
            //创建properties对象
            Properties properties=new Properties();
            //读取配置文件
            InputStream resourceAsStream = JDBCUtil.class.getClassLoader()
                    .getResourceAsStream("druid.properties");
            //加载配置文件
            properties.load(resourceAsStream);
            dataSource= DruidDataSourceFactory.createDataSource(properties);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static DataSource getDataSource() {
        return dataSource;
    }
}

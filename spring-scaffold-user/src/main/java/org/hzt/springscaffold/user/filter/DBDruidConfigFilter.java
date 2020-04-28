package org.hzt.springscaffold.user.filter;

import com.alibaba.druid.filter.config.ConfigFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.util.Properties;

/**
 * 数据库拦截器
 *
 */
public class DBDruidConfigFilter extends ConfigFilter {

    @Override
    public void decrypt(DruidDataSource dataSource, Properties info) {

        try {
            String encryptedPassword = null;
            if (info != null) {
                encryptedPassword = info.getProperty(DruidDataSourceFactory.PROP_PASSWORD);
            }

            if (encryptedPassword == null || encryptedPassword.length() == 0) {
                encryptedPassword = dataSource.getConnectProperties().getProperty(DruidDataSourceFactory.PROP_PASSWORD);
            }

            if (encryptedPassword == null || encryptedPassword.length() == 0) {
                encryptedPassword = dataSource.getPassword();
            }
            // 支持自定义的数据库密码加密方式
            DBPasswordFactory factory = new DBPasswordFactory();
            factory.setPassword(encryptedPassword);
            String passwordPlainText = factory.getObject();

            if (info != null) {
                info.setProperty(DruidDataSourceFactory.PROP_PASSWORD, passwordPlainText);
            } else {
                dataSource.setPassword(passwordPlainText);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to decrypt.", e);
        }

    }
}

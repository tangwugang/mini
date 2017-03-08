package com.sanweibook.xsd;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by twg on 17/2/14.
 */
public class SpringXsdTest {

    @Test
    public void test() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring-mysql.xml");
        DataSource dataSource = (DataSource)context.getBean("datasource");
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from legend_shop where is_deleted = ?");
            statement.setString(1,"N");
            ResultSet resultSet = statement.executeQuery();
            resultSet.last();
            System.out.println("count = " + resultSet.getRow());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}

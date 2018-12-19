package com.wbg.springaopdatasource.jdbc;

import com.wbg.springaopdatasource.entity.Role;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Component
public class RoleDAO {

    public List<Role> listAll() throws SQLException {
        List<Role> list = new ArrayList<>();
        PreparedStatement ps = ConnM.getConnection().prepareStatement("select * from role");
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            list.add(
                    new Role(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3))
            );
        }
        return list;
    };

    public void save(Role role) throws SQLException {

            PreparedStatement ps = ConnM.getConnection().prepareStatement("insert into Role(role_name,note) values(?,?)");
            ps.setString(1,role.getRoleName());
            ps.setString(2,role.getNote());
            ps.executeUpdate();

    }


}

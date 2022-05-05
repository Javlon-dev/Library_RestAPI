package com.company.repository;

import com.company.db.DBConnection;
import com.company.dto.BookDTO;
import com.company.dto.StudentDTO;
import com.company.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Component
public class StudentRepository {


    public int createStudent(StudentDTO dto) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnection.getConnection();
            String sql = "insert into student(uuid,firstname,lastname,phone,added_date)\n" +
                    "values(?,?,?,?,?)";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, dto.getUuid());
            preparedStatement.setString(2, dto.getFirstname());
            preparedStatement.setString(3, dto.getLastname());
            preparedStatement.setString(4, dto.getPhone());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(dto.getAdded_date()));

            return preparedStatement.executeUpdate();

        } catch (SQLException | RuntimeException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException | RuntimeException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public List<StudentDTO> getListStudent() {
        List<StudentDTO> list = new LinkedList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnection.getConnection();
            String sql = "select * from student\n" +
                    "where visible = true\n" +
                    "order by added_date desc";

            preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StudentDTO dto = new StudentDTO();
                dto.setUuid(resultSet.getString("uuid"));
                dto.setFirstname(resultSet.getString("firstname"));
                dto.setLastname(resultSet.getString("lastname"));
                dto.setPhone(resultSet.getString("phone"));
                dto.setAdded_date(resultSet.getTimestamp("added_date").toLocalDateTime());
                list.add(dto);
            }


        } catch (SQLException | RuntimeException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException | RuntimeException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public int updateStudent(String uuid, StudentDTO dto) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnection.getConnection();
            String sql = "update student\n" +
                    "set firstname = ?,\n" +
                    "lastname = ?,\n" +
                    "phone = ?\n" +
                    "where uuid = ?\n" +
                    "and visible = true";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, dto.getFirstname());
            preparedStatement.setString(2, dto.getLastname());
            preparedStatement.setString(3, dto.getPhone());
            preparedStatement.setString(4, uuid);

            return preparedStatement.executeUpdate();

        } catch (SQLException | RuntimeException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException | RuntimeException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public int deleteStudent(String uuid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnection.getConnection();
            String sql = "update student\n" +
                    "set visible = false\n" +
                    "where uuid = ?\n" +
                    "and visible = true;";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, uuid);

            return preparedStatement.executeUpdate();

        } catch (SQLException | RuntimeException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException | RuntimeException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
}

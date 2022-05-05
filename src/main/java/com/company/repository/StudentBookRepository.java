package com.company.repository;

import com.company.enums.StudentBookStatus;
import com.company.db.DBConnection;
import com.company.dto.StudentBookDTO;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Component
public class StudentBookRepository {

    public int createStudentBook(StudentBookDTO dto) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnection.getConnection();
            String sql = "insert into student_book(uuid,student_uuid,book_uuid,taken_date,status)\n" +
                    "values(?,?,?,?,?)";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, dto.getUuid());
            preparedStatement.setString(2, dto.getStudent_uuid());
            preparedStatement.setString(3, dto.getBook_uuid());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(dto.getTaken_date()));
            preparedStatement.setString(5, dto.getStatus().name());

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

    public List<StudentBookDTO> getListStudentBook() {
        List<StudentBookDTO> list = new LinkedList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnection.getConnection();
            String sql = "select * from student_book\n" +
                    "where status = ?\n" +
                    "order by taken_date desc";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, StudentBookStatus.TAKEN.name());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StudentBookDTO dto = new StudentBookDTO();
                dto.setUuid(resultSet.getString("uuid"));
                dto.setStudent_uuid(resultSet.getString("student_uuid"));
                dto.setBook_uuid(resultSet.getString("book_uuid"));
                dto.setTaken_date(resultSet.getTimestamp("taken_date").toLocalDateTime());
                dto.setStatus(StudentBookStatus.valueOf(resultSet.getString("status")));
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

    public int updateStudentBook(String uuid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnection.getConnection();
            String sql = "update student_book\n" +
                    "set status = ?\n" +
                    "where uuid = ?\n" +
                    "and status = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, StudentBookStatus.RETURNED.name());
            preparedStatement.setString(2, uuid);
            preparedStatement.setString(3, StudentBookStatus.TAKEN.name());

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

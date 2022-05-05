package com.company.repository;

import com.company.db.DBConnection;
import com.company.dto.BookDTO;
import com.company.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Component
public class BookRepository {

    public int createBook(BookDTO dto) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnection.getConnection();
            String sql = "insert into book(uuid,author,title,added_date)\n" +
                    "values(?,?,?,?)";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, dto.getUuid());
            preparedStatement.setString(2, dto.getAuthor());
            preparedStatement.setString(3, dto.getTitle());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(dto.getAdded_date()));

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

    public List<BookDTO> getListBook() {
        List<BookDTO> list = new LinkedList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnection.getConnection();
            String sql = "select * from book\n" +
                    "where visible = true\n" +
                    "order by added_date desc";

            preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                BookDTO dto = new BookDTO();
                dto.setUuid(resultSet.getString("uuid"));
                dto.setTitle(resultSet.getString("title"));
                dto.setAuthor(resultSet.getString("author"));
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

    public int updateBook(String uuid, BookDTO dto) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnection.getConnection();
            String sql = "update book\n" +
                    "set author = ?,\n" +
                    "title = ?\n" +
                    "where uuid = ?\n" +
                    "and visible = true;";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, dto.getAuthor());
            preparedStatement.setString(2, dto.getTitle());
            preparedStatement.setString(3, uuid);

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

    public int deleteBook(String uuid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnection.getConnection();
            String sql = "update book\n" +
                    "set visible = false\n" +
                    "where uuid = ?";

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

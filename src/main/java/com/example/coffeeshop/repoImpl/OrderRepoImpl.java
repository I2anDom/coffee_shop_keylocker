package com.example.coffeeshop.repoImpl;

import com.example.coffeeshop.dto.OrderReprDTO;
import com.example.coffeeshop.repo.OrderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class OrderRepoImpl implements OrderRepo {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    static final String DB_URL = "jdbc:mysql://localhost:3306/coffee_shop";
    static final String USER = "root";
    static final String PASS = "12345678";

    @Override
    public void addToOrder(int dishId, int orderId) {
        String query = "INSERT INTO orders_dishes  (order_id, dish_id) VALUES (:order_id, :dish_id)";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("dish_id", dishId)
                .addValue("order_id", orderId);
        namedParameterJdbcTemplate.update(query, mapSqlParameterSource);
    }

    @Override
    public int createOrder() {
        String query = "INSERT INTO coffee_shop.order (creation_time) VALUES(:time)";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("time", new Timestamp(System.currentTimeMillis()));
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(query, mapSqlParameterSource, keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public List<OrderReprDTO> getUserOrder(int userId) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            Statement stmt = conn.prepareStatement("fewf");
            PreparedStatement pstmt = conn.prepareStatement(
                    "SELECT ANY_VALUE(dish.id) AS dish_id, " +
                            "dish.name AS name, " +
                            "ANY_VALUE(dish.weight) AS weight, " +
                            "ANY_VALUE(dish.price) AS price, " +
                            "COUNT(dish.name) AS dish_count " +
                            "FROM customer " +
                            "INNER JOIN orders_dishes ON customer.active_order_id = orders_dishes.order_id " +
                            "INNER JOIN dish ON orders_dishes.dish_id = dish.id " +
                            "WHERE customer.id = ? " +
                            "GROUP BY dish.name"
            );
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            List<OrderReprDTO> orderReprDTOS = new ArrayList<>();
            while (rs.next()) {
                orderReprDTOS.add(
                        OrderReprDTO.builder()
                                .dishId(rs.getInt("dish_id"))
                                .name(rs.getString("name"))
                                .price(rs.getInt("price"))
                                .weight(rs.getFloat("weight"))
                                .count(rs.getInt("dish_count"))
                                .build()
                );
            }
            return orderReprDTOS;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

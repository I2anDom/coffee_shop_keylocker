package com.example.coffeeshop.repoImpl;

import com.example.coffeeshop.repo.OrderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
@RequiredArgsConstructor
public class OrderRepoImpl implements OrderRepo {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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
}

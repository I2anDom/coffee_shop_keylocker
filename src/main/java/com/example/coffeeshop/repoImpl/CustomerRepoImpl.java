package com.example.coffeeshop.repoImpl;

import com.example.coffeeshop.dto.DishDTO;
import com.example.coffeeshop.dto.OrderDTO;
import com.example.coffeeshop.dto.RegistrationDTO;
import com.example.coffeeshop.dto.CustomerDTO;
import com.example.coffeeshop.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class CustomerRepoImpl implements CustomerRepo {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public CustomerDTO saveUser(RegistrationDTO registrationDTO) {
        String query = "INSERT INTO customer (first_name, last_name, phone_number) " +
                "VALUES(:firstName, :lastName, :phoneNumber)";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("firstName", registrationDTO.getFirstName())
                .addValue("lastName", registrationDTO.getLastName())
                .addValue("phoneNumber", registrationDTO.getPhoneNumber());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(query, mapSqlParameterSource, keyHolder);

        int generatedId = keyHolder.getKey().intValue();
        return getUser(generatedId);
    }

    @Override
    public CustomerDTO getUser(int id) {
        String query = "SELECT * FROM customer WHERE (id) = (:id)";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("id", id);
        return ((ArrayList<CustomerDTO>) namedParameterJdbcTemplate
                .query(query, mapSqlParameterSource, new UserMapper()))
                .get(0);
    }

    @Override
    public CustomerDTO deleteUser(int id) {
        String query = "DELETE FROM customer WHERE (id) = (:id)";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("id", id);
        CustomerDTO customerToDelete = getUser(id);
        namedParameterJdbcTemplate.update(query, mapSqlParameterSource);

        return customerToDelete;
    }

    @Override
    public void updateUser(int id, RegistrationDTO registrationDTO) {
        CustomerDTO infoFromDB = getUser(id);
        infoFromDB.setFirstName(registrationDTO.getFirstName());
        infoFromDB.setLastName(registrationDTO.getLastName());
        infoFromDB.setPhoneNumber(registrationDTO.getPhoneNumber());

        String query = "UPDATE customer SET first_name = :firstName, " +
                "last_name = :lastName, phone_number = :phoneNumber " +
                "WHERE id = :id";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("firstName", registrationDTO.getFirstName())
                .addValue("lastName", registrationDTO.getLastName())
                .addValue("phoneNumber", registrationDTO.getPhoneNumber())
                .addValue("id", id);
        namedParameterJdbcTemplate.update(query, mapSqlParameterSource);

    }

    @Override
    public void updateUsersFirstName(int id, String firstName) {
        String query = "UPDATE customer SET first_name = :firstName WHERE id = :id";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("firstName", firstName)
                .addValue("id", id);
        namedParameterJdbcTemplate.update(query, mapSqlParameterSource);
    }

    @Override
    public void updateUsersLastName(int id, String lastName) {
        String query = "UPDATE customer SET last_name = :lastName WHERE id = :id";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("lastName", lastName)
                .addValue("id", id);
        namedParameterJdbcTemplate.update(query, mapSqlParameterSource);
    }

    @Override
    public void updateUsersPhoneNumber(int id, String phoneNumber) {
        String query = "UPDATE customer SET phone_number = :phoneNumber WHERE id = :id";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("phoneNumber", phoneNumber)
                .addValue("id", id);
        namedParameterJdbcTemplate.update(query, mapSqlParameterSource);
    }

    @Override
    public OrderDTO getActiveOrder(int userId) {
        String query = "SELECT * from customer c " +
                "JOIN orders_dishes od on c.active_order_id = od.order_id " +
                "JOIN dish d on od.dish_id = d.id " +
                "WHERE c.id = :id";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("id", userId);
        List<Map<String, Object>> mapList = namedParameterJdbcTemplate.queryForList(query, mapSqlParameterSource);
        OrderDTO order = new OrderDTO();
        List<DishDTO> dishList = new ArrayList<>();
        if(mapList.isEmpty()){
            return null;
        }
        order.setId((Integer) mapList.get(0).get("id"));
        order.setTimestamp((Timestamp) mapList.get(0).get("creation_time"));
        mapList.forEach(m -> {
            DishDTO dishDTO = new DishDTO();
            dishDTO.setPrice((Integer) m.get("price"));
            dishDTO.setCategoryId((Integer) m.get("category_id"));
            dishDTO.setWeight((Float) m.get("weight"));
            dishDTO.setName((String) m.get("name"));
            dishList.add(dishDTO);
        });
        order.setDishes(dishList);
        return order;
    }

    @Override
    public Integer getActiveOrderId(int userId) {
        String query = "SELECT active_order_id from customer WHERE id = :id";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("id", userId);
        List<Integer> activeOrders = namedParameterJdbcTemplate
                .queryForList(query, mapSqlParameterSource, Integer.class);
        return activeOrders.isEmpty() ? null : activeOrders.get(0);
    }

    @Override
    public void addOrderToUser(int userId, int orderId) {
        String query = "UPDATE customer SET active_order_id = :order_id WHERE id = :user_id";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("user_id", userId)
                .addValue("order_id", orderId);
        namedParameterJdbcTemplate.update(query, mapSqlParameterSource);
    }

    @Override
    public CustomerDTO getUserByPhoneNumber(String phoneNumber) {
        String query = "SELECT * FROM customer WHERE phone_number = :phoneNumber";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("phoneNumber", phoneNumber);
        List<CustomerDTO> foundUsers = namedParameterJdbcTemplate
                .query(query, mapSqlParameterSource, new BeanPropertyRowMapper<>(CustomerDTO.class));
        return foundUsers.isEmpty() ? null : foundUsers.get(0);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        String query = "SELECT * FROM customer";
        List<CustomerDTO> allUsers = namedParameterJdbcTemplate
                .query(query, new BeanPropertyRowMapper<>(CustomerDTO.class));
        return allUsers;
    }

    class UserMapper implements RowMapper {

        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            CustomerDTO user = new CustomerDTO();
            user.setId(rs.getInt("id"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setPhoneNumber(rs.getString("phone_number"));
            return user;
        }
    }
}

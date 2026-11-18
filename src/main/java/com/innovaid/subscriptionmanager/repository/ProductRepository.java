package com.innovaid.subscriptionmanager.repository;

import com.innovaid.subscriptionmanager.model.Product;
import com.innovaid.subscriptionmanager.model.ProductStatusType;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;




@Repository 
public class ProductRepository {
    
    
    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    private Product productRowMapper(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setSerialNumber(rs.getInt("serial_number"));
        product.setStatusType(ProductStatusType.getTypeFromString(rs.getString("status")));
        product.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
        product.setUpdatedAt(rs.getObject("updated_at", LocalDateTime.class));
        return product;
    }

    public Product createProduct(){
        Product product = jdbcTemplate.queryForObject(
            "INSERT INTO product DEFAULT VALUES RETURNING *",
            this::productRowMapper
        );
        return product;
    }

    public void updateProductStatus(int serialNumber, ProductStatusType status){
        jdbcTemplate.update(
            "UPDATE product SET status = ?::product_status WHERE serial_number = ?",
            status.getDbProductStatusType(),
            serialNumber
        );
    }
}

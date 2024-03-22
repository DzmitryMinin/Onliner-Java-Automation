package driver.entities.pojo;

import lombok.Data;

@Data
public class Product {
    private String position_id;
    private int product_id;
    private String product_key;
    private int quantity;
    private int shop_id;
}

import driver.entities.pojo.Cart;
import driver.entities.pojo.Position;
import driver.entities.pojo.Positions;
import driver.entities.pojo.Product;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObject.baseObjects.BaseTest;

import java.util.ArrayList;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.notNullValue;
import static propertyUtils.PropertyReader.getProperties;

public class ApiTests extends BaseTest{
    Product product = new Product();
    Cart cart = new Cart();

    @BeforeMethod
    public void precondition() {
        baseURI = getProperties().getProperty("urlApi");
    }

    @Test(priority = 1, description = "Check getting currency rates")
    public void getCurrency() {
        Response response = given()
                .filter(new AllureRestAssured())
                .basePath("/kurs/api/bestrate")
                .params("currency", "EUR", "type", "nbrb")
                .get();
        response.then()
                .body("amount", notNullValue())
                .body("grow", notNullValue())
                .body("delta", notNullValue())
                .body("banks", notNullValue())
                .body("scale", notNullValue())
                .statusCode(200);
    }

    @Test(priority = 2, description = "Add a product to cart")
    public void addProductToCart() {
        product.setPosition_id("1689:1137995");
        product.setProduct_id(4230055);
        product.setProduct_key("4894947004933");
        product.setQuantity(1);
        product.setShop_id(1689);
        Response response = given()
                .filter(new AllureRestAssured())
                .basePath("/cart.api/detached-cart/add")
                .contentType("application/json")
                .body(product).post();
        response.then()
                .body("cart_id", notNullValue())
                .body("expires_at", notNullValue());
        response.then().statusCode(200);
        cart = response.jsonPath().getObject("", Cart.class);
    }

    @Test(priority = 3, description = "Delete a product from cart")
    public void deleteProductFromCart() {
        Position position = new Position();
        position.setPosition_id(product.getPosition_id());
        position.setShop_id(product.getShop_id());
        position.setProduct_id(product.getProduct_id());
        Positions positions = new Positions();
        ArrayList<Position> positionList = new ArrayList<>();
        positionList.add(position);
        positions.setPositions(positionList);

        Response response = given()
                .filter(new AllureRestAssured())
                .basePath("/cart.api/detached-cart/" + cart.getCart_id())
                .contentType("application/json")
                .body(positions).delete();
        response.then().statusCode(204);
        Assert.assertTrue(response.getBody().asString().isEmpty());
    }
}

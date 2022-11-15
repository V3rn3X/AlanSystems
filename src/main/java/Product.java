import java.util.HashMap;
import java.util.Map;
import org.decimal4j.util.DoubleRounder;

public class Product {

    private static final Map<Integer, Product> productMap = new HashMap<>();
    private Type type;
    private Integer quantity;
    private Double value;

    public static Map<Integer, Product> getProductMap() {
        return productMap;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void addElement(Integer idProduct, Product test) {
        Product product = productMap.get(idProduct);

        if (product == null) {
            productMap.put(idProduct, test);
        } else {
            if (Type.PURCHASE.equals(test.getType())) {
                test.setQuantity(product.getQuantity() + test.getQuantity());
                test.setValue(product.getValue() + test.getValue());
            } else if (Type.SELL.equals(test.getType())) {
                test.setQuantity(product.getQuantity() - test.getQuantity());
                test.setValue(product.getValue() - test.getValue());
            }
            productMap.put(idProduct, test);
        }

        System.out.println("Product: " + idProduct +
                " quantity: " + test.getQuantity() +
                ", value: " + test.getValue() +
                ", ppl: " + DoubleRounder.round(test.getValue() / test.getQuantity(), 5));
    }
}

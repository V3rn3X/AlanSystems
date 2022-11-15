import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileHandling extends Component {

    public File readFile() {

        File selectedFile = null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        }
        return selectedFile;
    }

    public List<String> jsonToStingList(File file) {

        List<String> strJson = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                strJson.add(line);
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strJson;
    }

    public void jsonStringToProduct(List<String> strJson) {

        try {
            for (String line : strJson) {

                JSONParser parser = new JSONParser();
                Object object = parser.parse(line);
                JSONObject mainJsonObject = (JSONObject) object;

                Object type = mainJsonObject.get("type");
                JSONObject jsonObjectProduct = (JSONObject) mainJsonObject.get("product");
                Object id = jsonObjectProduct.get("id");
                Object quantity = jsonObjectProduct.get("quantity");
                Object value = jsonObjectProduct.get("value");
                System.out.println(object);

                Product product = new Product();
                product.setType(Type.valueOf(type.toString()));
                product.setQuantity(Integer.parseInt(quantity.toString()));
                product.setValue(Double.parseDouble(value.toString()));
                Integer idProduct = Integer.parseInt(id.toString());

                product.addElement(idProduct, product);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

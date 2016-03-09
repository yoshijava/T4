package oracle.common;
import org.json.*;
import java.io.*;

public class ConfigurableParameters {
    public static String COMMODITY;
    static {
        String content = "";
        try {
            FileReader in = new FileReader("configs/Configuration.json");
            BufferedReader buffReader = new BufferedReader(in);

            String line = "";
            while ( (line = buffReader.readLine()) != null ) {
                // i just like to add newline to make it beautiful when printing it out.
                content += line + "\n";
            }
            buffReader.close();
            JSONObject obj = new JSONObject(content);
            COMMODITY = obj.getJSONObject("configuration").getString("COMMODITY");
            System.out.println("Configuration loaded successfully.");
        }
        catch (IOException e) {
            throw new RuntimeException("I cannot find the configuration file. ");
        }
    }
}

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Execution {
    private static String protocol;
    private static String servername;
    private static int port;
    private static String directory;
    private static WebAccess webAccess;

    public static void main(String args[]) throws IOException {
        protocol = "https";
        servername = "translate.google.com";
        port = 80;
        directory = "";

        webAccess = new WebAccess(protocol, servername, port, directory);
        webAccess.selectSite();
        /*Map<Integer, List<String>> oldData = new HashMap<>();
        Map<Integer, List<String>> newData = new HashMap<>();
        oldData.put(0, new ArrayList<>());
        oldData.get(0).add("Hello");
        oldData.get(0).add("World");
        oldData.put(1, new ArrayList<>());
        oldData.get(1).add("Heute");
        oldData.get(1).add("nicht");
        String sourceLanguage = "de";
        String targetLanguage = "en";
        Translate test = new Translate();
        newData = test.translate(oldData, sourceLanguage, targetLanguage);
        System.out.println(newData.get(0).get(0) + " " + newData.get(0).get(1));
        System.out.println(newData.get(1).get(0) + " " + newData.get(1).get(1));*/
    }
}

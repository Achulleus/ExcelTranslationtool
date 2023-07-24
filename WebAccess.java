import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.*;
public class WebAccess {
    private String servername;
    private String protocol;
    private int port;
    private String directory;
    private URL url;
    private String[] arr;

    public WebAccess(String protocol, String servername, int port, String directory) throws MalformedURLException {
        this.protocol = protocol;
        this.servername = servername;
        this.port = port;
        this.directory = directory;
        url = new URL(protocol, servername, port, directory);
    }
    public void selectSite() throws IOException {
        Reader is = new InputStreamReader(url.openStream());
        BufferedReader in = new BufferedReader(is);
        for(String s; (s = in.readLine()) != null;){
            System.out.println(s);
        }
        in.close();
    }
}

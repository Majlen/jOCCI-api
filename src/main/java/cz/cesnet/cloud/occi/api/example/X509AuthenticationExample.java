package cz.cesnet.cloud.occi.api.example;

import cz.cesnet.cloud.occi.api.Client;
import cz.cesnet.cloud.occi.api.exception.CommunicationException;
import cz.cesnet.cloud.occi.api.http.HTTPClient;
import cz.cesnet.cloud.occi.api.http.auth.HTTPAuthentication;
import cz.cesnet.cloud.occi.api.http.auth.X509Authentication;
import java.net.URI;
import java.util.List;

/**
 *
 * @author Michal Kimle <kimle.michal@gmail.com>
 */
public class X509AuthenticationExample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            HTTPAuthentication authentication = new X509Authentication("/path/to/certificate.pem", "password");
            //if custom certificates are needed
            authentication.setCAPath("/path/to/certificate/directory");
            Client client = new HTTPClient(URI.create("https://localhost:1234"), authentication);

            List<URI> list = client.list();
            System.out.println("Locations:");
            for (URI uri : list) {
                System.out.println(uri);
            }
        } catch (CommunicationException ex) {
            throw new RuntimeException(ex);
        }
    }
}

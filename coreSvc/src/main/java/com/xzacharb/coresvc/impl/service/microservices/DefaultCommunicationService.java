package com.xzacharb.coresvc.impl.service.microservices;

import com.xzacharb.coresvc.infra.service.microservices.CommunicationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.*;

@Service
public class DefaultCommunicationService implements CommunicationService {
    private String INVOICES = "/invoices/";
    @Value("${invoices.services.ws}")
    private String webScrapingServiceURL;

    public String getCityInvoices(String city){
        String uri = webScrapingServiceURL+INVOICES+city;
        try {
            URL url = new URL(uri);
            System.out.println("protocol: "+url.getProtocol());
            System.out.println("host: "+url.getHost());
            System.out.println("port: "+url.getPort());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            System.out.println("kod odpovede: "+con.getResponseCode());
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "success";
    }
}

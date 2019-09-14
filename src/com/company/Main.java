package com.company;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        String url = "http://npchk.nalog.ru/ajax.html";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

//add request header
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        Scanner scan = new Scanner(System.in);
        String inn = scan.nextLine();

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dft = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dt = dft.format(now);

        String urlParameters = "inn=" + inn + "&kpp=&dt=" + dt;

// Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        Scanner responseScan = new Scanner(con.getInputStream());
        String output = responseScan.nextLine();

        responseScan.close();

        System.out.println(output);
    }
}

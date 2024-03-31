package com.peliculas.peliculas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class PeliculasApplication {

	public static void main(String[] args) {
		/*String url = "jdbc:oracle:thin:@zabe0uhpc4dpxonm_tp?TNS_ADMIN=/wallet";
         String user = "ADMIN";
        String password = "GryffoOlivares_2001";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection successful!");
            connection.close();
        } catch (SQLException e) {
            System.err.println("Connection failed!");
            e.printStackTrace();
        }*/


		SpringApplication.run(PeliculasApplication.class, args);
	}

}

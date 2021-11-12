package net.rhizomik.redefer.csv2rdf;

import net.rhizomik.redefer.csv2rdf.conf.StorageProperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class CSV2RDFApplication {

	public static void main(String[] args) {
		SpringApplication.run(CSV2RDFApplication.class, args);
	}

}

package com.tfg;

import com.tfg.conf.FileStorageProperties;
import com.tfg.services.RDFService;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.io.File;
import java.util.List;

@SpringBootApplication
public class TfgApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(TfgApplication.class, args);
		RDFService service = new RDFService();
		List<Model> models = service.createRDF(new File("./src/test/java/resources/test/Bienes_declarados_Patrimonio_mundial_de_la_UNESCO_en_España.csv"));
		for(Model model: models){
			RDFDataMgr.write(System.out, model, Lang.TURTLE);
		}
	}

}

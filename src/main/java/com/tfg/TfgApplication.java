package com.tfg;

import com.tfg.conf.StorageProperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class TfgApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(TfgApplication.class, args);

	/*	RDFService service = new RDFService();
		List<Model> models = service.createRDF(new File("./src/main/resources/csv/Bienes_declarados_Patrimonio_mundial_de_la_UNESCO_en_España.csv"));
		for(Model model: models){
			RDFDataMgr.write(System.out, model, Lang.RDFJSON);
		}
		for(Model model: models){
			RDFDataMgr.write(System.out, model, Lang.RDFXML);
		}
*/
	}

}

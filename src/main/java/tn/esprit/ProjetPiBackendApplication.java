package tn.esprit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import tn.esprit.repository.OrderRepository;
import tn.esprit.util.pdf.PDFGenerator;

@SpringBootApplication
@Configuration
@EnableScheduling
public class ProjetPiBackendApplication implements CommandLineRunner {
	
	@Autowired
	public PDFGenerator pfdGenerator ;
	

	public static void main(String[] args) {

		SpringApplication.run(ProjetPiBackendApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Debut PDF_GENERATE");
		//pfdGenerator.generatePdfReport();
	}

}

package com.bank.BankAPI;

import com.bank.BankAPI.models.accounts.Savings;
import com.bank.BankAPI.models.others.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class BankApiApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(BankApiApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		// AÑANDI LA LÓGICA DE LOS SETTER PARA MINIMOS Y MAXIMOS CON EL MONEY Y NO SE COMO VERIFICAR QUE SEA CORRECTO
		new Savings(new Money(new BigDecimal(0)),
				"QAS!",
				null,
				null,
				new Money(new BigDecimal(90))
				/*new Money(new BigDecimal(0.26))*/
		);
	}
}

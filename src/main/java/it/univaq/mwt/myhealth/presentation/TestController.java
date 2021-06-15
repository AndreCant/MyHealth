package it.univaq.mwt.myhealth.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.univaq.mwt.myhealth.business.DocumentService;
import it.univaq.mwt.myhealth.business.UserService;
import it.univaq.mwt.myhealth.domain.Invoice;
import it.univaq.mwt.myhealth.domain.User;

@Controller
@RequestMapping("test")
public class TestController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DocumentService documentService;
	
	@GetMapping("/test-view")
	public void all() {
		User user = new User();
		user.setUsername("admin");
		user.setEmail("admin@test.com");
		user.setPassword("admin123");
		userService.save(user);
	}
	
	@GetMapping("/connection")
	public void connection() {
		Invoice invoice = new Invoice();
		invoice.setTax(25);
		invoice.setCode(1000);
		documentService.saveInvoice(invoice);
	}
}

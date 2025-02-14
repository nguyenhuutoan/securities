package com.realpro.payment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/payment/checkout/course")
public class PaymentController {

	@GetMapping("/{courseName}")
	public String getCheckout(@RequestParam("discountCode") String discountCode) {
		return "payment/payment";
	}
}

package es.sm2baleares.tinglao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.sm2baleares.tinglao.exception.OrderException;
import es.sm2baleares.tinglao.model.Discount;
import es.sm2baleares.tinglao.model.Order;
import es.sm2baleares.tinglao.service.AmazonDeliveryService;
import es.sm2baleares.tinglao.service.DeliveryScoreService;

import java.util.Date;

/**
 * Created by pablo.beltran on 21/09/2016.
 */
@Controller
public class SampleController {

	@Autowired
	private AmazonDeliveryService amazonDeliveryService;

	@Autowired
	private DeliveryScoreService deliveryScoreService;

	@GetMapping("/")
	@ResponseBody
	public String home() throws OrderException {

		Order order = amazonDeliveryService.newOrder("Anal intruder", 90.25, true);
		amazonDeliveryService.addDiscount(order, new Discount("Promo fidelidad", 5.0));
		amazonDeliveryService.markSent(order, new Date());
		amazonDeliveryService.markDelivered(order, new Date());

		System.out.println("FINAL PRICE: " + order.getFinalPrice());

		return "El servicio de entregas tiene " + deliveryScoreService.getCurrentScore() + " puntos";
	}

}
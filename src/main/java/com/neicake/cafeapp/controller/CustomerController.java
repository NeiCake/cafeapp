package com.neicake.cafeapp.controller;

import com.neicake.cafeapp.domain.Coupon;
import com.neicake.cafeapp.domain.Customer;
import com.neicake.cafeapp.domain.ProductDiscount;
import com.neicake.cafeapp.service.ICouponService;
import com.neicake.cafeapp.service.ICustomerService;
import com.neicake.cafeapp.service.IProductSErvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller

public class CustomerController {

	@Autowired
	private ICustomerService customerService;
	@Autowired
	private ICouponService couponService;
	@Autowired
	private IProductSErvice productSErvice;


	@GetMapping("/customers")
	public ModelAndView getAllCustomers() {

		ModelAndView modelAndView = new ModelAndView("customers/list");
		modelAndView.addObject("customers", customerService.getAllActiveCustomers());
		return modelAndView;

	}


	@GetMapping("/newcustomer")
	public String newCustomer(Customer customer, Model model, RedirectAttributes redirectAttributes) {
		model.addAttribute("customer", customer);
		return "customers/new";
	}

	@GetMapping("customers/{id}")
	public String editCustomer(@PathVariable Long id, Model model) {
		model.addAttribute("customer", customerService.findOneById(id));
		return "customers/edit";
	}

	@PostMapping("/customers")
	public String saveCustomer(@ModelAttribute Customer customer, Model model, RedirectAttributes redirectAttributes) {

		customerService.save(customer);
		return "redirect:/customers";
	}

	@GetMapping("/customers/{id}/coupons")
	public ModelAndView listDiscountsForCustomer(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("customers/coupons/list");
		modelAndView.addObject("coupons", customerService.findOneById(id).getCoupons());
		return modelAndView;
	}

	@PostMapping("customers/{customerid}/")
	public String saveCoupon(@ModelAttribute Coupon coupon, Model model, @PathVariable Long customerid, RedirectAttributes redirectAttributes) {
		System.out.println("saving "+coupon);
		Customer toSave = customerService.findOneById(customerid);
		coupon.setCustomer(toSave);

		couponService.saveCoupon(coupon);
		return "redirect:/customers/" + customerid + "/coupons";
	}


	@GetMapping("/customers/{id}/newcoupon")
	public String newCouponPage( Model model, @PathVariable Long id, RedirectAttributes redirectAttributes) {
		Coupon coupon=new Coupon();
		model.addAttribute("coupon", coupon);
		model.addAttribute("customerid", id);
		System.out.println("sending coupon to model "+coupon);
		return "customers/coupons/new";
	}

	@PostMapping("customers/{id}/deletecoupon/{couponid}")
	public String deleteCoupon(@ModelAttribute Coupon coupon, Model model, @PathVariable("id") Long id, @PathVariable("couponid") Long couponid, RedirectAttributes redirectAttributes) {

		couponService.deleteCoupon(couponid);

		return "redirect:/customers/" + id + "/coupons";
	}

	@GetMapping("/customers/{id}/delete")
	public String deleteCustomer(@PathVariable Long id) {
		customerService.deleteCustomer(id);
		return "redirect:/customers";
	}



	@GetMapping("/json/customers")
	@ResponseBody
	public List<Customer> getCustomersJson() {
		return customerService.getAllActiveCustomers();
	}




	@GetMapping("/json/customers/{id}/coupons/{prodid}/")
	@ResponseBody
	public List<Coupon> getCoupons(@PathVariable("id") Long id, @PathVariable("prodid") Long prodid) {
		System.out.println("sending json ---");
		List<Coupon> list = customerService.findOneById(id).getCoupons();
		List<Coupon> finalList = new ArrayList<>();
		for (Coupon c : list) {
			if((c.getProductType().equals(productSErvice.findOneById(prodid).getType()))&&c.isActive()){
				finalList.add(c);
			}

		}

		return finalList;
	}
}



package com.neicake.cafeapp.controller;

import com.neicake.cafeapp.domain.Coupon;
import com.neicake.cafeapp.domain.Customer;
import com.neicake.cafeapp.service.ICouponService;
import com.neicake.cafeapp.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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



    @GetMapping("/customers")
    public ModelAndView getAllCustomers() {

        ModelAndView modelAndView = new ModelAndView("customers/list");
        modelAndView.addObject("customers", customerService.getAllCustomers());
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

        System.out.println("-------"+customer.getId()+"---------------dd");
        customerService.save(customer);
        return "redirect:/customers";
    }

    @GetMapping("/customers/{id}/coupons")
    public ModelAndView listDiscountsForCustomer(@PathVariable Long id){
        ModelAndView modelAndView=new ModelAndView("coupons/list");
        modelAndView.addObject("coupons",customerService.findOneById(id).getCoupons());
        return modelAndView;
    }

    @PostMapping("customers/{id}/")
    public String saveCoupon(@ModelAttribute Coupon coupon, Model model, @PathVariable Long id, RedirectAttributes redirectAttributes){
        Customer toSave=customerService.findOneById(id);
        coupon.setCustomer(toSave);
        couponService.saveCoupon(coupon);
        return "redirect:/customers/"+id;
    }


    @GetMapping("/customers/{id}/newcoupon")
    public String newCouponPage(@ModelAttribute Coupon coupon, Model model, @PathVariable Long id, RedirectAttributes redirectAttributes){
        model.addAttribute("coupon", coupon);
        model.addAttribute("id", id);
        return "coupons/new";
    }

    @PostMapping("customers/{id}/deletecoupon")
    public String deleteCoupon(@ModelAttribute Coupon coupon, Model model,@PathVariable Long id, RedirectAttributes redirectAttributes){
        couponService.delete(coupon);

        return "redirect:/customers/"+id;
    }
}



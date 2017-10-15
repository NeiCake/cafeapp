package com.neicake.cafeapp.controller;

import com.neicake.cafeapp.domain.Customer;
import com.neicake.cafeapp.domain.Product;
import com.neicake.cafeapp.domain.Purchase;
import com.neicake.cafeapp.service.ICustomerService;
import com.neicake.cafeapp.service.IProductSErvice;
import com.neicake.cafeapp.service.IPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import java.util.List;

@Controller
public class PurchaseController {

    @Autowired
    private IPurchaseService purchaseService;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IProductSErvice productSErvice;

    @GetMapping("purchases/{days}")
    public ModelAndView listPurchasesForNDays(@PathVariable int days, RedirectAttributes redirectAttributes){

        ModelAndView modelAndView = new ModelAndView("purchases/list");
        modelAndView.addObject("purchases",purchaseService.listAllPurchasesForLastDays(days));
        return  modelAndView;
    }

    @GetMapping("/purchases")
    public ModelAndView listPurchases(){

        ModelAndView modelAndView = new ModelAndView("purchases/list");
        modelAndView.addObject("purchases",purchaseService.listAllPurchases());
        return  modelAndView;
    }



    @GetMapping("/newpurchase")
    public String newPurchase(Model model, RedirectAttributes redirectAttributes){

        Purchase purchase=new Purchase();
        Customer c=new Customer();
        Product p=new Product();
        purchase.setCustomer(c);
        purchase.setProduct(p);
        model.addAttribute("purchase",purchase);
        List<Customer> customerList=customerService.getAllCustomers();
        model.addAttribute("customers",customerList);
        List<Product> productList=productSErvice.getAllNonExpiredProductsInStock();
        model.addAttribute("products", productList);

        return "purchases/new";

    }

    @PostMapping("/newpurchase")
    public String newPurchase(Purchase purchase){
        System.out.println(purchase);
        purchaseService.performPurchase(purchase);

        return "redirect:/purchases";
    }
}

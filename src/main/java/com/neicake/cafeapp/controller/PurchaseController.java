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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    }


    @GetMapping("purchases/newpurchase")
    public String newPurchase(Model model, RedirectAttributes redirectAttributes, Purchase purchase){
        List<Customer> customerList=customerService.getAllCustomers();
        model.addAttribute("customers",customerList);
        List<Product> productList=productSErvice.getAllNonExpiredProductsInStock();
        model.addAttribute("products", productList);


    }
}

package com.neicake.cafeapp.controller;

import com.neicake.cafeapp.domain.NonPerishableProduct;
import com.neicake.cafeapp.domain.PerishableProduct;
import com.neicake.cafeapp.domain.Product;
import com.neicake.cafeapp.service.IProductSErvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

@Controller
public class BaseController {
    @Autowired
    IProductSErvice productService;

    @RequestMapping(value = "/products",method = RequestMethod.GET)
    public ModelAndView getAllProducts(){

        ModelAndView modelAndView = new ModelAndView("products/list");
        modelAndView.addObject("products",productService.getAllProducts());
        return modelAndView;

    }


    @PostMapping("/products")
    public String saveProduct(@ModelAttribute Product product, Errors errors, Model model){

        return "";
    }

    @GetMapping("/newproduct")
    public String newProduct(Product product, Model model, RedirectAttributes redirectAttributes) {

        model.addAttribute("products", product);
        Date date=null;
        model.addAttribute("date",date);


        return "products/new";
    }


    @PostMapping("/newproduct")
    public String saveProduct(@ModelAttribute Product product, @ModelAttribute  Date date, Model mode, RedirectAttributes redirectAttributes){
        if(date!=null) {
            PerishableProduct productToSave=new PerishableProduct(product);
            productToSave.setExpirationDate(date);
            productService.save(productToSave);

        }

        else{
            System.out.println("DATE WAS NULL");
            NonPerishableProduct productToSave=new NonPerishableProduct(product);
            productService.save(productToSave);
        }


        return "redirect:/products";
    }
}

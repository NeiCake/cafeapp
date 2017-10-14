package com.neicake.cafeapp.controller;

import com.neicake.cafeapp.domain.Product;
import com.neicake.cafeapp.service.IProductSErvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductController {
    @Autowired
    IProductSErvice productService;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ModelAndView getAllProducts() {

        ModelAndView modelAndView = new ModelAndView("products/list");
        modelAndView.addObject("products", productService.getAllProducts());
        return modelAndView;

    }


    @GetMapping("/newproduct")
    public String newProduct(Product product, Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("products", product);
        return "products/new";
    }


    @PostMapping("/products")
    public String saveProduct(@ModelAttribute Product product, Model model, RedirectAttributes redirectAttributes) {

        productService.save(product);
        return "redirect:/products";
    }


    @GetMapping("products/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findOneById(id));
        return "products/edit";
    }
}

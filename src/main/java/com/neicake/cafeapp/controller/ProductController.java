package com.neicake.cafeapp.controller;

import com.neicake.cafeapp.domain.Coupon;
import com.neicake.cafeapp.domain.Customer;
import com.neicake.cafeapp.domain.Product;
import com.neicake.cafeapp.domain.ProductDiscount;
import com.neicake.cafeapp.service.IProductDiscountService;
import com.neicake.cafeapp.service.IProductSErvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private IProductSErvice productService;
    @Autowired
    private IProductDiscountService productDiscountService;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ModelAndView getAllProducts() {

        ModelAndView modelAndView = new ModelAndView("products/list");
        List<Product> list= productService.getAllProducts();
        productService.completeListOfProductDiscountsWithDiscountBoolean(list);
        modelAndView.addObject("products",list);
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



////////////////

    @GetMapping("/products/{id}/discounts")
    public ModelAndView listDiscountsForProduct(@PathVariable Long id){
        ModelAndView modelAndView=new ModelAndView("products/discounts/list");
        modelAndView.addObject("discounts",productService.findOneById(id).getProductDiscount());
        return modelAndView;
    }

    @PostMapping("/products/{id}/")
    public String saveCoupon(@ModelAttribute ProductDiscount productDiscount, Model model, @PathVariable Long id, RedirectAttributes redirectAttributes){
        Product toSave=productService.findOneById(id);
        productDiscount.setProduct(toSave);
        productDiscountService.saveProductDiscount(productDiscount);
        return "redirect:/products/";
    }


    @GetMapping("/products/{id}/newdiscount")
    public String newProductDiscount(@ModelAttribute ProductDiscount discount, Model model, @PathVariable Long id, RedirectAttributes redirectAttributes){
        model.addAttribute("discount", discount);
        model.addAttribute("id", id);
        return "products/discounts/new";
    }


    @GetMapping("/products/{id}/deletediscount")
    public String unDiscountProduct(@PathVariable Long id){
        ProductDiscount discount=productService.findOneById(id).getProductDiscount();
        productDiscountService.delete(discount);
        return "redirect:/products";
    }
}

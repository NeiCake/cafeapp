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
        List<Product> list = productService.getAllProducts();
        productService.completeListOfProductDiscountsWithDiscountBoolean(list);
        modelAndView.addObject("products", list);
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


    @GetMapping("products/good")
    public String goodProducts(Model model) {
        List<Product> list = productService.getAllNonExpiredProductsInStock();
        productService.completeListOfProductDiscountsWithDiscountBoolean(list);
        model.addAttribute("products", list);
        return "products/list";
    }

    @GetMapping("products/bad/expired")
    public String badProductsExpired(Model model) {

        List<Product> list = productService.getAllExpiredProducts();
        productService.completeListOfProductDiscountsWithDiscountBoolean(list);
        model.addAttribute("products", list);
        return "products/list";
    }

    @GetMapping("products/bad/nostock")
    public String badProductsNoStock(Model model) {
        List<Product> list = productService.getAllStockZeroProducts();
        productService.completeListOfProductDiscountsWithDiscountBoolean(list);
        model.addAttribute("products", list);

        return "products/list";
    }
////////////////

    @GetMapping("/products/{id}/discounts")
    public ModelAndView listDiscountsForProduct(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("products/discounts/list");
        modelAndView.addObject("discounts", productService.findOneById(id).getProductDiscount());
        return modelAndView;
    }

    @PostMapping("/products/{prodid}/")
    public String saveProductDiscount(@ModelAttribute ProductDiscount productDiscount, Model model, @PathVariable Long prodid, RedirectAttributes redirectAttributes) {
        Product toSave = productService.findOneById(prodid);
        productDiscount.setProduct(toSave);
        System.out.println("the productDiscount you are trying to save has the ID = "+productDiscount.getId());
        productDiscountService.saveProductDiscount(productDiscount);
        return "redirect:/products/";
    }


    @GetMapping("/products/{id}/newdiscount")
    public String newProductDiscount(@ModelAttribute ProductDiscount discount, Model model, @PathVariable Long id, RedirectAttributes redirectAttributes) {
        model.addAttribute("discount", discount);
        model.addAttribute("id", id);
        return "products/discounts/new";
    }


    @GetMapping("/products/{id}/deletediscount")
    public String unDiscountProduct(@PathVariable Long id) {
        ProductDiscount discount = productService.findOneById(id).getProductDiscount();
        productDiscountService.delete(discount);
        return "redirect:/products";
    }

    @GetMapping("json/products/{id}/discount")
	@ResponseBody
	public ProductDiscount getDiscount(@PathVariable Long id){
    	return productService.findOneById(id).getProductDiscount();
    }

	@GetMapping("/json/products")
	@ResponseBody
	public List<Product> getProducts(){
		return productService.getAllNonExpiredProductsInStock();
	}
}

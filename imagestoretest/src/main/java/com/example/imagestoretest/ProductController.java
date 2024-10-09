package com.example.imagestoretest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.imagestoretest.cart.CartItem;
import com.example.imagestoretest.cart.CartService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartService cartService;

    // TO SEND TO PRODUCT_LIST.HTML PAGE
    // @GetMapping("/products")
    // public String showProducts(Model model) {
    //     List<Product> products = productService.getAllProducts();
    //     model.addAttribute("products", products);
    //     return "product_list";
    // }




    @GetMapping("/products")
public String showProducts(@RequestParam(required = false) String category, Model model) {
    List<Product> products;
    if (category != null && !category.isEmpty()) {
        products = productService.getProductsByCategory(category);
    } else {
        products = productService.getAllProducts();
    }
    List<CartItem> cartItems = cartService.getCartItems(); // Fetch cart items
    model.addAttribute("products", products);
    model.addAttribute("cartItems", cartItems); // Add cart items to the model
    return "product_list";
}

    // @GetMapping("/products")
    // public String showProducts(@RequestParam(required = false) String category, Model model) {
    //     List<Product> products;
    //     if (category != null && !category.isEmpty()) {
    //         products = productService.getProductsByCategory(category);
    //     } else {
    //         products = productService.getAllProducts();
    //     }
    //     model.addAttribute("products", products);
    //     return "product_list";
    // }


    // TO ADD NEW PRODUCT IN LIST
    @GetMapping("/products/new")
    public String newProductForm() {
        return "new_product";
    }


    // TO SAVE PRODUCTS IN LIST
    @PostMapping("/products")
    public String saveProduct(@RequestParam("name") String name,
                              @RequestParam("description") String description,
                              @RequestParam("category") String category,
                              @RequestParam("price") double price,
                              @RequestParam("file") MultipartFile file) {
        try {
            Product product = new Product();
            product.setName(name);
            product.setDescription(description);
            product.setCategory(category);
            product.setPrice(price);
            product.setImage(file.getBytes());
            productService.saveProduct(product);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/products";
    }


    // TO GET IMAGES FOR PRODUCTS
    @GetMapping("/products/image/{id}")
@ResponseBody
public byte[] getProductImage(@PathVariable String id) {
    Optional<Product> product = productService.getProductById(id);
    return product.map(Product::getImage).orElse(null);
}

 
// TO SUBMIT RATE TO THE PRODUCTS
@PostMapping("/products/{id}/rate")
public ResponseEntity<?> rateProduct(@PathVariable String id, @RequestBody Map<String, Integer> request) {
    int rating = request.get("rating");

    // Fetch the product by ID, update its rating, and save it
    Optional<Product> productOpt = productRepository.findById(id);
    if (productOpt.isPresent()) {
        Product product = productOpt.get();
        product.setRating(rating); // Assuming your Product class has a rating field
        productRepository.save(product);
        return ResponseEntity.ok().build(); // Success response
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Product not found
    }
}



@GetMapping("/products/{id}")
public String getProductDetails(@PathVariable("id") String id, Model model) {
    Optional<Product> product = productService.getProductById(id);
    if (product.isPresent()) {
        model.addAttribute("product", product.get());
        return "product-details"; // Thymeleaf template name for product details
    } else {
        return "404"; // Return a 404 page or redirect if product not found
    }
}


 // Endpoint to fetch related products
 @GetMapping("/products/{id}/related")
 @ResponseBody
 public RelatedProductsResponse getRelatedProducts(@PathVariable String id) {
     List<Product> relatedProducts = productService.getRelatedProducts(id);
     return new RelatedProductsResponse(relatedProducts, true);
 }


//  TO CHECK DETAILS OF PRODUCTS ON CLICK IMAGE OF THE PRODUCTS
//  @GetMapping("/details/{id}")
//  public String getProductDetailsPage(@PathVariable String id, Model model) {
//      Optional<Product> product = productService.getProductById(id);
//      if (product.isPresent()) {
//          model.addAttribute("product", product.get());
//          return "product-details"; // Return the view name for the product details page
//      } else {
//          return "404"; // Return a 404 page or redirect if product not found
//      }
//  }




}


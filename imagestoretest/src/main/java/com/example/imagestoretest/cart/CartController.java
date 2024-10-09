// package com.example.imagestoretest.cart;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.*;

// import com.example.imagestoretest.Product;
// import com.example.imagestoretest.ProductService;

// import java.util.Optional;

// @Controller
// @RequestMapping("/cart")
// public class CartController {

//     @Autowired
//     private CartService cartService;

//     @Autowired
//     private ProductService productService;

//     // Show the cart page
//     @GetMapping
//     public String showCart(Model model) {
//         model.addAttribute("cartItems", cartService.getCartItems());
//         model.addAttribute("totalPrice", cartService.calculateTotalPrice());
//         return "cart"; // cart.html (Thymeleaf template)
//     }

//     // Add product to cart
//     @PostMapping("/add")
//     public String addToCart(@RequestParam String productId, @RequestParam int quantity) {
//         Optional<Product> product = productService.getProductById(productId);
//         if (product.isPresent()) {
//             cartService.addProductToCart(product.get(), quantity);
//         }
//         return "redirect:/cart";  // Redirect to cart after adding product
//     }

//     // Update cart item quantity
//     @PostMapping("/update")
//     public String updateCart(@RequestParam String productId, @RequestParam int quantity) {
//         cartService.updateCartItemQuantity(productId, quantity);
//         return "redirect:/cart";
//     }

//     // Remove item from cart
//     @PostMapping("/remove")
//     public String removeFromCart(@RequestParam String productId) {
//         cartService.removeProductFromCart(productId);
//         return "redirect:/cart";
//     }

//     // Clear cart
//     @PostMapping("/clear")
//     public String clearCart() {
//         cartService.clearCart();
//         return "redirect:/cart";
//     }

// }


package com.example.imagestoretest.cart;

import com.example.imagestoretest.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public String showCart(Model model) {
        model.addAttribute("cartItems", cartService.getCartItems());
        model.addAttribute("totalPrice", cartService.calculateTotalPrice());
        return "cart";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam String productId, @RequestParam int quantity) {
        cartService.addProductToCart(productService.getProductById(productId).orElseThrow(), quantity);
        return "redirect:/cart";
    }

    @PostMapping("/checkout")
    public String checkout(@RequestParam String buyerName, @RequestParam String buyerAddress ,@RequestParam String buyerMobile) {
        cartService.checkout(buyerName, buyerAddress, buyerMobile);
        return "checkout-success";
    }
   
    @PostMapping("/update")
    public String updateCart(@RequestParam String productId, @RequestParam int quantity) {
        cartService.updateCartItemQuantity(productId, quantity);
        return "redirect:/cart";
    }


    
}

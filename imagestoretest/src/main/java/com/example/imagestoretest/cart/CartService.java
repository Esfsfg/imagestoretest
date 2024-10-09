// package com.example.imagestoretest.cart;

// import org.springframework.stereotype.Service;

// import com.example.imagestoretest.Product;

// import java.util.ArrayList;
// import java.util.List;

// @Service
// public class CartService {

//     private List<CartItem> cartItems = new ArrayList<>();

//     public List<CartItem> getCartItems() {
//         return cartItems;
//     }

//     public void addProductToCart(Product product, int quantity) {
//         // Check if the product is already in the cart
//         for (CartItem item : cartItems) {
//             if (item.getProduct().getId().equals(product.getId())) {
//                 // If product already in the cart, increase quantity
//                 item.setQuantity(item.getQuantity() + quantity);
//                 return;
//             }
//         }
//         // If not in the cart, add as a new CartItem
//         cartItems.add(new CartItem(product, quantity));
//     }

//     public void updateCartItemQuantity(String productId, int quantity) {
//         for (CartItem item : cartItems) {
//             if (item.getProduct().getId().equals(productId)) {
//                 item.setQuantity(quantity);
//             }
//         }
//     }

//     public void removeProductFromCart(String productId) {
//         cartItems.removeIf(item -> item.getProduct().getId().equals(productId));
//     }

//     public double calculateTotalPrice() {
//         return cartItems.stream()
//                 .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
//                 .sum();
//     }

//     public void clearCart() {
//         cartItems.clear();
//     }
// }
package com.example.imagestoretest.cart;

import com.example.imagestoretest.Product;
import com.example.imagestoretest.order.Order;
import com.example.imagestoretest.order.OrderItem;
import com.example.imagestoretest.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private OrderRepository orderRepository;

    private List<CartItem> cartItems = new ArrayList<>();

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void addProductToCart(Product product, int quantity) {
        for (CartItem item : cartItems) {
            if (item.getProduct().getId().equals(product.getId())) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        cartItems.add(new CartItem(product, quantity));
    }

    public void updateCartItemQuantity(String productId, int quantity) {
        for (CartItem item : cartItems) {
            if (item.getProduct().getId().equals(productId)) {
                item.setQuantity(quantity);
            }
        }
    }

    public void removeProductFromCart(String productId) {
        cartItems.removeIf(item -> item.getProduct().getId().equals(productId));
    }

    public double calculateTotalPrice() {
        return cartItems.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }

    public void clearCart() {
        cartItems.clear();
    }

    public Order checkout(String buyerName, String buyerAddress, String buyerMobile) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            orderItems.add(new OrderItem(cartItem.getProduct(), cartItem.getQuantity()));
        }
        Order order = new Order(buyerName, buyerAddress, buyerMobile, orderItems, calculateTotalPrice());
        orderRepository.save(order);
        clearCart();  // Clear the cart after checkout
        return order;
    }
    
}

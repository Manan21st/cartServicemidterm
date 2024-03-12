package com.nj_projects.cartservice.controllers;

import com.nj_projects.cartservice.models.Cart;
import com.nj_projects.cartservice.services.CartService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
public class CartController {
    private CartService cartService;

    public CartController(CartService cartService){
        this.cartService=cartService;
    }

    @GetMapping("/carts")
    public List<Cart> getAllCart(){
        return cartService.getAllCart();
    }


    @GetMapping("/carts/{Id}")
    public Cart getSingleCart(@PathVariable("Id") Long Id){
        return cartService.getSingleCart(Id);
    }

    @GetMapping("/carts?startdate=start&enddate=end")
    public List<Cart> getByDateRange(@RequestParam(name="startdate") LocalDate start,@RequestParam(name="enddate") LocalDate end){
        return cartService.getByDateRange(start,end);
    }

    @GetMapping("/carts/user/{userId}")
    public List<Cart> getUserCart(@PathVariable("userId") Long userId){
        return cartService.getUserCart(userId);
    }

    @PostMapping("/carts")
    public Cart addNewCart(@RequestBody Cart cart){
        return cartService.addNewCart(cart);
    }

    @PutMapping("/carts/{Id}")
    public Cart updateCart(@PathVariable("Id") Long Id, @RequestBody Cart cart){
        return cartService.updateCart(Id,cart);
    }

    @DeleteMapping("/carts/{Id}")
    public Cart deleteCart(@PathVariable("Id") Long Id){
        return cartService.deleteCart(Id);
    }
}

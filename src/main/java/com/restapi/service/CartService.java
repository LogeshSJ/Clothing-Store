package com.restapi.service;

import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.AppUser;
import com.restapi.model.Cart;
import com.restapi.model.Cloth;
import com.restapi.repository.CartRepository;
import com.restapi.repository.ClothRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.CartRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClothRepository clothRepository;

    public List<Cart> findUserCart(Long userId) {
        List<Cart> cart = (List<Cart>) cartRepository.findUserCart(userId)
                .orElseThrow(() -> new ResourceNotFoundException("cart", "userId", userId));
        return cart;
    }

    @Transactional
    public List<Cart> addToCart(CartRequest cartRequest) {
        AppUser appUser = userRepository.findById(cartRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("userId", "userId",
                        cartRequest.getUserId()));

        Cloth cloth = clothRepository.findById(cartRequest.getClothId())
                .orElseThrow(() -> new ResourceNotFoundException("clothId", "clothId",
                        cartRequest.getClothId()));

        Optional<List<Cart>> cartOptional = cartRepository.findUserCart(cartRequest.getUserId());

        if (cartOptional.isPresent()) {
            boolean isPresent = false;
            for (Cart cart : cartOptional.get()) {
                if (cart.getCloth().getId().equals(cartRequest.getClothId())) {
                    cart.setCount(cartRequest.getCount());
                    cartRepository.save(cart);
                    isPresent = true;
                }
            }
            if (!isPresent) {
                Cart cart = new Cart();
                cart.setAppUser(appUser);
                cart.setCloth(cloth);
                cart.setCount(cartRequest.getCount());
                cartRepository.save(cart);
            }
        } else {
            Cart cart = new Cart();
            cart.setAppUser(appUser);
            cart.setCloth(cloth);
            cart.setCount(cartRequest.getCount());
            cartRepository.save(cart);
        }
        return findUserCart(cartRequest.getUserId());
    }
    @Transactional
    public List<Cart> deleteClothFromCart(Long userId, Long clothId) {
        Optional<Cart> cartOptional = cartRepository.findById(clothId);
        if (cartOptional.isPresent() && cartOptional.get().getAppUser().getId().equals(userId)) {
            cartRepository.deleteById(clothId);
        }
        return findUserCart(userId);
    }
}


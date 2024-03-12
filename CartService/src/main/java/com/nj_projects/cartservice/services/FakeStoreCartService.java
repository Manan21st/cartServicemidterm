package com.nj_projects.cartservice.services;

import com.nj_projects.cartservice.dtos.FakeStoreDto;
import com.nj_projects.cartservice.models.Cart;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class FakeStoreCartService implements CartService {
    private RestTemplate restTemplate= new RestTemplate();


    @Override
    public List<Cart> getAllCart() {
        String url="https://fakestoreapi.com/carts";
        ParameterizedTypeReference<List<FakeStoreDto>> responseType= new ParameterizedTypeReference<>(){};

        List<FakeStoreDto> fakeStoreDtos = restTemplate.exchange(
                url,
                org.springframework.http.HttpMethod.GET,
                null,
                responseType
        ).getBody();

        List<Cart> carts=new ArrayList<>();
        if(fakeStoreDtos==null){
            return new ArrayList<>();
        }

        for(FakeStoreDto fakeStoreDto:fakeStoreDtos){
            Cart cart= new Cart();
            cart.setId(fakeStoreDto.getId());
            cart.setUserId(fakeStoreDto.getUserId());
            cart.setDate(fakeStoreDto.getDate());
            cart.setProducts(fakeStoreDto.getProducts());
            carts.add(cart);
        }

        return carts;
    }

    @Override
    public Cart getSingleCart(Long Id) {
        String url="https://fakestoreapi.com/carts/"+Id;
        FakeStoreDto fakeStoreDto= restTemplate.getForObject(
                url,
                FakeStoreDto.class
        );
        if(fakeStoreDto==null){
            return new Cart();
        }
        Cart cart= new Cart();
        cart.setId(fakeStoreDto.getId());
        cart.setUserId(fakeStoreDto.getUserId());
        cart.setDate(fakeStoreDto.getDate());
        cart.setProducts(fakeStoreDto.getProducts());

        return cart;
    }

    @Override
    public List<Cart> getByDateRange(LocalDate start, LocalDate end) {
        String url="https://fakestoreapi.com/carts?startdate="+start+"&enddate="+end;
        ParameterizedTypeReference<List<FakeStoreDto>> responseType= new ParameterizedTypeReference<>(){};
        List<FakeStoreDto> fakeStoreDtos = restTemplate.exchange(
                url,
                org.springframework.http.HttpMethod.GET,
                null,
                responseType
        ).getBody();

        List<Cart> carts=new ArrayList<>();
        if(fakeStoreDtos==null){
            return new ArrayList<>();
        }

        for(FakeStoreDto fakeStoreDto:fakeStoreDtos){

                Cart cart = new Cart();
                cart.setId(fakeStoreDto.getId());
                cart.setUserId(fakeStoreDto.getUserId());
                cart.setDate(fakeStoreDto.getDate());
                cart.setProducts(fakeStoreDto.getProducts());
                carts.add(cart);
        }
        return carts;
    }

    @Override
    public List<Cart> getUserCart(Long userId) {
        String url="https://fakestoreapi.com/carts/user/"+userId;

        ParameterizedTypeReference<List<FakeStoreDto>> responseType= new ParameterizedTypeReference<>(){};

        List<FakeStoreDto> fakeStoreDtos = restTemplate.exchange(
                url,
                org.springframework.http.HttpMethod.GET,
                null,
                responseType
        ).getBody();

        List<Cart> carts=new ArrayList<>();
        if(fakeStoreDtos==null){
            return new ArrayList<>();
        }

        for(FakeStoreDto fakeStoreDto:fakeStoreDtos){
            Cart cart= new Cart();
            cart.setId(fakeStoreDto.getId());
            cart.setUserId(fakeStoreDto.getUserId());
            cart.setDate(fakeStoreDto.getDate());
            cart.setProducts(fakeStoreDto.getProducts());
            carts.add(cart);
        }
        return carts;
    }

    @Override
    public Cart addNewCart(Cart cart) {
        String url="https://fakestoreapi.com/carts";

        FakeStoreDto fakeStoreDto= new FakeStoreDto();
        fakeStoreDto.setId(cart.getId());
        fakeStoreDto.setUserId(cart.getUserId());
        fakeStoreDto.setDate(cart.getDate());
        fakeStoreDto.setProducts(cart.getProducts());

        restTemplate.postForObject(
                url,
                fakeStoreDto,
                FakeStoreDto.class
        );
        return cart;
    }

    @Override
    public Cart updateCart(Long Id, Cart cart) {
        String url="https://fakestoreapi.com/carts/"+Id;
        FakeStoreDto fakeStoreDto= new FakeStoreDto();

        fakeStoreDto.setUserId(cart.getUserId());
        fakeStoreDto.setDate(cart.getDate());
        fakeStoreDto.setProducts(cart.getProducts());

        restTemplate.put(
                url,
                fakeStoreDto
        );
        return cart;
    }

    @Override
    public Cart deleteCart(Long Id) {
        Cart cart = getSingleCart(Id);
        restTemplate.delete("https://fakestoreapi.com/carts/"+Id);
        return cart;
    }
}

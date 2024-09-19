package com.cartService.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.cartService.clients.ProductClient;
import com.cartService.clients.UserClient;
import com.cartService.entities.CartItem;
import com.cartService.exception.CartNotFoundException;
import com.cartService.exception.ProductNotFoundException;
import com.cartService.exception.UserNotFoundException;
import com.cartService.payload.CartItemDto;
import com.cartService.payload.CartItemUpdateRequest;
import com.cartService.payload.ProductDto;
import com.cartService.payload.UserDto;
import com.cartService.repositories.CartItemRepository;
import com.cartService.services.CartItemService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CartItemServiceImpl implements CartItemService {
	
	private final CartItemRepository cartItemRepository;
	private final ModelMapper modelMapper;
	private final UserClient userClient;
	private final ProductClient productClient;

	@Override
	public CartItemDto createNewCart(CartItemDto cartItemDto) {
		
		CartItem map = modelMapper.map(cartItemDto, CartItem.class);
		ProductDto product = productClient.getProductbyId(cartItemDto.getProductId());
		
		if (product== null) {
			throw new ProductNotFoundException("product not found");
		}
		UserDto user = userClient.getUserById(cartItemDto.getUserId());
		
		if (user==null) {
			throw new UserNotFoundException("User not found");
		}
		CartItem save = cartItemRepository.save(map);
		return modelMapper.map(save, CartItemDto.class);
	}
	
	
	@Override
    public CartItemDto getCartItemById(Long itemId) {
    	
    	CartItem cart = cartItemRepository.findById(itemId).orElseThrow(()-> new CartNotFoundException("Cart not found with this id : "+itemId));

    	CartItemDto cartItemDTO = modelMapper.map(cart, CartItemDto.class);
    	UserDto userDto = userClient.getUserById(cart.getUserId());
    	ProductDto productDto = productClient.getProductbyId(cart.getProductId());
    	cartItemDTO.setProductDto(productDto);
    	cartItemDTO.setUserDto(userDto);
    	return cartItemDTO;
     }

    @Override
    public List<CartItemDto> getAllCartDetails() {
    	List<CartItem> all = cartItemRepository.findAll();
		return all.stream()
				  .map(cart->{
					  CartItemDto map = modelMapper.map(cart, CartItemDto.class);
					  ProductDto productDto = productClient.getProductbyId(cart.getProductId());
					  UserDto userDto = userClient.getUserById(cart.getUserId());
					  
					  map.setProductDto(productDto);
					  map.setUserDto(userDto);
					  return map;
					  
				  })
				  .collect(Collectors.toList());
    	
    }
    
    @Override
    public CartItemDto updateCartDetails(Long itemId, CartItemUpdateRequest updateDto) {
        CartItem cart = cartItemRepository.findById(itemId)
            .orElseThrow(() -> new CartNotFoundException("Cart not found with this id: " + itemId));
        
        // Update cart item
        cart.setQuantity(cart.getQuantity());
        cart.setOrderId(cart.getOrderId());
        
        CartItem updatedCart = cartItemRepository.save(cart);
        
        // Update product using Feign client
        ProductDto updatedProductDto = productClient.updateByProductId(cart.getProductId(), updateDto.getProductDto());
        
        // Update user using Feign client
        UserDto updatedUserDto = userClient.updateByUserId(cart.getUserId(),
        											       updateDto.getUserDto());
        
        CartItemDto resultDto = modelMapper.map(updatedCart, CartItemDto.class);
        resultDto.setProductDto(updatedProductDto);
        resultDto.setUserDto(updatedUserDto);
        
        return resultDto;
    }


//    @Override
//    public CartItemDto updateCartDetails(Long itemId, CartItemUpdateRequest request) {
//    		 
//        CartItem cart = cartItemRepository.findById(itemId)
//                .orElseThrow(() -> new CartNotFoundException("Cart not found with this id: " + itemId));
//        
//        CartItemDto cartItemDto = request.getCartItemDto();
//        ProductDto productDto = request.getProductDto();
//        UserDto userDto = request.getUserDto();
//        
//        if (cartItemDto == null) {
//            throw new NullPointerException("cartItemDto is null");
//        }
//        if (userDto == null) {
//            throw new NullPointerException("userDto is null");
//        }
//        if (productDto == null) {
//            throw new NullPointerException("productDto is null");
//        }
//        // Update Product
//        ProductDto updatedProduct = productClient.updateByProductId(cartItemDto.getProductId(),
//                new ProductDto(
//                	 productDto.getProductId(),
//                	 productDto.getName(),
//                	 productDto.getCategory(),
//                	 productDto.getPrice(),
//                	 productDto.getDescription(),
//                	 productDto.getStockQuantity()
//                ));
//        
//        // Update User
//        UserDto updatedUser = userClient.updateByUserId(cartItemDto.getUserId(),
//                new UserDto(
//                		userDto.getUserId(),
//                        userDto.getName(),
//                        userDto.getEmail(),
//                        userDto.getAddress(),
//                        userDto.getPhoneNumber()
//                ));
//        
//        // Update Cart
//        cart.setItemId(cartItemDto.getItemId());
////        cart.setProductId(cartItemDto.getProductId());
//        cart.setUserId(cartItemDto.getUserId());
//        cart.setQuantity(cartItemDto.getQuantity());
//        
//        CartItem updatedCart = cartItemRepository.save(cart);
//        CartItemDto updatedCartDto = modelMapper.map(updatedCart, CartItemDto.class);
//        updatedCartDto.setProductDto(updatedProduct);
//        updatedCartDto.setUserDto(updatedUser);
//        
//        return updatedCartDto;
//    }


	@Override
	public CartItemDto deleteCart(Long itemId) {
		CartItem cart = cartItemRepository.findById(itemId).orElseThrow(()-> new CartNotFoundException("cart not found eith this id :"+itemId));
		CartItemDto map = modelMapper.map(cart, CartItemDto.class);
		ProductDto productDto = productClient.deleteProductbyId(cart.getProductId());
		UserDto userDto = userClient.deleteByUserId(cart.getUserId());
		map.setProductDto(productDto);
		map.setUserDto(userDto);
		
		cartItemRepository.delete(cart);
		return map;
	}


	@Override
	public CartItemDto updateIsOrderedStatus(Long orderId) {
		 CartItem cartItem = cartItemRepository.findByOrderId(orderId);
		cartItem.setOrdered(true);
		CartItem save = cartItemRepository.save(cartItem);
		return modelMapper.map(save, CartItemDto.class);
	}
	
 

}

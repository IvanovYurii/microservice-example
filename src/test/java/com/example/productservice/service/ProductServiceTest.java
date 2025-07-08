package com.example.productservice.service;

import com.example.productservice.dto.ProductRequestDto;
import com.example.productservice.dto.ProductResponseDto;
import com.example.productservice.exception.ProductNotFoundException;
import com.example.productservice.mapper.ProductMapper;
import com.example.productservice.model.Product;
import com.example.productservice.repository.ProductRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addProduct_ShouldSaveAndReturnDto() {
        ProductRequestDto requestDto = new ProductRequestDto();
        // Заповни потрібні поля requestDto тут

        Product productEntity = new Product();
        // Заповни поля productEntity, які потрібні

        Product savedProduct = new Product();
        // Заповни поля savedProduct (можливо id після збереження)

        ProductResponseDto responseDto = new ProductResponseDto();
        // Заповни поля responseDto

        when(productMapper.toEntity(requestDto)).thenReturn(productEntity);
        when(productRepository.save(productEntity)).thenReturn(savedProduct);
        when(productMapper.toDto(savedProduct)).thenReturn(responseDto);

        ProductResponseDto result = productService.addProduct(requestDto);

        assertNotNull(result);
        assertEquals(responseDto, result);

        verify(productMapper).toEntity(requestDto);
        verify(productRepository).save(productEntity);
        verify(productMapper).toDto(savedProduct);
    }

    @Test
    void getAllProducts_ShouldReturnListOfDtos() {
        Product product1 = new Product();
        Product product2 = new Product();

        ProductResponseDto dto1 = new ProductResponseDto();
        ProductResponseDto dto2 = new ProductResponseDto();

        when(productRepository.findAll()).thenReturn(List.of(product1, product2));
        when(productMapper.toDto(product1)).thenReturn(dto1);
        when(productMapper.toDto(product2)).thenReturn(dto2);

        List<ProductResponseDto> result = productService.getAllProducts();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(dto1));
        assertTrue(result.contains(dto2));
    }

    @Test
    void getProductById_ShouldReturnDto_WhenProductExists() {
        Long id = 1L;
        Product product = new Product();
        ProductResponseDto dto = new ProductResponseDto();

        when(productRepository.findById(id)).thenReturn(Optional.of(product));
        when(productMapper.toDto(product)).thenReturn(dto);

        ProductResponseDto result = productService.getProductById(id);

        assertNotNull(result);
        assertEquals(dto, result);
    }

    @Test
    void getProductById_ShouldThrow_WhenProductNotFound() {
        Long id = 999L;
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> {
            productService.getProductById(id);
        });
    }
}

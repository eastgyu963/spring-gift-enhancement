package gift.service.product;

import gift.dto.product.ProductRequestDto;
import gift.dto.product.ProductResponseDto;
import gift.entity.Product;
import gift.exception.NameHasKakaoException;
import gift.exception.notfound.ProductNotFoundException;
import gift.repository.product.ProductJpaRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

  private final ProductJpaRepository repository;

  public ProductServiceImpl(ProductJpaRepository repository) {
    this.repository = repository;
  }

  public List<ProductResponseDto> findAllProduct() {
    List<Product> allProduct = repository.findAll();
    List<ProductResponseDto> responseDtoList = new ArrayList<>();
    for (Product product : allProduct) {
      ProductResponseDto responseDto = new ProductResponseDto(product);
      responseDtoList.add(responseDto);
    }
    return responseDtoList;
  }

  public ProductResponseDto findProductById(Long id) {
    return repository.findById(id)
        .map(ProductResponseDto::new)
        .orElseThrow(() -> new ProductNotFoundException("product가 없습니다."));
  }

  public ProductResponseDto createProduct(ProductRequestDto requestDto) {
    Product checkProduct = new Product(requestDto.getName(), requestDto.getPrice(),
        requestDto.getImageUrl());
    if (checkProduct.isNameHasWord("카카오") && !requestDto.getMdOk()) {
      throw new NameHasKakaoException("상품 이름에 '카카오'가 포함되어 있습니다. 담당 MD와 협의가 필요합니다.");
    }
    Product product = repository.save(
        new Product(requestDto.getName(), requestDto.getPrice(),
            requestDto.getImageUrl()));
    return new ProductResponseDto(product);
  }

  @Transactional
  public ProductResponseDto updateProduct(Long id, ProductRequestDto requestDto) {
    Product product = repository.findById(id)
        .orElseThrow(() -> new ProductNotFoundException("product가 없습니다."));
    if (product.isNameHasWord("카카오") && !requestDto.getMdOk()) {
      throw new NameHasKakaoException("상품 이름에 '카카오'가 포함되어 있습니다. 담당 MD와 협의가 필요합니다.");
    }
    product.update(requestDto.getName(), requestDto.getPrice(), requestDto.getImageUrl());
    return new ProductResponseDto(id, product.getName(), product.getPrice(),
        product.getImageUrl());
  }

  public void deleteProduct(Long id) {
    repository.deleteById(id);
  }
}

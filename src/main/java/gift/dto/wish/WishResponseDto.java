package gift.dto.wish;

import gift.entity.Product;
import gift.entity.Wish;

public class WishResponseDto {

  private Long id;
  private Product product;
  private Long quantity;

  public WishResponseDto(Long id, Product product, Long quantity) {
    this.id = id;
    this.product = product;
    this.quantity = quantity;
  }

  public WishResponseDto(Wish wish) {
    this(wish.getId(), wish.getProduct(), wish.getQuantity());
  }

  public Long getId() {
    return id;
  }

  public Product getProduct() {
    return product;
  }

  public Long getQuantity() {
    return quantity;
  }
}

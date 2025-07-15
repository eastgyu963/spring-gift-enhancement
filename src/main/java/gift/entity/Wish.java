package gift.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Wish {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, name = "member_id")
  private Long memberId;

  @Column(nullable = false, name = "product_id")
  private Long productId;

  private Long quantity;

  public Wish() {

  }

  public Wish(Long id, Long memberId, Long productId, Long count) {
    this.id = id;
    this.memberId = memberId;
    this.productId = productId;
    this.quantity = count;
  }

  public Wish(Long memberId, Long productId, Long count) {
    this(null, memberId, productId, count);
  }

  public Long getId() {
    return id;
  }

  public Long getMemberId() {
    return memberId;
  }

  public Long getProductId() {
    return productId;
  }

  public Long getQuantity() {
    return quantity;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void updateQuantity(Long quantity) {
    this.quantity = quantity;
  }
}

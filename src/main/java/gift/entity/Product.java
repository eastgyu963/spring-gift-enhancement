package gift.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 15)
  private String name;

  @Column(nullable = false)
  private Long price;

  @Column(nullable = false, name = "IMAGE_URL")
  private String imageUrl;

  public Product() {

  }

  public Product(Long id, String name, Long price, String imageUrl) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.imageUrl = imageUrl;
  }

  public Product(String name, Long price, String imageUrl) {
    this(null, name, price, imageUrl);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public Long getPrice() {
    return price;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public boolean isNameHasWord(String word) {
    return this.name.contains(word);
  }

  public void update(String name, Long price, String imageUrl) {
    this.name = name;
    this.price = price;
    this.imageUrl = imageUrl;
  }
}

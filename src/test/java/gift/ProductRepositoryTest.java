package gift;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import gift.entity.Product;
import gift.repository.product.ProductJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProductRepositoryTest {

  @Autowired
  private ProductJpaRepository repository;

  @Test
  void 상품저장() {
    Product product = new Product("product", 1000L, "https://naver.com");
    Product actual = repository.save(product);

    assertThat(actual.getId()).isNotNull();
    assertThat(actual.getName()).isEqualTo("product");
    assertThat(actual.getPrice()).isEqualTo(1000L);
    assertThat(actual.getImageUrl()).isEqualTo("https://naver.com");
  }

  @Test
  void 상품아이디_조회() {
    Product product = new Product("product", 1000L, "https://naver.com");
    Product actual = repository.save(product);

    Product actual2 = repository.findById(actual.getId()).orElseThrow();

    assertThat(actual.getId()).isEqualTo(actual2.getId());
    assertThat(actual.getName()).isEqualTo(actual2.getName());
    assertThat(actual.getPrice()).isEqualTo(actual2.getPrice());
    assertThat(actual.getImageUrl()).isEqualTo(actual2.getImageUrl());
  }


  @Test
  void 상품수정() {
    Product product = new Product("product", 1000L, "https://naver.com");
    Product actual = repository.save(product);

    Product actual2 = repository.findById(actual.getId()).orElseThrow();
    actual2.update("products", 102L, "https://maver.com");

    Product updatedProduct = repository.findById(actual.getId()).orElseThrow();
    assertThat(updatedProduct.getName()).isEqualTo("products");
    assertThat(updatedProduct.getPrice()).isEqualTo(102L);
    assertThat(updatedProduct.getImageUrl()).isEqualTo("https://maver.com");
  }

  @Test
  void 상품삭제() {
    Product product = new Product("product", 1000L, "https://naver.com");
    Product actual = repository.save(product);

    repository.deleteById(actual.getId());

    boolean present = repository.findById(actual.getId()).isPresent();
    assertThat(present).isFalse();
  }
}

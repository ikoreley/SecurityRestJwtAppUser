package ik.koresh.securityrestjwtappuser.repository;

import ik.koresh.securityrestjwtappuser.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}

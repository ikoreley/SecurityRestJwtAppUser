package ik.koresh.securityrestjwtappuser.controller;

import ik.koresh.securityrestjwtappuser.dto.ReqResDTO;
import ik.koresh.securityrestjwtappuser.entity.Product;
import ik.koresh.securityrestjwtappuser.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AminUserController {

    private final ProductRepository productRepository;

    public AminUserController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/public/product")
    public ResponseEntity<Object> getAllProducts(){
        return ResponseEntity.ok(productRepository.findAll());
    }

    @PostMapping("/admin/saveproduct")
    public ResponseEntity<Object> signUp(@RequestBody ReqResDTO productRequest){
        Product productToSave = new Product();
        productToSave.setName(productRequest.getName());
        return ResponseEntity.ok(productRepository.save(productToSave));
    }

    @GetMapping("/user/alone")
    public ResponseEntity<Object> userAlone(){
        return ResponseEntity.ok("Users alone can access this Api only");
    }

    @GetMapping("/adminuser/both")
    public ResponseEntity<Object> bothAdminAndUser(){
        return ResponseEntity.ok("Both Admin and User  can access the Api");
    }

}

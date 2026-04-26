package com.example.ecommerce.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.ecommerce.Dao.ProductRepository;
import com.example.ecommerce.Service.ProductService;
import com.example.ecommerce.entity.Product;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductRepository productRepository;
	
	@Override
	public String saveProduct(Product product) {
		productRepository.save(product);
		return "Congratulations your product "+product.getProductName()+" is registered successfully!";	}

	@Override
	public List<Product> findByAll() {
		List<Product> products = productRepository.findAll();
		return products;
	}
	@Override
	public List<Product> findByImagePath(String string) {
		 List<Product> product = productRepository.findByImagePath(string);
		return product;
	}

	@Override
	public Product getProductById(int id) {
		Optional<Product> optional =  productRepository.findById(id);
		Product product = optional.get();
		return product;
	}

	@Override
	public Product getById(int id) {
		Optional<Product> pid = productRepository.findById(id);
		Product product=pid.get();
		return product;
	}	
	
	@Override
	public Page<Product> findByAll(int pageno) {
		PageRequest pageRequest = PageRequest.of(pageno, 4);
		Page<Product> pageContent = productRepository.findAll(pageRequest);
		return pageContent;
	}
	
	@Override
	public Page<Product> findByProductName(String name, int pageno) {
		PageRequest pageRequest = PageRequest.of(pageno, 4);
		Page<Product> pageContent = productRepository.findAll(pageRequest);
		return pageContent;
	}

	@Override
	public void deleteProduct(int id) {
		productRepository.deleteById(id);	
	}

	public String updateProduct(String productName, String price, String category, String discount, int id)
	{
		productRepository.updateProduct(productName, price, category,discount,id);
		return "Congratulations your product "+productName+" is updated successfully!";
		}

	@Override
	public Page<Product> findByProduct(int page, String category) {
		PageRequest pageRequest = PageRequest.of(page, 4);
		Page<Product> pageContent =productRepository.findByCategory(pageRequest, category);
		return pageContent;
	}

}

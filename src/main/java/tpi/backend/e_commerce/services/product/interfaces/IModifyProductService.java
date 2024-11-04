package tpi.backend.e_commerce.services.product.interfaces;

import tpi.backend.e_commerce.models.Product;

public interface IModifyProductService {
    Product discountStock(Product product, Integer discount);
}

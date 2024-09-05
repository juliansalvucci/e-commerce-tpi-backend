package tpi.backend.e_commerce.services;

import java.util.List;
import java.util.Optional;

import tpi.backend.e_commerce.models.Brand;


public interface IBrandService {
    public Brand saveBrand(Brand brand);

    public List<Brand> findAll();
    public List<Brand> findAllDeleted();

    public Optional<Brand> findById(Long id);
    public Optional<Brand> findActiveById(Long id);
    public Optional<Brand> findDeletedById(Long id);

    public void delete(Brand brand);
}

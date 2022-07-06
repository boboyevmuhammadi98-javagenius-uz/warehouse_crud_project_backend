package uz.pdp.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.project.entity.Supplier;
import uz.pdp.project.payload.Result;
import uz.pdp.project.repositary.SupplierRepository;

import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    SupplierRepository supplierRepository;

    public Page<Supplier> getSupplier(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return supplierRepository.findAllByActiveIsTrue(pageable);
    }

    public Supplier getSupplierById(int id) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isPresent()) {
            if (optionalSupplier.get().isActive()) {
                return optionalSupplier.get();
            }
        }
        return null;
    }

    public Result addSupplier(Supplier supplier) {
        if (supplierRepository.existsByPhoneNumber(supplier.getPhoneNumber()))
            return new Result("exists bu phoneNumber", false);
        Supplier supplier1 = new Supplier();
        supplier1.setName(supplier.getName());
        supplier1.setPhoneNumber(supplier.getPhoneNumber());
        supplierRepository.save(supplier1);
        return new Result("added", true);
    }

    public Result editSupplier(int id, Supplier supplier) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (!optionalSupplier.isPresent())
            return new Result("supplier not found", false);
        if (!optionalSupplier.get().isActive())
            return new Result("supplier is not active", false);
        if (supplierRepository.existsByPhoneNumber(supplier.getPhoneNumber()))
            return new Result("exists bu phoneNumber", false);
        optionalSupplier.get().setName(supplier.getName());
        optionalSupplier.get().setPhoneNumber(supplier.getPhoneNumber());
        supplierRepository.save(optionalSupplier.get());
        return new Result("edited", true);
    }

    public Result deleteSupplier(int id) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isPresent()) {
            if (optionalSupplier.get().isActive()) {
                supplierRepository.deleteById(id);
                return new Result("deleted", true);
            }
            return new Result("supplier is not active", false);
        }
        return new Result("supplier not found", false);
    }
}

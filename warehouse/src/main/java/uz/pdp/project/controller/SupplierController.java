package uz.pdp.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.project.entity.Supplier;
import uz.pdp.project.payload.Result;
import uz.pdp.project.service.SupplierService;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @GetMapping
    public Page<Supplier> getSupplier(@RequestParam int page) {
        return supplierService.getSupplier(page);
    }

    @GetMapping("/{id}")
    public Supplier getSupplierById(@PathVariable int id) {
        return supplierService.getSupplierById(id);
    }

    @PostMapping
    public Result addSupplier(@RequestBody Supplier supplier) {
        return supplierService.addSupplier(supplier);
    }

    @PutMapping("/{id}")
    public Result editSupplier(@PathVariable int id, Supplier supplier) {
        return supplierService.editSupplier(id, supplier);
    }

    @DeleteMapping("/{id}")
    public Result deleteSupplier(@PathVariable int id) {
        return supplierService.deleteSupplier(id);
    }
}

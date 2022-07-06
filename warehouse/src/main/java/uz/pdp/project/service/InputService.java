package uz.pdp.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.project.entity.*;
import uz.pdp.project.payload.InputDto;
import uz.pdp.project.payload.Result;
import uz.pdp.project.repositary.*;

import java.util.Optional;

@Service
public class InputService {
    @Autowired
    InputRepository inputRepository;

    @Autowired
    WareHouseRepository wareHouseRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    SupplierRepository supplierRepository;

    public Page<Input> getInputs(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return inputRepository.findAll(pageable);
    }

    public Input getInputById(int id) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        return optionalInput.orElse(null);
    }

    public Page<Input> getInputsByWareHouseId(int id, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return inputRepository.findAllByWarehouse_Id(id, pageable);
    }

    public Page<Input> getInputsBySupplierId(int id, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return inputRepository.findAllBySupplier_Id(id, pageable);
    }

    public Result addInput(InputDto inputDto) {
        Optional<WareHouse> optionalWareHouse = wareHouseRepository.findById(inputDto.getWareHouseId());
        if (!optionalWareHouse.isPresent())
            return new Result("ware house not found", false);
        if (!optionalWareHouse.get().isActive())
            return new Result("warehouse is not active", false);
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("currency not found", false);
        if (!optionalCurrency.get().isActive())
            return new Result("currency is not active", false);
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (!optionalSupplier.isPresent())
            return new Result("supplier not found", false);
        if (!optionalSupplier.get().isActive())
            return new Result("supplier is not active", false);
        Input input = new Input();
        input.setDate(inputDto.getDate());
        input.setCurrency(optionalCurrency.get());
        input.setSupplier(optionalSupplier.get());
        input.setWarehouse(optionalWareHouse.get());
        input.setFactureNumber(inputDto.getFactureNumber());
        inputRepository.save(input);
        return new Result("added", true);
    }

    public Result editInput(int id, InputDto inputDto) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (!optionalInput.isPresent())
            return new Result("input not found", false);
        Optional<WareHouse> optionalWareHouse = wareHouseRepository.findById(inputDto.getWareHouseId());
        if (!optionalWareHouse.isPresent())
            return new Result("ware house not found", false);
        if (!optionalWareHouse.get().isActive())
            return new Result("warehouse is not active", false);
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("currency not found", false);
        if (!optionalCurrency.get().isActive())
            return new Result("currency is not active", false);
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (!optionalSupplier.isPresent())
            return new Result("supplier not found", false);
        if (!optionalSupplier.get().isActive())
            return new Result("supplier is not active", false);
        Input input = optionalInput.get();
        input.setDate(inputDto.getDate());
        input.setWarehouse(optionalWareHouse.get());
        input.setCurrency(optionalCurrency.get());
        input.setSupplier(optionalSupplier.get());
        input.setFactureNumber(inputDto.getFactureNumber());
        inputRepository.save(input);
        return new Result("edited", true);
    }

    public Result deleteInput(int id) {
        if (inputRepository.existsById(id)) {
            inputRepository.deleteById(id);
            return new Result("deleted", true);
        }
        return new Result("input not found", false);
    }
}

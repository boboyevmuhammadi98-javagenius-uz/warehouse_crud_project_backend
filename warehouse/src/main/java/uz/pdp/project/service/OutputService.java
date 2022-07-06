package uz.pdp.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.project.entity.*;
import uz.pdp.project.payload.OutputDto;
import uz.pdp.project.payload.Result;
import uz.pdp.project.repositary.*;

import java.util.Optional;

@Service
public class OutputService {
    @Autowired
    OutputRepository outputRepository;

    @Autowired
    WareHouseRepository wareHouseRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    ClientRepository clientRepository;

    public Page<Output> getOutputs(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return outputRepository.findAll(pageable);
    }

    public Output getOutputById(int id) {
        Optional<Output> optionalOutput = outputRepository.findById(id);
        return optionalOutput.orElse(null);
    }

    public Page<Output> getOutputsByWareHouseId(int id, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return outputRepository.findAllByWarehouse_Id(id, pageable);
    }

    public Page<Output> getOutputsByClientId(int id, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return outputRepository.findAllByClient_Id(id, pageable);
    }

    public Result addOutput(OutputDto outputDto) {
        Optional<WareHouse> optionalWareHouse = wareHouseRepository.findById(outputDto.getWareHouseId());
        if (!optionalWareHouse.isPresent())
            return new Result("ware house not found", false);
        if (!optionalWareHouse.get().isActive())
            return new Result("warehouse is not active", false);
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("currency not found", false);
        if (!optionalCurrency.get().isActive())
            return new Result("currency is not active", false);
        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (!optionalClient.isPresent())
            return new Result("client not found", false);
        Output output = new Output();
        output.setCurrency(optionalCurrency.get());
        output.setDate(outputDto.getDate());
        output.setWarehouse(optionalWareHouse.get());
        output.setFactureNumber(outputDto.getFactureNumber());
        output.setClient(optionalClient.get());
        outputRepository.save(output);
        return new Result("added", true);
    }

    public Result editOutput(int id, OutputDto outputDto) {
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (!optionalOutput.isPresent())
            return new Result("output not found", false);
        Optional<WareHouse> optionalWareHouse = wareHouseRepository.findById(outputDto.getWareHouseId());
        if (!optionalWareHouse.isPresent())
            return new Result("ware house not found", false);
        if (!optionalWareHouse.get().isActive())
            return new Result("warehouse is not active", false);
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("currency not found", false);
        if (!optionalCurrency.get().isActive())
            return new Result("currency is not active", false);
        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (!optionalClient.isPresent())
            return new Result("client not found", false);
        Output output = optionalOutput.get();
        output.setClient(optionalClient.get());
        output.setWarehouse(optionalWareHouse.get());
        output.setDate(outputDto.getDate());
        output.setCurrency(optionalCurrency.get());
        output.setFactureNumber(outputDto.getFactureNumber());
        outputRepository.save(output);
        return new Result("edited", true);
    }

    public Result deleteOutput(int id) {
        if (outputRepository.existsById(id)) {
            outputRepository.deleteById(id);
            return new Result("deleted", true);
        }
        return new Result("output not found", false);
    }
}

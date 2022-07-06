package uz.pdp.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.project.entity.WareHouse;
import uz.pdp.project.payload.Result;
import uz.pdp.project.repositary.WareHouseRepository;

import java.util.Optional;

@Service
public class WareHouseService {
    @Autowired
    WareHouseRepository wareHouseRepository;

    public Page<WareHouse> getWareHose(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return wareHouseRepository.findAllByActiveIsTrue(pageable);
    }

    public WareHouse getWareHouseById(int id) {
        Optional<WareHouse> optionalWareHouse = wareHouseRepository.findById(id);
        if (optionalWareHouse.isPresent()) {
            if (optionalWareHouse.get().isActive()) {
                return optionalWareHouse.orElse(null);
            }
        }
        return optionalWareHouse.orElse(null);
    }

    public Result addWareHouse(WareHouse wareHouse) {
        boolean existsByName = wareHouseRepository.existsByName(wareHouse.getName());
        if (existsByName)
            return new Result("exists by name", false);
        WareHouse wareHouse1 = new WareHouse();
        wareHouse1.setName(wareHouse.getName());
        wareHouseRepository.save(wareHouse1);
        return new Result("added", true);
    }

    public Result editWareHouse(int id, WareHouse wareHouse) {
        Optional<WareHouse> optionalWareHouse = wareHouseRepository.findById(id);
        if (!optionalWareHouse.isPresent())
            return new Result("wareHouse not found", false);
        if (!optionalWareHouse.get().isActive())
            return new Result("this warehouse is not actibe", false);
        boolean existsByName = wareHouseRepository.existsByName(wareHouse.getName());
        if (existsByName)
            return new Result("exists bu name", false);
        optionalWareHouse.get().setName(wareHouse.getName());
        wareHouseRepository.save(optionalWareHouse.get());
        return new Result("edited", true);
    }

    public Result deleteWareHouse(int id) {
        Optional<WareHouse> optionalWareHouse = wareHouseRepository.findById(id);
        if (optionalWareHouse.isPresent()) {
            if (optionalWareHouse.get().isActive()) {
                wareHouseRepository.deleteById(id);
                return new Result("deleted", true);
            }
            return new Result("warehouse is not actice", false);
        }
        return new Result("wareHouse not found", false);
    }
}

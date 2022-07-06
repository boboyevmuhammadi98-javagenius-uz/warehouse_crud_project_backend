package uz.pdp.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.project.entity.WareHouse;
import uz.pdp.project.payload.Result;
import uz.pdp.project.service.WareHouseService;

@RestController
@RequestMapping("/warehouse")
public class WereHouseController {

    @Autowired
    WareHouseService wareHouseService;

    @GetMapping
    public Page<WareHouse> getWareHouse(@RequestParam int page) {
        return wareHouseService.getWareHose(page);
    }

    @GetMapping("/{id}")
    public WareHouse getWareHouseById(@PathVariable int id) {
        return wareHouseService.getWareHouseById(id);
    }

    @PostMapping
    public Result addWareHouse(@RequestBody WareHouse wareHouse) {
        return wareHouseService.addWareHouse(wareHouse);
    }

    @PutMapping("/{id}")
    public Result editWareHouse(@PathVariable int id, WareHouse wareHouse) {
        return wareHouseService.editWareHouse(id, wareHouse);
    }

    @DeleteMapping("/{id}")
    public Result deleteWareHoouse(@PathVariable int id) {
        return wareHouseService.deleteWareHouse(id);
    }
}

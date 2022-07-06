package uz.pdp.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.project.entity.Input;
import uz.pdp.project.payload.InputDto;
import uz.pdp.project.payload.Result;
import uz.pdp.project.service.InputService;

@RestController
@RequestMapping("/input")
public class InputController {

    @Autowired
    InputService inputService;

    @GetMapping
    public Page<Input> getInputs(@RequestParam int page) {
        return inputService.getInputs(page);
    }

    @GetMapping("/{id}")
    public Input getInputById(@PathVariable int id) {
        return inputService.getInputById(id);
    }

    @GetMapping("/warehouse/{id}")
    public Page<Input> getInputsByWareHouseId(@PathVariable int id, @RequestParam int page) {
        return inputService.getInputsByWareHouseId(id, page);
    }

    @GetMapping("/supplier/{id}")
    public Page<Input> getInputsBySupplierId(@PathVariable int id, @RequestParam int page) {
        return inputService.getInputsBySupplierId(id, page);
    }

    @PostMapping
    public Result addInput(@RequestBody InputDto inputDto) {
        return inputService.addInput(inputDto);
    }

    @PutMapping("/{id}")
    public Result editInput(@PathVariable int id, InputDto inputDto) {
        return inputService.editInput(id, inputDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteInput(@PathVariable int id) {
        return inputService.deleteInput(id);
    }
}

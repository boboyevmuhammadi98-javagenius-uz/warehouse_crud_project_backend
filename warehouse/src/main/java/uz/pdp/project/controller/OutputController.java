package uz.pdp.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.project.entity.Output;
import uz.pdp.project.payload.OutputDto;
import uz.pdp.project.payload.Result;
import uz.pdp.project.service.OutputService;

@RestController
@RequestMapping("/output")
public class OutputController {

    @Autowired
    OutputService outputService;

    @GetMapping
    public Page<Output> getOutputs(@RequestParam int page) {
        return outputService.getOutputs(page);
    }

    @GetMapping("/{id}")
    public Output getOutputById(@PathVariable int id) {
        return outputService.getOutputById(id);
    }

    @GetMapping("/warehouse/{id}")
    public Page<Output> getOutputsByWareHouseId(@PathVariable int id, @RequestParam int page) {
        return outputService.getOutputsByWareHouseId(id, page);
    }

    @GetMapping("/client/{id}")
    public Page<Output> getOutputsByClientId(@PathVariable int id, @RequestParam int page) {
        return outputService.getOutputsByClientId(id, page);
    }

    @PostMapping
    public Result addOutput(@RequestBody OutputDto outputDto) {
        return outputService.addOutput(outputDto);
    }

    @PutMapping("/{id}")
    public Result editOutput(@PathVariable int id, OutputDto outputDto) {
        return outputService.editOutput(id, outputDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteOutput(@PathVariable int id) {
        return outputService.deleteOutput(id);
    }
}

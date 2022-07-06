package uz.pdp.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.project.entity.Measurement;
import uz.pdp.project.payload.Result;
import uz.pdp.project.service.MeasurementService;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {
    @Autowired
    MeasurementService measurementService;

    @GetMapping()
    public Page<Measurement> getMeasurement(@RequestParam int page) {
        return measurementService.getMeasurement(page);
    }

    @GetMapping("/{id}")
    public Measurement getMeasurementById(@PathVariable int id) {
        return measurementService.getMeasurementById(id);
    }

    @PostMapping()
    public Result addMeasurement(@RequestBody Measurement measurement) {
        return measurementService.addMeasurement(measurement);
    }

    @PutMapping("/{id}")
    public Result editMeasurement(@PathVariable int id, @RequestBody Measurement measurement) {
        return measurementService.editMeasurement(id, measurement);
    }

    @DeleteMapping("/{id}")
    public Result deleteMeasurement(@PathVariable int id) {
        return measurementService.deleteMeasurement(id);
    }
}

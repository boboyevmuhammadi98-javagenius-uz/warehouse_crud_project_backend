package uz.pdp.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.project.entity.Measurement;
import uz.pdp.project.payload.Result;
import uz.pdp.project.repositary.MeasurementRepository;

import java.util.Optional;

@Service
public class MeasurementService {
    @Autowired
    MeasurementRepository measurementRepository;

    public Page<Measurement> getMeasurement(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return measurementRepository.findAllByActiveIsTrue(pageable);
    }

    public Measurement getMeasurementById(int id) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (optionalMeasurement.isPresent()) {
            if (optionalMeasurement.get().isActive()) {
                return optionalMeasurement.get();
            }
        }
        return null;
    }

    public Result addMeasurement(Measurement measurement) {
        boolean existsByName = measurementRepository.existsByName(measurement.getName());
        if (existsByName)
            return new Result("measurement exists", false);
        measurementRepository.save(measurement);
        return new Result("added", true);
    }

    public Result editMeasurement(int id, Measurement measurement) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (!optionalMeasurement.isPresent())
            return new Result("measurement not found", false);
        if (!optionalMeasurement.get().isActive())
            return new Result("measurement is not active", false);
        optionalMeasurement.get().setName(measurement.getName());
        measurementRepository.save(optionalMeasurement.get());
        return new Result("edited", true);
    }

    public Result deleteMeasurement(int id) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (optionalMeasurement.isPresent()) {
            if (optionalMeasurement.get().isActive()) {
                measurementRepository.delete(optionalMeasurement.get());
                return new Result("deleted", true);
            }
            return new Result("measurement is not active", false);
        }
        return new Result("measurement not found", false);
    }
}

package uz.pdp.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.project.entity.Currency;
import uz.pdp.project.entity.Users;
import uz.pdp.project.payload.Result;
import uz.pdp.project.payload.UserDto;
import uz.pdp.project.service.CurrencyService;
import uz.pdp.project.service.UserService;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    CurrencyService currencyService;

    @GetMapping
    public Page<Currency> getCurrency(@RequestParam int page) {
        return currencyService.getCurrency(page);
    }

    @GetMapping("/{id}")
    public Currency getCurrencyById(@PathVariable int id) {
        return currencyService.getCurrencyById(id);
    }

    @PostMapping
    public Result addCurrency(@RequestBody Currency currency) {
        return currencyService.addCurrency(currency);
    }

    @PutMapping("/{id}")
    public Result editCurrency(@PathVariable int id, Currency currency) {
        return currencyService.editCurrency(id, currency);
    }

    @DeleteMapping("/{id}")
    public Result deleteCurrency(@PathVariable int id) {
        return currencyService.deleteCurrency(id);
    }
}

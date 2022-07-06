package uz.pdp.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.project.entity.Currency;
import uz.pdp.project.payload.Result;
import uz.pdp.project.repositary.CurrencyRepository;

import java.util.Optional;

@Service
public class CurrencyService {
    @Autowired
    CurrencyRepository currencyRepository;

    public Page<Currency> getCurrency(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return currencyRepository.findAllByActiveIsTrue(pageable);
    }

    public Currency getCurrencyById(int id) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (optionalCurrency.isPresent()) {
            if (optionalCurrency.get().isActive()) {
                return optionalCurrency.get();
            }
        }
        return null;
    }

    public Result addCurrency(Currency currency) {
        if (currencyRepository.existsByName(currency.getName()))
            return new Result("exists bu name", false);
        Currency currency1 = new Currency();
        currency1.setName(currency.getName());
        currencyRepository.save(currency1);
        return new Result("added", true);
    }

    public Result editCurrency(int id, Currency currency) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (!optionalCurrency.isPresent())
            return new Result("currency not found", false);
        if (!optionalCurrency.get().isActive())
            return new Result("currency is not active", false);
        if (currencyRepository.existsByName(currency.getName()))
            return new Result("exists bu name", false);
        optionalCurrency.get().setName(currency.getName());
        currencyRepository.save(optionalCurrency.get());
        return new Result("edited", true);
    }

    public Result deleteCurrency(int id) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (optionalCurrency.isPresent()) {
            if (optionalCurrency.get().isActive()) {
                currencyRepository.deleteById(id);
                return new Result("deleted", true);
            }
            return new Result("currency is not active", false);
        }
        return new Result("currency not found", false);
    }
}

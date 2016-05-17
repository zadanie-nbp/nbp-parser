package pl.nbp.parser.exchangeRate.operations;

import pl.nbp.parser.exchangeRate.ExchangeRate;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by maciek on 17.05.16.
 */
public class ExchangeRateFilter {
    public static List<ExchangeRate> filterByCurrencyCode(List<ExchangeRate> exchangeRates, String currencyCode) {
        return exchangeRates.stream().
                filter(exchangeRate -> exchangeRate.getCurrencyCode().equals(currencyCode)).
                collect(Collectors.toList());
    }
}

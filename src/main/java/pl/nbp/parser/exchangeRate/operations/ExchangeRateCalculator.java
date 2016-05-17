package pl.nbp.parser.exchangeRate.operations;

import pl.nbp.parser.exchangeRate.ExchangeRate;

import java.util.List;
import java.util.function.ToDoubleFunction;

public class ExchangeRateCalculator {
    static Double calculateAverageRate(List<ExchangeRate> exchangeRates, ToDoubleFunction<ExchangeRate> exchangeRateToDoubleFunction) {
        return exchangeRates.stream().
                mapToDouble(exchangeRateToDoubleFunction).
                average().
                getAsDouble();
    }

    public static Double calculateAverageRateBuy(List<ExchangeRate> exchangeRates) {
        return calculateAverageRate(exchangeRates, ExchangeRate::getRateBuy);
    }

    public static Double calculateStandardDeviationOfRateSell(List<ExchangeRate> exchangeRates) {
        Double avg = calculateAverageRate(exchangeRates, ExchangeRate::getRateSell);
        return Math.sqrt(
                exchangeRates.stream().
                    mapToDouble(el -> avg - el.getRateSell()).
                    map(el -> Math.pow(el, 2)).
                    average().getAsDouble()
        );
    }

}

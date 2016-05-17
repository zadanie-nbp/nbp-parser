package pl.nbp.parser.exchangeRate.operations;

import org.hamcrest.Matchers;
import org.junit.Test;
import pl.nbp.parser.exchangeRate.ExchangeRate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;

public class ExchangeRateCalculatorAverageTest {
    @Test
    public void shouldReturnRateBuyAverage() {
        List<ExchangeRate> exchangeRatesToCalculateAverage = Arrays.asList(
                new ExchangeRate("USD", 1f, 2.1f),
                new ExchangeRate("PLN", 3.22f, 2.1f),
                new ExchangeRate("USD", 1.42f, 42.1f),
                new ExchangeRate("EUR", 1.6f, 0.88f)
        );

        Double average = ExchangeRateCalculator.calculateAverageRateBuy(exchangeRatesToCalculateAverage);
        assertThat(average, Matchers.closeTo(1.81, 0.001));
    }
    @Test
    public void shouldReturnRateSellAverage() {
        List<ExchangeRate> exchangeRatesToCalculateAverage = Arrays.asList(
                new ExchangeRate("USD", 1f, 2.1f),
                new ExchangeRate("PLN", 3.22f, 2.1f),
                new ExchangeRate("USD", 1.42f, 42.1f),
                new ExchangeRate("EUR", 1.6f, 0.88f)
        );

        Double average = ExchangeRateCalculator.calculateAverageRate(exchangeRatesToCalculateAverage, ExchangeRate::getRateSell);
        assertThat(average, Matchers.closeTo(11.794, 0.001));
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowNoSuchElementExceptionForEmptyList() {
        ExchangeRateCalculator.calculateAverageRateBuy(new ArrayList<>());
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionForNullList() {
        ExchangeRateCalculator.calculateAverageRateBuy(null);
    }
}

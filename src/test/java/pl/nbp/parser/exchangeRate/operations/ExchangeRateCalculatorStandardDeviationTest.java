package pl.nbp.parser.exchangeRate.operations;

import org.hamcrest.Matchers;
import org.junit.Test;
import pl.nbp.parser.exchangeRate.ExchangeRate;
import pl.nbp.parser.exchangeRate.operations.ExchangeRateCalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;

public class ExchangeRateCalculatorStandardDeviationTest {
    @Test
    public void shouldReturnStandardDeviation() {
        List<ExchangeRate> exchangeRatesToCalculateStandardDeviation = Arrays.asList(
                new ExchangeRate("USD", 1f, 2.1f),
                new ExchangeRate("PLN", 3.22f, 2.1f),
                new ExchangeRate("USD", 1.42f, 42.1f),
                new ExchangeRate("EUR", 1.6f, 0.88f)
        );

        Double standardDeviation = ExchangeRateCalculator.calculateStandardDeviationOfRateSell(exchangeRatesToCalculateStandardDeviation);
        assertThat(standardDeviation, Matchers.closeTo(17.50368, 0.00001));
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowNoSuchElementExceptionForEmptyList() {
        ExchangeRateCalculator.calculateStandardDeviationOfRateSell(new ArrayList<>());
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionForNullList() {
        ExchangeRateCalculator.calculateStandardDeviationOfRateSell(null);
    }
}

package pl.nbp.parser.exchangeRate;

import org.hamcrest.Matchers;
import org.junit.Test;
import pl.nbp.parser.exchangeRate.operations.ExchangeRateFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;

public class ExchangeRateFilterTest {
    @Test
    public void shouldFilterExchangeRatesByCurrency() {
        List<ExchangeRate> exchangeRatesToFilter = Arrays.asList(
            new ExchangeRate("USD", 1f, 2.1f),
            new ExchangeRate("PLN", 3.22f, 2.1f),
            new ExchangeRate("USD", 1.42f, 42.1f),
            new ExchangeRate("EUR", 1.6f, 0.88f)
        );

        List<ExchangeRate> expectedFilteredExchangeRates = Arrays.asList(
                new ExchangeRate("USD", 1f, 2.1f),
                new ExchangeRate("USD", 1.42f, 42.1f)
        );

        List<ExchangeRate> filteredExchangeRates = ExchangeRateFilter.filterByCurrencyCode(exchangeRatesToFilter, "USD");

        assertThat(filteredExchangeRates, Matchers.is(expectedFilteredExchangeRates));

    }

    @Test
    public void shouldReturnEmptyListWhenFilteringEmptyList() {
        List<ExchangeRate> filteredExchangeRates = ExchangeRateFilter.filterByCurrencyCode(new ArrayList<>(), "USD");

        assertThat(
                filteredExchangeRates,
                Matchers.allOf(
                        Matchers.instanceOf(List.class),
                        Matchers.empty()
                )
        );
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionForNullList() {
        ExchangeRateFilter.filterByCurrencyCode(null, "USD");
    }
}

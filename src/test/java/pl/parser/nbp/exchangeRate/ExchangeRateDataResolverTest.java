package pl.parser.nbp.exchangeRate;

import org.hamcrest.Matchers;
import org.junit.Test;
import pl.parser.nbp.exchangeRate.resolver.ExchangeRateDataResolver;
import pl.parser.nbp.params.Params;

import java.io.IOException;
import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;

public class ExchangeRateDataResolverTest {
    @Test
    public void shouldReturnExchangeRateDataForGivenParams() throws IOException {
        Params params = new Params("USD", LocalDate.of(2016, 1, 2), LocalDate.of(2016, 1, 8));

        ExchangeRateDataResolver.ExchangeRateData exchangeRateData = ExchangeRateDataResolver.resolve(params);
        assertThat(
                exchangeRateData,
                Matchers.allOf(
                        Matchers.hasProperty("buyAverage", Matchers.closeTo(3.95209, 0.00001)),
                        Matchers.hasProperty("sellStandardDeviation", Matchers.closeTo(0.0195, 0.0001))
                )
        );
    }
}

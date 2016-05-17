package pl.parser.nbp.exchangeRate.resolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.parser.nbp.client.NbpClient;
import pl.parser.nbp.exchangeRate.ExchangeRate;
import pl.parser.nbp.exchangeRate.operations.ExchangeRateCalculator;
import pl.parser.nbp.exchangeRate.operations.ExchangeRateFilter;
import pl.parser.nbp.exchangeRate.parser.ExchangeRateParser;
import pl.parser.nbp.params.Params;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ExchangeRateDataResolver {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExchangeRateDataResolver.class);

    public static ExchangeRateData resolve(Params params) throws IOException {
        List<String> exchangeDateFileNames = NbpClient.getExchangeRatesFilenamesBetweenDates(
                params.getDateFrom(),
                params.getDateTo()
        );

        List<ExchangeRate> exchangeRates = exchangeDateFileNames.stream().flatMap(el -> {
            try {
                return ExchangeRateParser.parseToList(NbpClient.getFileContent(el)).stream();
            } catch (IOException e) {
                LOGGER.error("IO error during parsing of '{}' file", el);
            } catch (JAXBException e) {
                LOGGER.error("XML parse error during parsing of '{}' file", el);
            }
            return null;
        }).collect(Collectors.toList());

        List<ExchangeRate> filteredExchangeRates = ExchangeRateFilter.filterByCurrencyCode(exchangeRates, params.getCurrency());

        return new ExchangeRateData(
                ExchangeRateCalculator.calculateAverageRateBuy(filteredExchangeRates),
                ExchangeRateCalculator.calculateStandardDeviationOfRateSell(filteredExchangeRates)
        );
    }

    public static class ExchangeRateData {
        private final Double buyAverage;
        private final Double sellStandardDeviation;

        public ExchangeRateData(Double buyAverage, Double sellStandardDeviation) {
            this.buyAverage = buyAverage;
            this.sellStandardDeviation = sellStandardDeviation;
        }

        public Double getBuyAverage() {
            return buyAverage;
        }

        public Double getSellStandardDeviation() {
            return sellStandardDeviation;
        }
    }
}

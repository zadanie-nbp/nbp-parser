package pl.parser.nbp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.parser.nbp.exchangeRate.resolver.ExchangeRateDataResolver;
import pl.parser.nbp.params.Params;
import pl.parser.nbp.params.ParamsParser;

import java.io.IOException;

public class MainClass {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExchangeRateDataResolver.class);

    public static void main(String[] args) {
        Params params = ParamsParser.parseArguments(args[0], args[1], args[2]);
        try {
            ExchangeRateDataResolver.ExchangeRateData exchangeRateData = ExchangeRateDataResolver.resolve(params);
            System.out.format("%.4f\n", exchangeRateData.getBuyAverage());
            System.out.format("%.4f\n", exchangeRateData.getSellStandardDeviation());
        } catch (IOException e) {
            LOGGER.error("Error while parsing params");
        }

    }
}

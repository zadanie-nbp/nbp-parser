package pl.nbp.parser.exchangeRate.parser;

import pl.nbp.parser.exchangeRate.ExchangeRate;
import pl.nbp.parser.exchangeRate.parser.model.ExchangeRateTable;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.List;
import java.util.stream.Collectors;

public class ExchangeRateParser {

    public static List<ExchangeRate> parseToList(String content) throws JAXBException {
        Unmarshaller unmarshaller = JAXBContext.newInstance(ExchangeRateTable.class).createUnmarshaller();
        StringReader reader = new StringReader(content);

        ExchangeRateTable exchangeRateTable = (ExchangeRateTable) unmarshaller.unmarshal(reader);

        return exchangeRateTable.getItems().stream().map(exchangeRateItem-> {
            ExchangeRate exchangeRate = new ExchangeRate();
            exchangeRate.setCurrencyCode(exchangeRateItem.getCurrencyCode());
            exchangeRate.setRateBuy(new Float(exchangeRateItem.getRateBuy().replace(",", ".")));
            exchangeRate.setRateSell(new Float(exchangeRateItem.getRateSell().replace(",", ".")));
            return exchangeRate;
        }).collect(Collectors.toList());
    }
}

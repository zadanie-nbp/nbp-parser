package pl.nbp.parser.exchangeRate;

import org.hamcrest.Matchers;
import org.junit.Test;
import pl.nbp.parser.exchangeRate.parser.ExchangeRateParser;

import javax.xml.bind.JAXBException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;

public class ExchangeRateParserTest {
    @Test
    public void shouldParseExchangeRates() throws JAXBException {
        final String xmlToParse =
                "<tabela_kursow typ=\"C\">\n" +
                "   <numer_tabeli>73/C/NBP/2007</numer_tabeli>\n" +
                "   <data_notowania>2007-04-12</data_notowania>\n" +
                "   <data_publikacji>2007-04-13</data_publikacji>\n" +
                "   <pozycja>\n" +
                "      <nazwa_waluty>dolar amerykański</nazwa_waluty>\n" +
                "      <przelicznik>1</przelicznik>\n" +
                "      <kod_waluty>USD</kod_waluty>\n" +
                "      <kurs_kupna>2,8210</kurs_kupna>\n" +
                "      <kurs_sprzedazy>2,8780</kurs_sprzedazy>\n" +
                "   </pozycja>\n" +
                "   <pozycja>\n" +
                "      <nazwa_waluty>dolar australijski</nazwa_waluty>\n" +
                "      <przelicznik>1</przelicznik>\n" +
                "      <kod_waluty>AUD</kod_waluty>\n" +
                "      <kurs_kupna>2,3292</kurs_kupna>\n" +
                "      <kurs_sprzedazy>2,3762</kurs_sprzedazy>\n" +
                "   </pozycja>\n" +
                "</tabela_kursow>";

        List<ExchangeRate> expectedExchangeRates = Arrays.asList(
                new ExchangeRate("USD", 2.8210f, 2.8780f),
                new ExchangeRate("AUD", 2.3292f, 2.3762f)
        );

        List<ExchangeRate> parsedExchangeRates= ExchangeRateParser.parseToList(xmlToParse);

        assertThat(parsedExchangeRates, Matchers.is(expectedExchangeRates));
    }
}

package pl.nbp.parser.exchangeRate.parser.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="tabela_kursow")
public class ExchangeRateTable {

    private List<ExchangeRateItem> items;

    @XmlElement(name = "pozycja")
    public List<ExchangeRateItem> getItems() {
        return items;
    }

    public void setItems(List<ExchangeRateItem> items) {
        this.items = items;
    }

    @XmlRootElement(name="pozycja")
    public static class ExchangeRateItem {

        String currencyCode;

        String rateBuy;

        String rateSell;

        @XmlElement(name = "kod_waluty")
        public String getCurrencyCode() {
            return currencyCode;
        }

        @XmlElement(name = "kurs_kupna")
        public String getRateBuy() {
            return rateBuy;
        }

        public void setRateBuy(String rateBuy) {
            this.rateBuy = rateBuy;
        }

        @XmlElement(name = "kurs_sprzedazy")
        public String getRateSell() {
            return rateSell;
        }

        public void setRateSell(String rateSell) {
            this.rateSell = rateSell;
        }

        public void setCurrencyCode(String currencyCode) {
            this.currencyCode = currencyCode;
        }
    }
}

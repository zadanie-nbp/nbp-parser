package pl.nbp.parser;

public class ExchangeRate {
    private String currencyCode;
    private Float rateBuy;
    private Float rateSell;

    public ExchangeRate() {
    }

    public ExchangeRate(String currencyCode, Float rateBuy, Float rateSell) {
        this.currencyCode = currencyCode;
        this.rateBuy = rateBuy;
        this.rateSell = rateSell;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Float getRateBuy() {
        return rateBuy;
    }

    public void setRateBuy(Float rateBuy) {
        this.rateBuy = rateBuy;
    }

    public Float getRateSell() {
        return rateSell;
    }

    public void setRateSell(Float rateSell) {
        this.rateSell = rateSell;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExchangeRate that = (ExchangeRate) o;

        if (currencyCode != null ? !currencyCode.equals(that.currencyCode) : that.currencyCode != null) return false;
        if (rateBuy != null ? !rateBuy.equals(that.rateBuy) : that.rateBuy != null) return false;
        return rateSell != null ? rateSell.equals(that.rateSell) : that.rateSell == null;

    }

    @Override
    public int hashCode() {
        int result = currencyCode != null ? currencyCode.hashCode() : 0;
        result = 31 * result + (rateBuy != null ? rateBuy.hashCode() : 0);
        result = 31 * result + (rateSell != null ? rateSell.hashCode() : 0);
        return result;
    }
}

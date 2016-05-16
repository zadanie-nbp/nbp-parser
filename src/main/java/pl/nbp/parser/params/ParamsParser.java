package pl.nbp.parser.params;

import java.time.LocalDate;

public class ParamsParser {
    public static Params parseArguments(String currency, String dateFrom, String dateTo) {
        Params params = new Params();
        params.setCurrency(currency);
        params.setDateFrom(LocalDate.parse(dateFrom));
        params.setDateTo(LocalDate.parse(dateTo));
        return params;
    }
}

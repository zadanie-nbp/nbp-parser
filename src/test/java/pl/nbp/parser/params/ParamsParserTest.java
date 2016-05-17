package pl.nbp.parser.params;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class ParamsParserTest {
    @Test
    public void shouldParseArguments() {
        Params expectedParams = new Params(
                "PLN",
                LocalDate.of(2015,1,2),
                LocalDate.of(2015,5,10)
        );

        Params params = ParamsParser.parseArguments("PLN", "2015-01-02", "2015-05-10");

        Assert.assertEquals(params, expectedParams);
    }
}

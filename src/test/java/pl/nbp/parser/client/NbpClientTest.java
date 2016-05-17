package pl.nbp.parser.client;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;


public class NbpClientTest {
    @Test
    public void shouldReturnCurrencyExchangeFilesFilteredByDates() throws IOException {
        List<String> expectedFiles = Arrays.asList(
                "c002z160105",
                "c003z160107",
                "c004z160108",
                "c005z160111"
        );

        List<String> resultFiles = NbpClient.getExchangeRatesFilesBetweenDates(
                LocalDate.of(2016, 1, 5),
                LocalDate.of(2016, 1, 11)
        );

        assertThat(resultFiles, Matchers.is(expectedFiles));
    }

    @Test
    public void shouldReturnRequestedFile() throws IOException {
        String expectedTableNumber = "73/C/NBP/2007";

        String resultFileContent = NbpClient.getFileContent("c073z070413");
        String resultTableNumberLine = resultFileContent.split("\r\n")[2];

        assertThat(resultTableNumberLine.trim(), Matchers.containsString(expectedTableNumber));
    }

    @Test
    public void shouldReturnCurrencyExchangeFilesFilteredByDatesWithYears() throws IOException {
        List<String> expectedFiles = Arrays.asList(
                "c247z151221",
                "c248z151222",
                "c249z151223",
                "c250z151224",
                "c251z151228",
                "c252z151229",
                "c253z151230",
                "c254z151231",
                "c002z160105",
                "c003z160107",
                "c004z160108",
                "c005z160111"
        );

        List<String> resultFiles = NbpClient.getExchangeRatesFilesBetweenDates(
                LocalDate.of(2015, 12, 20),
                LocalDate.of(2016, 1, 11)
        );

        assertThat(resultFiles, Matchers.is(expectedFiles));
    }

}

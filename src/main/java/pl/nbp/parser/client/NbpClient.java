package pl.nbp.parser.client;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NbpClient {
    static final String BASE_NBP_URI = "http://www.nbp.pl/kursy/xml";

    static List<String> getAvailableFiles() throws IOException {
        return Arrays.asList(HttpClient.getStringFromUri(String.format("%s/%s", BASE_NBP_URI, "dir.txt")).split("\r\n"));
    }

    public static List<String> getExchangeRatesFilesBetweenDates(LocalDate dateFrom, LocalDate dateTo) throws IOException {
        List<String> availableFiles = getAvailableFiles();
        return availableFiles.stream()
                .filter(s -> s.startsWith("c"))
                .filter(s -> {
                    String fileDateString = s.substring(5,11);
                    LocalDate fileDate = LocalDate.parse(fileDateString, DateTimeFormatter.ofPattern("yyMMdd"));
                    return !(fileDate.isAfter(dateTo) || fileDate.isBefore(dateFrom));
                })
                .collect(Collectors.toList());
    }

    public static String getFileContent(String filename) throws IOException {
        return HttpClient.getStringFromUri(String.format("%s/%s.xml", BASE_NBP_URI , filename));
    }
}

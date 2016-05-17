package pl.nbp.parser.client;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class NbpClient {
    private static final String BASE_NBP_URI = "http://www.nbp.pl/kursy/xml";

    private static List<String> getAvailableFiles(LocalDate dateFrom, LocalDate dateTo) throws IOException {
        int year = dateFrom.getYear();
        int endYear = dateTo.getYear();
        List<String> availableFiles = new LinkedList<>();
        while(year <= endYear) {
            availableFiles.addAll(getAvailableFilesForYear(year));
            year++;
        }
        return availableFiles;
    }

    private static List<String> getAvailableFilesForYear(int year) throws IOException {
        int currentYear = LocalDate.now().getYear();
        String filename = year == currentYear ? "dir.txt" : String.format("dir%d.txt", year);
        return Arrays.asList(HttpClient.getStringFromUri(String.format("%s/%s", BASE_NBP_URI, filename)).split("\r\n"));
    }

    public static List<String> getExchangeRatesFilenamesBetweenDates(LocalDate dateFrom, LocalDate dateTo) throws IOException {
        List<String> availableFiles = getAvailableFiles(dateFrom, dateTo);
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

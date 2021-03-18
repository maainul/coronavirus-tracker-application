package com.coronainfo;

import com.coronainfo.model.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronavirusDataService {

    private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private static String DETH_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";

    private List<LocationStats> allStats = new ArrayList<>();

    public List<LocationStats> getAllStats() {
        return allStats;

    }

    private List<DeathReportStats> alldeathReportStats = new ArrayList<>();

    public List<DeathReportStats> getDeathReportStats() {
        return alldeathReportStats;
    }

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchVirusData() throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URL))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        List<LocationStats> newStats = new ArrayList<>();
        StringReader in = new StringReader(response.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
        for (CSVRecord record : records) {
            String state = record.get("Province/State");
            LocationStats locationStat = new LocationStats();
            locationStat.setState(record.get("Province/State"));
            locationStat.setCountry(record.get("Country/Region"));
            int latestCases = Integer.parseInt(record.get(record.size() - 1));
            int previousDayCases = Integer.parseInt(record.get(record.size() - 2));

            locationStat.setLatestTotalCase(latestCases);
            locationStat.setDifFromPreviousDay(latestCases - previousDayCases);
            newStats.add(locationStat);
        }
        this.allStats = newStats;
    }
    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchDeathData() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(DETH_DATA_URL)).GET().build();

        HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());

        StringReader in = new StringReader(response.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);

        List<DeathReportStats> newDeathReports = new ArrayList<>();

        for (CSVRecord record : records){
            DeathReportStats deathReportStats = new DeathReportStats();
            String state = record.get("Province/State");
            deathReportStats.setState(record.get("Province/State"));
            deathReportStats.setCountry(record.get("Country/Region"));
            int latestDeathCase = Integer.parseInt(record.get(record.size()-1));
            int previousDayDeathCase = Integer.parseInt(record.get(record.size()-2));
            deathReportStats.setLatestTotalDeath(latestDeathCase);
            deathReportStats.setDiffDeathFromPreviousDay(previousDayDeathCase);
            newDeathReports.add(deathReportStats);

        }
        this.alldeathReportStats = newDeathReports;
    }
}

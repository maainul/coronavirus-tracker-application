package com.coronainfo.controllers;

import com.coronainfo.CoronavirusDataService;
import com.coronainfo.DeathReportStats;
import com.coronainfo.model.LocationStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronavirusDataService coronavirusDataService;

    @GetMapping("/")
    public String home(Model model){
        List<LocationStats> allStats = coronavirusDataService.getAllStats();
        // Recorded cases
        int totalReportedCases = allStats.stream().mapToInt(LocationStats::getLatestTotalCase).sum();
        int totalNewCases = allStats.stream().mapToInt(LocationStats::getDifFromPreviousDay).sum();
        int bdTotalReportedCase = 0;
        int bdTotalNewCases = 0;

        for (LocationStats bd : allStats){
            if (bd.getCountry().equals("Bangladesh")){
                bdTotalReportedCase = bd.getLatestTotalCase();
                bdTotalNewCases = bd.getDifFromPreviousDay();
            }
        }
        model.addAttribute("locationStats",allStats);
        model.addAttribute("totalReportedCases",totalReportedCases);
        model.addAttribute("totalNewCases",totalNewCases);
        model.addAttribute("bdTotalReportedCase",bdTotalReportedCase);
        model.addAttribute("bdTotalNewCases",bdTotalNewCases);

        // Death Cases
        List<DeathReportStats> allDeathStats = coronavirusDataService.getDeathReportStats();
        int totalDeathCases = allDeathStats.stream().mapToInt(DeathReportStats::getLatestTotalDeath).sum();
        int totalNewDeathCases = allDeathStats.stream().mapToInt(DeathReportStats::getDiffDeathFromPreviousDay).sum();
        int bangladeshTotalDeathCase = 0;
        int bangladeshNewDeathCase = 0;
        for (DeathReportStats bd : allDeathStats){
            if (bd.getCountry().equals("Bangladesh")){
                bangladeshTotalDeathCase = bd.getLatestTotalDeath();
                bangladeshNewDeathCase = bd.getDiffDeathFromPreviousDay();
            }
        }

        model.addAttribute("totalDeathCases",totalDeathCases);
        model.addAttribute("totalNewDeathCases",totalNewDeathCases);
        model.addAttribute("bangladeshTotalDeathCase",bangladeshTotalDeathCase);
        model.addAttribute("bangladeshNewDeathCase",bangladeshNewDeathCase);
        return "home";
    }
}

package com.harish.ListingReportsGenerator.service;

import com.harish.ListingReportsGenerator.comparator.FrequencyComparator;
import com.harish.ListingReportsGenerator.dto.Contacts;
import com.harish.ListingReportsGenerator.dto.Listing;
import com.harish.ListingReportsGenerator.dto.reports.AverageListingSellingPriceReport;
import com.harish.ListingReportsGenerator.dto.reports.MakeDistributionReport;
import com.harish.ListingReportsGenerator.dto.reports.MostContactedListingAvgPriceReport;
import com.harish.ListingReportsGenerator.repository.ContactsRepository;
import com.harish.ListingReportsGenerator.repository.ListingRepository;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportService {
    private final ListingRepository listingRepository;
    private final ContactsRepository contactsRepository;

    public ReportService(ListingRepository listingRepository, ContactsRepository contactsRepository) {
        this.listingRepository = listingRepository;
        this.contactsRepository = contactsRepository;
    }

    public Set<AverageListingSellingPriceReport> getAverageListingSellingPrices() {
        var listingSellingPriceReports = new HashSet<AverageListingSellingPriceReport>();
        List<Listing> listings = listingRepository.findAll();
        Map<String, Long> sellerTypeToAvgPriceMap = Optional.of(listings).orElse(new ArrayList<>()).stream()
                .collect(Collectors.toMap(Listing::getSellerType, Listing::getPrice, (price1, price2) -> (price1 + price2) / 2));

        sellerTypeToAvgPriceMap.forEach((sellerType, average) -> listingSellingPriceReports.add(new AverageListingSellingPriceReport(sellerType, formatCurrency(average))));
        return listingSellingPriceReports;
    }

    public Set<MakeDistributionReport> getMakeDistributionReport() {
        var makeDistributionReports = new HashSet<MakeDistributionReport>();
        List<Listing> listings = listingRepository.findAll();
        long totalCount = listings.size();

        Map<String, Long> makeToCountMap = Optional.of(listings).orElse(new ArrayList<>()).stream()
                .collect(Collectors.groupingBy(Listing::getMake, Collectors.counting()));

        makeToCountMap.forEach((make, count) -> makeDistributionReports.add(new MakeDistributionReport(make, count, totalCount)));
        return makeDistributionReports;
    }

    public MostContactedListingAvgPriceReport getMostContactedListingAvgPriceReport() {
        List<Contacts> contacts = contactsRepository.findAll();
        List<Listing> listings = listingRepository.findAll();

        Map<Integer, Long> listingContactsCountMap = contacts.stream().collect(Collectors.groupingBy(Contacts::getListingId, Collectors.counting()));

        FrequencyComparator frequencyComparator = new FrequencyComparator(listingContactsCountMap);
        List<Integer> mostContactedListingIdList = new ArrayList<>(listingContactsCountMap.keySet());
        int totalListingContacted = mostContactedListingIdList.size();
        mostContactedListingIdList.sort(frequencyComparator);
        mostContactedListingIdList.subList(0, getTop30ElementsIndex(totalListingContacted));

        Double averagePrice = mostContactedListingIdList.stream().map(listingRepository::findById).filter(Optional::isPresent).map(Optional::get).map(Listing::getPrice).collect(Collectors.averagingLong(Long::longValue));
        return new MostContactedListingAvgPriceReport(formatCurrency(averagePrice.longValue()));
    }


    private int getTop30ElementsIndex(int totalSize) {
        return (totalSize * 30) / 100;
    }

    private String formatCurrency(long value) {
        NumberFormat numberFormat = DecimalFormat.getCurrencyInstance(Locale.GERMANY);
        DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
        decimalFormat.applyPattern(DecimalFormatSymbols.getInstance(Locale.GERMANY).getCurrencySymbol()+" ###,###',-'");
        return decimalFormat.format(value);
    }

}

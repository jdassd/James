package com.James.corporateportraitplatforms.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YearReport {

    private Long id;
    private String cid;
    private String year;
    private String numberOfEmployees;
    private String totalAssets;
    private String totalLiabilities;
    private String totalOperatingIncome;
    private String mainBusinessIncome;
    private String totalProfit;
    private String netProfit;
    private String totalTax;
    private String totalOwnerEquity;
}
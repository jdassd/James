package com.James.corporateportraitplatforms.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyScore {
    private Long id;
    private String cid;

    private double score;
    private double roe;
    private double roa;
    private double turnoverOfTotalAssets;
    private double assetLiabilityRatio;
    private double growthRateOfOperationIncome;
    private double growthRateOfTotalAssets;
}

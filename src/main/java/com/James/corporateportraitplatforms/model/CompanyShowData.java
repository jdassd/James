package com.James.corporateportraitplatforms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyShowData {
    private long id;
    private String cid;
    private int year;
    private double roe;
    private double roa;
    private double assetLiabilityRatio;
    private double totalOperationIncome;
    private double totalAssets;
}

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
    //年
    private int year;
    //净资产收益率
    private double roe;
    //总资产报酬率
    private double roa;
    //资产负债率
    private double assetLiabilityRatio;
    //总营业额
    private double totalOperationIncome;
    //总资产
    private double totalAssets;
}

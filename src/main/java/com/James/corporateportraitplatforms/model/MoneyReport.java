package com.James.corporateportraitplatforms.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoneyReport {

    private Long id;
    private String cid;
    private String year;
    private String debtFinancingLine;
    private String debtFinancingCost;
    private String equityFinancingLine;
    private String equityFinancingCost;
    private String internalFinancingAndTradeFinancingLine;
    private String internalFinancingAndTradeFinancingCosts;
    private String projectFinancingAndPolicyFinancingQuotas;
    private String projectFinancingAndPolicyFinancingCosts;
}
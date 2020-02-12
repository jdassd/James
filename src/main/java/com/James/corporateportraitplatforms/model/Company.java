package com.James.corporateportraitplatforms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    private String id;
    private String registerTime;
    private String registerMoney;
    private String industry;
    private String city;
    private String companyType;
    private String controllerType;
    private String controllerProportion;
    private String flag;

    private KnowledgeReport knowledgeReport;
    private MoneyReport moneyReport;
    private YearReport yearReport;
    private List<Tag> Tags;
}
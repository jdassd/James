package com.James.corporateportraitplatforms.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KnowledgeReport {

    private Long id;
    private String cid;
    private String patent;
    private String trademark;
    private String copyright;
}
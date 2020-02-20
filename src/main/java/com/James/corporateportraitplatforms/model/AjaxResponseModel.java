package com.James.corporateportraitplatforms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AjaxResponseModel<T> {
    private int code;
    private T data;
    private String msg;
}

package com.hotel.rating.system.hotel.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceResponseBean {

    @Builder.Default
    private Boolean status = Boolean.FALSE;
    private String message;
    private Object data;
    private Set<String> errors;

}
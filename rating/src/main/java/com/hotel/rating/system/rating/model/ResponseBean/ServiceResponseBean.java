package com.hotel.rating.system.rating.model.ResponseBean;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceResponseBean {

    @Builder.Default
    private Boolean status = Boolean.FALSE;
    private String message;
    private Object data;
    private Set<String> errors;

}

package org.myungkeun.hiking_240423.base;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class BaseResponse<T> {
    private Integer statusCode;
    private T data;
}

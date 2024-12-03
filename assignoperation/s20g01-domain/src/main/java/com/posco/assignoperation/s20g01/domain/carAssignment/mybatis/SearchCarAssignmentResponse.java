package com.posco.assignoperation.s20g01.domain.carAssignment.mybatis;

import java.util.Date;
import lombok.Data;

@Data
public class SearchCarAssignmentResponse {

    private String id;
    private Date approvalDate;
    private Date requestDate;
    private Period period;
}

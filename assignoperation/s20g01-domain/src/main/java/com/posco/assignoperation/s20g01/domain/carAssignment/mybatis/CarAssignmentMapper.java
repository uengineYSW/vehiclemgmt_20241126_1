package com.posco.assignoperation.s20g01.domain.carAssignment.mybatis;

import com.posco.assignoperation.s20g01.domain.carAssignment.mybatis.SearchCarAssignmentDTO;
import com.posco.assignoperation.s20g01.domain.carAssignment.mybatis.SearchCarAssignmentResponse;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CarAssignmentMapper {
    SearchCarAssignmentResponse searchCarAssignment(SearchCarAssignmentDTO dto);
}

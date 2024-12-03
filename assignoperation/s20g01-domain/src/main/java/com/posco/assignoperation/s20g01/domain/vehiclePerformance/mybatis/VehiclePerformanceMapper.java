package com.posco.assignoperation.s20g01.domain.vehiclePerformance.mybatis;

import com.posco.assignoperation.s20g01.domain.vehiclePerformance.mybatis.SearchVehiclePerformanceDTO;
import com.posco.assignoperation.s20g01.domain.vehiclePerformance.mybatis.SearchVehiclePerformanceResponse;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VehiclePerformanceMapper {
    SearchVehiclePerformanceResponse searchVehiclePerformance(
        SearchVehiclePerformanceDTO dto
    );
}

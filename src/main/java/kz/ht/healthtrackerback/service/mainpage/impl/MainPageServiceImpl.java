package kz.ht.healthtrackerback.service.mainpage.impl;

import kz.ht.healthtrackerback.models.BaseResponse;
import kz.ht.healthtrackerback.models.DayPlan;
import kz.ht.healthtrackerback.repository.DayPlanRepo;
import kz.ht.healthtrackerback.repository.ProductRepo;
import kz.ht.healthtrackerback.service.mainpage.MainPageService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainPageServiceImpl implements MainPageService {

    private final DayPlanRepo dayPlanRepo;
    private final ProductRepo productRepo;

    public BaseResponse<DayPlan> getDayPlan(int userId) {
        return null;
    }


}

package kz.talimger.controller;

import kz.talimger.dto.city.CitySearchDto;
import kz.talimger.dto.city.CityViewDto;
import kz.talimger.dto.common.PageDto;
import kz.talimger.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cities")
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;

    @GetMapping
    public ResponseEntity<PageDto<CityViewDto>> getCities(@ModelAttribute CitySearchDto searchDto, Pageable pageable) {
        PageDto<CityViewDto> cityPage = cityService.getPageView(searchDto, pageable);
        return ResponseEntity.ok(cityPage);
    }
}

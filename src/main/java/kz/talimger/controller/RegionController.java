package kz.talimger.controller;

import kz.talimger.dto.common.PageDto;
import kz.talimger.dto.region.RegionViewDto;
import kz.talimger.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/regions")
@RequiredArgsConstructor
public class RegionController {
    private final RegionService regionService;

    @GetMapping
    public ResponseEntity<PageDto<RegionViewDto>> getRegions(Pageable pageable) {
        PageDto<RegionViewDto> regionPage = regionService.getPageView(pageable);
        return ResponseEntity.ok(regionPage);
    }
}

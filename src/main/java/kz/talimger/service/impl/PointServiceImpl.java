package kz.talimger.service.impl;

import kz.talimger.dto.institution.InstitutionDTO;
import kz.talimger.model.Point;
import kz.talimger.repository.PointRepository;
import kz.talimger.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PointServiceImpl implements PointService {

    private final PointRepository pointRepository;

    public Point processPoint(InstitutionDTO dto) {
        if (Objects.isNull(dto.getPoint())) {
            return null;
        }

        Double latitude = dto.getPoint().getLat();
        Double longitude = dto.getPoint().getLon();

        Point point = new Point();
        point.setLatitude(latitude);
        point.setLongitude(longitude);
        return pointRepository.save(point);
    }
}

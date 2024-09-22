package kz.talimger.strategy.impl;

import kz.talimger.exception.KazNpuException;
import kz.talimger.model.School;
import kz.talimger.repository.SchoolRepository;
import kz.talimger.strategy.QRCodeStrategy;
import kz.talimger.util.ErrorCodeConstant;
import kz.talimger.util.PDFGenerator;
import kz.talimger.util.QRCodeGenerator;
import kz.talimger.util.QRCodeWithDetailsGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
public class SchoolQRCodeStrategy implements QRCodeStrategy<School> {

    private final QRCodeGenerator qrCodeGenerator;
    private final QRCodeWithDetailsGenerator qrWithDetailsGenerator;
    private final PDFGenerator pdfGenerator;
    private final SchoolRepository schoolRepository;

    @Value("${qr-code.base-url}")
    private String baseUrl;

    public SchoolQRCodeStrategy(QRCodeGenerator qrCodeGenerator, QRCodeWithDetailsGenerator qrWithDetailsGenerator, PDFGenerator pdfGenerator, SchoolRepository schoolRepository) {
        this.qrCodeGenerator = qrCodeGenerator;
        this.qrWithDetailsGenerator = qrWithDetailsGenerator;
        this.pdfGenerator = pdfGenerator;
        this.schoolRepository = schoolRepository;
    }

    @Override
    public School generateAndSaveQRCode(UUID institutionId) {
        School school = findSchoolById(institutionId);
        validateQRCodeNotExists(school);

        try {
            String qrContent = baseUrl + institutionId;
            byte[] qrCode = qrCodeGenerator.generateQRCode(qrContent);
            byte[] qrCodeWithDetails = qrWithDetailsGenerator.generateQRCodeWithDetails(qrCode, school.getName(), school.getAddress().getAddressName());

            school.setQrCode(qrCodeWithDetails);
            return schoolRepository.save(school);
        } catch (Exception e) {
            throw new KazNpuException(HttpStatus.BAD_REQUEST, ErrorCodeConstant.QR_CODE_GENERATION_EXCEPTION, e.getMessage());
        }
    }

    @Override
    public byte[] generatePDFWithQRCode(UUID institutionId) {
        School school = findSchoolById(institutionId);
        validateQRCodeExists(school);

        try {
            return pdfGenerator.generatePDFWithQRCode(school.getQrCode());
        } catch (Exception e) {
            throw new KazNpuException(HttpStatus.BAD_REQUEST, ErrorCodeConstant.PDF_GENERATION_EXCEPTION, e.getMessage());
        }
    }

    @Override
    public School deleteQrCode(UUID institutionId) {
        School school = findSchoolById(institutionId);

        if (Objects.nonNull(school.getQrCode())) {
            school.setQrCode(null);
            schoolRepository.save(school);
        }
        return school;
    }

    private School findSchoolById(UUID institutionId) {
        return schoolRepository.findById(institutionId)
                .orElseThrow(() -> new KazNpuException(HttpStatus.NOT_FOUND, ErrorCodeConstant.SCHOOL_NOT_FOUND, "message.error.school-not-found"));
    }

    private void validateQRCodeNotExists(School school) {
        if (Objects.nonNull(school.getQrCode())) {
            throw new KazNpuException(HttpStatus.FORBIDDEN, ErrorCodeConstant.QR_IS_EXIST, "message.error.qr-is-exist");
        }
    }

    private void validateQRCodeExists(School school) {
        if (Objects.isNull(school.getQrCode())) {
            throw new KazNpuException(HttpStatus.FORBIDDEN, ErrorCodeConstant.QR_NOT_FOUND, "message.error.qr-not-found");
        }
    }
}
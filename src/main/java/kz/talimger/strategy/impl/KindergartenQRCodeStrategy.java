package kz.talimger.strategy.impl;

import kz.talimger.exception.KazNpuException;
import kz.talimger.model.Kindergarten;
import kz.talimger.repository.KindergartenRepository;
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
public class KindergartenQRCodeStrategy implements QRCodeStrategy<Kindergarten> {

    private final QRCodeGenerator qrCodeGenerator;
    private final QRCodeWithDetailsGenerator qrWithDetailsGenerator;
    private final PDFGenerator pdfGenerator;
    private final KindergartenRepository kindergartenRepository;

    @Value("${qr-code.base-url}")
    private String baseUrl;

    public KindergartenQRCodeStrategy(QRCodeGenerator qrCodeGenerator, QRCodeWithDetailsGenerator qrWithDetailsGenerator, PDFGenerator pdfGenerator, KindergartenRepository kindergartenRepository) {
        this.qrCodeGenerator = qrCodeGenerator;
        this.qrWithDetailsGenerator = qrWithDetailsGenerator;
        this.pdfGenerator = pdfGenerator;
        this.kindergartenRepository = kindergartenRepository;
    }

    @Override
    public Kindergarten generateAndSaveQRCode(UUID institutionId) {
        Kindergarten kindergarten = findKindergartenById(institutionId);
        validateQRCodeNotExists(kindergarten);

        try {
            String qrContent = baseUrl + institutionId;
            byte[] qrCode = qrCodeGenerator.generateQRCode(qrContent);
            byte[] qrCodeWithDetails = qrWithDetailsGenerator.generateQRCodeWithDetails(qrCode, kindergarten.getName(), kindergarten.getAddress().getAddressName());

            kindergarten.setQrCode(qrCodeWithDetails);
            return kindergartenRepository.save(kindergarten);
        } catch (Exception e) {
            throw new KazNpuException(HttpStatus.BAD_REQUEST, ErrorCodeConstant.QR_CODE_GENERATION_EXCEPTION, e.getMessage());
        }
    }

    @Override
    public byte[] generatePDFWithQRCode(UUID institutionId) {
        Kindergarten kindergarten = findKindergartenById(institutionId);
        validateQRCodeExists(kindergarten);

        try {
            return pdfGenerator.generatePDFWithQRCode(kindergarten.getQrCode());
        } catch (Exception e) {
            throw new KazNpuException(HttpStatus.BAD_REQUEST, ErrorCodeConstant.PDF_GENERATION_EXCEPTION, e.getMessage());
        }
    }

    @Override
    public Kindergarten deleteQrCode(UUID institutionId) {
        Kindergarten kindergarten = findKindergartenById(institutionId);

        if (Objects.nonNull(kindergarten.getQrCode())) {
            kindergarten.setQrCode(null);
            kindergartenRepository.save(kindergarten);
        }
        return kindergarten;
    }

    private Kindergarten findKindergartenById(UUID institutionId) {
        return kindergartenRepository.findById(institutionId)
                .orElseThrow(() -> new KazNpuException(HttpStatus.NOT_FOUND, ErrorCodeConstant.KINDERGARTEN_NOT_FOUND, "message.error.kindergarten-not-found"));
    }

    private void validateQRCodeNotExists(Kindergarten kindergarten) {
        if (Objects.nonNull(kindergarten.getQrCode())) {
            throw new KazNpuException(HttpStatus.FORBIDDEN, ErrorCodeConstant.QR_IS_EXIST, "message.error.qr-is-exist");
        }
    }

    private void validateQRCodeExists(Kindergarten kindergarten) {
        if (Objects.isNull(kindergarten.getQrCode())) {
            throw new KazNpuException(HttpStatus.FORBIDDEN, ErrorCodeConstant.QR_NOT_FOUND, "message.error.qr-not-found");
        }
    }
}
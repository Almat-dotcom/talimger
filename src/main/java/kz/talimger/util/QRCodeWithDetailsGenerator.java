package kz.talimger.util;

import kz.talimger.constants.QrCodeConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Component
public class QRCodeWithDetailsGenerator {

    @Value("${qr-code.width}")
    private int qrWidth;

    @Value("${qr-code.height}")
    private int qrHeight;

    @Value("${qr-code.font.name}")
    private String fontName;

    @Value("${qr-code.font.size}")
    private int fontSize;

    @Value("${qr-code.big-font.size}")
    private int bigFontSize;

    @Value("${qr-code.small-font.size}")
    private int smallFontSize;

    @Value("${qr-code.text-height}")
    private int textHeight;

    public byte[] generateQRCodeWithDetails(byte[] qrCodeImage, String institutionName, String address) throws Exception {
        Font font = new Font(fontName, Font.PLAIN, fontSize);
        Font bigFont = new Font(fontName, Font.BOLD, bigFontSize);
        Font smallFont = new Font(fontName, Font.PLAIN, smallFontSize);

        BufferedImage combinedImage = new BufferedImage(qrWidth, qrHeight + textHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = combinedImage.createGraphics();

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, combinedImage.getWidth(), combinedImage.getHeight());

        BufferedImage qrImage = ImageIO.read(new ByteArrayInputStream(qrCodeImage));
        g.drawImage(qrImage, 0, 0, qrWidth, qrHeight, null);

        g.setFont(bigFont);
        g.setColor(Color.BLACK);
        drawCenteredText(g, institutionName, qrWidth, qrHeight + 35);

        g.setFont(font);
        drawCenteredText(g, address, qrWidth, qrHeight + 65);

        g.setFont(smallFont);
        g.drawString(QrCodeConstants.KAZNPU, 5, qrHeight + textHeight - 20);
        g.drawString(QrCodeConstants.SYNAPSE, qrWidth - g.getFontMetrics().stringWidth("synapse.hq") - 5, qrHeight + textHeight - 20);

        g.dispose();

        ByteArrayOutputStream image = new ByteArrayOutputStream();
        ImageIO.write(combinedImage, "png", image);
        return image.toByteArray();
    }

    private void drawCenteredText(Graphics2D g, String text, int width, int y) {
        FontMetrics metrics = g.getFontMetrics();
        int x = (width - metrics.stringWidth(text)) / 2;
        g.drawString(text, x, y);
    }
}
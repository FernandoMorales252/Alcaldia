package com.SysGroup.Alcaldia.Utilidades;

import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.context.Context;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;

@Service
public class PdfGeneratorService {
    
    @Autowired
    private SpringTemplateEngine templateEngine;
    
    // Metodo para PDF
    public byte[] generatePdfReport (String templateName, Context context) throws Exception {

        String htmlContent = templateEngine.process(templateName, context);

        try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()){
            PdfRendererBuilder builder = new PdfRendererBuilder();

            builder.useFastMode();
            builder.withHtmlContent(htmlContent, null);
            builder.toStream(outputStream);
            builder.run();

            return outputStream.toByteArray();
        }
    }

}
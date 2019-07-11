package org.dimigo.project;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;

import java.io.FileOutputStream;
import java.io.IOException;

public class PdfSplitter {
    public static void splitPDFFile(String filepath, int pageSize) throws IOException, DocumentException, Exception {
            PdfReader reader = new PdfReader(filepath);
            int total = reader.getNumberOfPages();
            int i = 0;
            if(pageSize < 1){
                throw new Exception();
            }
            for (int page = 1; page <= total; page += pageSize) {
                String outFile = filepath.substring(0, filepath.length()-4)+ "_" + i++ + ".pdf";
                Document document = new Document(reader.getPageSizeWithRotation(1));
                PdfCopy writer = new PdfCopy(document, new FileOutputStream(outFile));
                document.open();
                for (int offset = 0; offset < pageSize && (page + offset) <= total; offset++) {
                    PdfImportedPage Page = writer.getImportedPage(reader, page + offset);
                    writer.addPage(Page);
                }
                document.close();
                writer.close();
            }
    }
}

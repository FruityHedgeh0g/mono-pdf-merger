package fr.hedgehog.services;

import jakarta.enterprise.context.ApplicationScoped;
import org.apache.pdfbox.io.RandomAccessStreamCacheImpl;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PdfMergerService {

    public byte[] mergePdf(List<File> files) throws Exception {

        PDFMergerUtility pdfMergerUtility = new PDFMergerUtility();

        if (files == null || files.isEmpty()) {
            return null;
        }

        for (File file : files) {
            try{
                pdfMergerUtility.addSource(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }catch (Exception e){
                throw new Exception(e);
            }
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        pdfMergerUtility.setDestinationStream(out);
        pdfMergerUtility.mergeDocuments(RandomAccessStreamCacheImpl::new);

        return out.toByteArray();
    }

    public byte[] mergePdfAsUrl(List<String> fileUrls) throws Exception {
        List<File> files = new ArrayList<>();
        for (String fileUrl : fileUrls) {
            files.add(new File(fileUrl));
        }
        return this.mergePdf(files);
    }
}

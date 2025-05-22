package fr.hedgehog.services;

import jakarta.enterprise.context.ApplicationScoped;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.io.RandomAccessStreamCacheImpl;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PdfMergerService {

    public File mergePdf(String destination, List<File> files) throws Exception {

        PDFMergerUtility pdfMergerUtility = new PDFMergerUtility();

        if (files == null || files.isEmpty()) {
            return null;
        }

        if (destination == null || destination.isEmpty()) {
            destination = files.getFirst()
                    .getParent() + "/" + String.format("%tY%<tm%<td%<tH%<tM%<tS%<tL-merged.pdf"
                    , OffsetDateTime.now());
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

        pdfMergerUtility.setDestinationFileName(destination);
        pdfMergerUtility.mergeDocuments(RandomAccessStreamCacheImpl::new);

        return new File(destination);
    }

    public File mergePdf(List<File> files) throws Exception {
        return this.mergePdf(null, files);
    }

    public File mergePdfAsUrl(List<String> fileUrls) throws Exception {
        List<File> files = new ArrayList<>();
        for (String fileUrl : fileUrls) {
            files.add(new File(fileUrl));
        }
        return this.mergePdf(files);
    }
}

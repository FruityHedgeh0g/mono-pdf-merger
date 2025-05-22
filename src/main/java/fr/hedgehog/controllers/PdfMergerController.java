package fr.hedgehog.controllers;

import fr.hedgehog.pojo.MergePdfMetaData;
import fr.hedgehog.services.PdfMergerService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import java.io.File;
import java.util.List;

@Path("/v1/pdf-merger-api")
@ApplicationScoped
public class PdfMergerController {

    @Inject
    PdfMergerService pdfMergerService;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.MULTIPART_FORM_DATA)
    public RestResponse<File> mergeAsAMultipart(
            @RestForm("metadata")
            @PartType(MediaType.TEXT_PLAIN)
            MergePdfMetaData metadata,

            @RestForm("files")
            @PartType(MediaType.MULTIPART_FORM_DATA)
            List<FileUpload> files) throws Exception {

        List<File> fileList = files.stream()
                .map(f -> f.uploadedFile().toFile())
                .toList();

        return RestResponse.ok(pdfMergerService.mergePdf(metadata.getDestination(), fileList));
    }
}

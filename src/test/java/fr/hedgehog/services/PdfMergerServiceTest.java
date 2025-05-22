package fr.hedgehog.services;//package fr.hedgehog.services;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@QuarkusTest
class PdfMergerServiceTest {

    @Inject
    PdfMergerService pdfMergerService;

    @BeforeAll
    static void setUp() {
        File dir = new File("src/test/resources/pdf");
        File[] prevMerges = dir.listFiles((dir1, name) -> name.endsWith("-merged.pdf"));
        if ( prevMerges != null && prevMerges.length > 0) {
            for (File file : prevMerges){
                file.delete();
            }
        }
    }

    @ParameterizedTest
    @MethodSource("dataSamplesMergePdf")
    void mergePdf_OK(List<String> args) throws Exception {
        System.out.printf("Starting at %tY-%<tm-%<td %<tH:%<tM'%<tS''%<tL\n", OffsetDateTime.now());
        pdfMergerService.mergePdfAsUrl(args);
        System.out.printf("Ending at %tY-%<tm-%<td %<tH:%<tM'%<tS''%<tL\n", OffsetDateTime.now());
    }

    static Stream<Arguments> dataSamplesMergePdf() {
        return Stream.of(
                Arguments.of(Arrays.asList(
                        "src/test/resources/pdf/Courrier-XX.pdf"
                        ,"src/test/resources/pdf/Courrier-XX.pdf"
                        ,"src/test/resources/pdf/Courrier-XX.pdf"
                        ,"src/test/resources/pdf/Courrier-XX.pdf"
                        ,"src/test/resources/pdf/Courrier-XX.pdf"
                        ,"src/test/resources/pdf/Courrier-XX.pdf"
                        ,"src/test/resources/pdf/Courrier-XX.pdf"
                        ,"src/test/resources/pdf/Courrier-XX.pdf"
                        ,"src/test/resources/pdf/Courrier-XX.pdf"
                        ,"src/test/resources/pdf/Courrier-XX.pdf"
                        ,"src/test/resources/pdf/Courrier-XX.pdf"
                        ,"src/test/resources/pdf/Courrier-XX.pdf"
                        ,"src/test/resources/pdf/Courrier-XX.pdf"
                        ,"src/test/resources/pdf/Courrier-XX.pdf"
                        ,"src/test/resources/pdf/Courrier-XX.pdf"
                        ,"src/test/resources/pdf/Courrier-XX.pdf"
                        ,"src/test/resources/pdf/Courrier-XX.pdf"
                        ,"src/test/resources/pdf/Courrier-XX.pdf"
                        ,"src/test/resources/pdf/Courrier-XX.pdf"
                        ,"src/test/resources/pdf/Courrier-XX.pdf"
                        ,"src/test/resources/pdf/Courrier-XX.pdf"
                        ,"src/test/resources/pdf/Courrier-XX.pdf"
                        ,"src/test/resources/pdf/Courrier-XX.pdf"
                        ,"src/test/resources/pdf/Courrier-XX.pdf"
                        ,"src/test/resources/pdf/Courrier-XX.pdf"
                        ,"src/test/resources/pdf/Courrier-XX.pdf"
                        ,"src/test/resources/pdf/Courrier-XX.pdf"
                        ,"src/test/resources/pdf/Courrier-XX.pdf"
                        ,"src/test/resources/pdf/Courrier-XX.pdf"
                        ,"src/test/resources/pdf/Courrier-XX.pdf"
                ))
//                Arguments.of(Arrays.asList("src/test/resources/pdf/Hello.pdf"))
//                ,Arguments.of(Arrays.asList("src/test/resources/pdf/Hello.pdf", "src/test/resources/pdf/World.pdf"))
//                ,Arguments.of(Arrays.asList("src/test/resources/pdf/Hedge1.pdf", "src/test/resources/pdf/Hedge2.pdf", "src/test/resources/pdf/Hedge3.pdf"))
        );
    }
}
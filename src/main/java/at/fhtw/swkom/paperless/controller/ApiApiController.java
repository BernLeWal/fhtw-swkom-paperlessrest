package at.fhtw.swkom.paperless.controller;


import at.fhtw.swkom.paperless.services.dto.Statistics;
import at.fhtw.swkom.paperless.services.dto.UserInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Generated;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-07-06T10:18:32.656877Z[Etc/UTC]")
@Controller
@RequestMapping("${openapi.mockServer.base-path:}")
@Slf4j
public class ApiApiController implements ApiApi {
    @RequestMapping(
            method = RequestMethod.GET,
            value = {"/api/statistics", "/api/statistics/"}
    )
    @Override
    public ResponseEntity<Statistics> statistics(

    ) {
        log.info("GET /api/statistics");
        var stat = Statistics.builder()
                .documentsInboxCount(400)
                .documentsTotalCount(800)
                .inboxTagCount(200)
                .characterCount(100000)
                .documentFileTypeCounts( Arrays.asList(
                        Statistics.DocumentFileTypeCount.builder().mimeType("application/pdf").count(400).build(),
                        Statistics.DocumentFileTypeCount.builder().mimeType("application/msword").count(200).build()
                ) )
                .build();
        return new ResponseEntity<>(stat, HttpStatus.OK);
    }


    @RequestMapping(
            method = RequestMethod.POST,
            value = {"/api/token", "/api/token/"},
            consumes = { "application/json", "text/json", "application/*+json" }
    )
    @Override
    public ResponseEntity<String> getToken(
            @Parameter(name = "UserInfo") @Valid @RequestBody(required = false) UserInfo userInfo
    ) {
        log.info("POST /api/token");
        if (userInfo.getUsername().get().equals("user") && userInfo.getPassword().get().equals("password"))
            return new ResponseEntity<>("{\"token\": \"asdasd\"}", HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }


    /**
     * POST /api/documents/post_document
     *
     * @param title  (optional)
     * @param created  (optional)
     * @param documentType  (optional)
     * @param tags  (optional)
     * @param correspondent  (optional)
     * @param document  (optional)
     * @return Success (status code 200)
     */
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/api/documents/post_document",
            consumes = { "multipart/form-data" }
    )
    public ResponseEntity<Void> uploadDocument(
            @Parameter(name = "title", description = "") @Valid @RequestParam(value = "title", required = false) String title,
            @Parameter(name = "created", description = "") @Valid @RequestParam(value = "created", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime created,
            @Parameter(name = "document_type", description = "") @Valid @RequestParam(value = "document_type", required = false) Integer documentType,
            @Parameter(name = "tags", description = "") @Valid @RequestPart(value = "tags", required = false) List<Integer> tags,
            @Parameter(name = "correspondent", description = "") @Valid @RequestParam(value = "correspondent", required = false) Integer correspondent,
            @Parameter(name = "document", description = "") @RequestPart(value = "document", required = false) List<MultipartFile> document
    ) {
        log.info("POST /api/documents/post_document");
        log.info("received document with title " + title);
        if ( document != null ) {
            for (MultipartFile doc : document) {
                log.info("received document from filename " + doc.getOriginalFilename());

                // Only for debugging - show that the file was really received
                int totalBytesRead = 0;
                try (
                        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(doc.getOriginalFilename()));
                ) {
                    var inputStream = doc.getInputStream();
                    byte[] buffer = new byte[1024];
                    int bytesRead = -1;

                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                        totalBytesRead += bytesRead;
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                log.info("received file has " + totalBytesRead + " bytes.");
            }
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}

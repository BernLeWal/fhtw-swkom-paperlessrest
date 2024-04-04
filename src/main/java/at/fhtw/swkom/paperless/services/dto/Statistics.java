package at.fhtw.swkom.paperless.services.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Statistics {
    @JsonProperty("documents_total")
    private long documentsTotalCount;

    @JsonProperty("documents_inbox")
    private long documentsInboxCount;

    @JsonProperty("inbox_tag")
    private long inboxTagCount;

    @JsonProperty("character_count")
    private long characterCount;

    @JsonProperty("document_file_type_counts")
    private List<DocumentFileTypeCount> documentFileTypeCounts;

    @Data
    @Builder
    public static class DocumentFileTypeCount {
        @JsonProperty("mime_type")
        private String mimeType;

        @JsonProperty("mime_type_count")
        private long count;
    }
}



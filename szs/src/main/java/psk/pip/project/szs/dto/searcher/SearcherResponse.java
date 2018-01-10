package psk.pip.project.szs.dto.searcher;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearcherResponse {
	private Long entityId;
	private String text;
}

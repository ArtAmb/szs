package psk.pip.project.szs.dto.searcher;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearcherParams {
	private String queryString;
}

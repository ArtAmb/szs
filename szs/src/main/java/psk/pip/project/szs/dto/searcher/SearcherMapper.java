package psk.pip.project.szs.dto.searcher;

import psk.pip.project.szs.entity.administration.Employee;

public class SearcherMapper {

	public static SearcherResponse map(Employee employee) {
		String text = String.format("Id: %d, login: %s dr. %s %s", employee.getId(), employee.getUser().getLogin(),
				employee.getName(), employee.getSurname());
		return SearcherResponse.builder().entityId(employee.getId()).text(text).build();
	}
}

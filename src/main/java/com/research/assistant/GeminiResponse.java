package com.research.assistant;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeminiResponse {
	public List<Candidate> candidates;

	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Candidate {
		private Content content;
	}

	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Content {
		List<Part> parts;
	}

	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Part {
		String text;
	}
}

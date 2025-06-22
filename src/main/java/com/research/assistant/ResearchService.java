package com.research.assistant;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ResearchService {
	
	@Value("${gemini.api.url}")
	private String geminiApiBaseUrl;
	
	@Value("${gemini.api.key}")
	private String geminiApiKey;
	
	private final WebClient webClient;
	
	private final ObjectMapper objectMapper;
	
	public ResearchService(WebClient.Builder webClientBuilder, ObjectMapper objectMapper) {
		this.webClient = webClientBuilder.build();
		this.objectMapper = objectMapper;
	}

	public String processContent(ResearchRequest request) {
		// Build prompt
		String prompt = buildPrompt(request);

		// Querying the AI API
		Map<String, Object> requestBody = Map.of("contents",
				new Object[] { Map.of("parts", new Object[] { Map.of("text", prompt) }) });
		
		String finalUrl = geminiApiBaseUrl + "?key=" + geminiApiKey;
		// System.out.println("Final Gemini URL: " + finalUrl);

		
		String response = webClient.post()
				.uri(finalUrl)
				.bodyValue(requestBody)
				.retrieve()
				.bodyToMono(String.class)
				.block();
		
		
		//Parse the response
		//Return the response
		return extractTextFromResponse(response);
	}

	private String extractTextFromResponse(String response) {
	    try {
	        GeminiResponse geminiResponse = objectMapper.readValue(response, GeminiResponse.class);

	        if (geminiResponse.getCandidates() == null || geminiResponse.getCandidates().isEmpty()) {
	            return "";
	        }

	        GeminiResponse.Candidate firstCandidate = geminiResponse.getCandidates().get(0);
	        if (firstCandidate.getContent() == null || firstCandidate.getContent().getParts() == null 
	            || firstCandidate.getContent().getParts().isEmpty()) {
	            return "";
	        }

	        String text = firstCandidate.getContent().getParts().get(0).getText();
	        return (text != null) ? text : "";

	    } catch (Exception e) {
	        e.printStackTrace(); // Log instead of returning error message
	        return "";
	    }
	}


	private String buildPrompt(ResearchRequest request) {
		StringBuilder prompt = new StringBuilder();
		switch (request.getOperation()) {
		case "summarize":
			prompt.append("Provide a clear and concise summary of the following text in a few sentences:\n\n");
			break;
		case "suggest":
			prompt.append(
					"Suggest what other topics that i could read similar to the following text. Format response with clear heading and bullet points\n\n");
			break;
		case "prerequisite":
			prompt.append(
					"Provide what are things or concept one should know to understand the following text completely. Give your answer in bullet points\n\n");
			break;
		default:
			throw new IllegalArgumentException("Unknown operation : " + request.getOperation());
		}
		prompt.append(request.getContent());
		return prompt.toString();
	}

}

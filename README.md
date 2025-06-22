# Research Assistant Chrome Extension

### ⚙️ Extension Side Panel
![Side Panel](screenshots\Screenshot_side_panel.png)

### Selecting and Summarizing Text
![Summarize Text](screenshots\Screenshot_summarizing.png)

### ✍️ Writing and Saving Notes
![Notes Section](screenshots\Screenshot_saving_notes.png)

## Overview

The Research Assistant is a Chrome extension designed to enhance your online research experience. With this tool, you can select any text on a webpage and perform three key actions:

- **Summarize**: Get a concise summary of the selected text.
- **Find Similar Content**: Retrieve additional related content to expand your understanding.
- **Prerequisites**: Identify any prerequisite knowledge needed to comprehend the selected text.

Additionally, the extension allows users to write and save notes locally within Chrome for easy reference.

## Features

- **AI-Powered Research Assistance**: Uses Spring AI and Gemini API for processing content.
- **Local Note-Taking**: Save notes directly in Chrome for future use.
- **Seamless Integration**: Opens a side panel on selection for quick access to features.

## Tech Stack

- **Backend**: Spring Boot, Spring AI
- **Frontend**: HTML, JavaScript
- **API**: Google Gemini AI

## Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/Priyanshk444/research-assistant.git
   ```
2. Navigate to the project directory:
   ```sh
   cd research-assistant
   ```
3. Install Chrome Extension:
   - Open Chrome and go to `chrome://extensions/`
   - Enable **Developer mode** (toggle on the top right corner).
   - Click **Load unpacked** and select the `src/main/resorces/static` folder inside the project.

## Configuration

Update the `application.properties` file with your API key:

```properties
spring.application.name=research-assistant
gemini.api.url=https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent
gemini.api.key=${GEMINI_KEY}
```

## Backend API Endpoints

### Process Research Content

**Endpoint:** `POST /api/research/process`



### Request Model

```java
@Data
public class ResearchRequest {
    private String content;
    private String operation;
}
```

## Author
Priyansh Kumar

For any issues or questions, please contact [[priyansh.kumar.dev@gmail.com](mailto\:priyansh.kumar.dev@gmail.com)] or open an issue on GitHub.


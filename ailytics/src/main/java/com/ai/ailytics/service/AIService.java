package com.ai.ailytics.service;

import com.ai.ailytics.model.DataRow;
import com.ai.ailytics.storage.DataStorage;
import com.openai.client.OpenAIClient;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import com.openai.models.ChatModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AIService {

    private final OpenAIClient client;

    public AIService(OpenAIClient client) {
        this.client = client;
    }

    public String askAI(String question) {

        List<DataRow> data = DataStorage.getAll();

        StringBuilder context = new StringBuilder();

        for (int i = 0; i < Math.min(data.size(), 50); i++) {
            context.append(data.get(i).getData().toString()).append("\n");
        }

        String prompt = "You are a data analyst.\n"
                + "Analyze the following dataset:\n"
                + context
                + "\nUser Question: " + question
                + "\nAnswer clearly:";

        ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
                .addUserMessage(prompt)
                .model(ChatModel.GPT_5_2)
                .build();

        try {
            ChatCompletion response = client.chat().completions().create(params);

            return response.choices().stream()
                    .flatMap(choice -> choice.message().content().stream())
                    .findFirst()
                    .orElse("No response from AI");

        } catch (Exception e) {

            return "Demo Mode: Based on dataset, USA has highest sales. (AI quota exceeded)";
        }
    }
}
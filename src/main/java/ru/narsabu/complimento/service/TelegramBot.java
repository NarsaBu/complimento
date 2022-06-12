package ru.narsabu.complimento.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.narsabu.complimento.configuration.properties.TelegramBotConfigurationProperties;

@Service
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {
    private final TelegramBotConfigurationProperties config;
    private final ComplimentService complimentService;

    @Override
    public void onUpdateReceived(Update update) {
        try {
            val receivedMessage = update.getMessage().getText();

            switch (receivedMessage) {
                case "/start" -> execute(performWelcome(update));
                case "/compliment" -> execute(createCompliment(update));
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private SendMessage createCompliment(Update update) {
        val message = new SendMessage();
        message.setChatId(update.getMessage().getChatId().toString());
        message.setText(complimentService.getCompliment());

        return message;
    }

    private SendMessage performWelcome(Update update) {
        val message = new SendMessage();
        message.setChatId(update.getMessage().getChatId().toString());
        message.setText("""
                                Привет! Я - Комплименто, бот, который будет генерировать комплименты для тебя. Мой создатель - @zalhala.

                                Для того, чтобы начать со мной работу отправь /start.
                                Для того, чтобы получить комплимент, отправь /compliment.""");

        return message;
    }

    @Override
    public String getBotUsername() {
        return config.getUsername();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }
}

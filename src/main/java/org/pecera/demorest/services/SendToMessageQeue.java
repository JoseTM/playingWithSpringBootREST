package org.pecera.demorest.services;

public interface SendToMessageQeue {
    public boolean send(String message);
    public boolean read();
}

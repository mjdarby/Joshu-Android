package net.mjdarby.joshu;

class ServerResponse {
    String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    private String text;
    private String mood;
}

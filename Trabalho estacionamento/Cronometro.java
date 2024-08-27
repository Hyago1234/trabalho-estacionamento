public class Cronometro {
    private long startTime;
    private long elapsedTime;
    private boolean running;

    public Cronometro() {
        this.startTime = 0;
        this.elapsedTime = 0;
        this.running = false;
    }

    public void start() {
        if (!running) {
            this.startTime = System.currentTimeMillis();
            this.running = true;
        }
    }

    public void stop() {
        if (running) {
            this.elapsedTime += System.currentTimeMillis() - startTime;
            this.running = false;
        }
    }

    public void reset() {
        this.startTime = 0;
        this.elapsedTime = 0;
        this.running = false;
    }

    public long getElapsedTime() {
        if (running) {
            return elapsedTime + (System.currentTimeMillis() - startTime);
        } else {
            return elapsedTime;
        }
    }

    public String getFormattedElapsedTime() {
        long elapsedTimeMillis = getElapsedTime();
        long seconds = (elapsedTimeMillis / 1000) % 60;
        long minutes = (elapsedTimeMillis / (1000 * 60)) % 60;
        long hours = (elapsedTimeMillis / (1000 * 60 * 60)) % 24;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
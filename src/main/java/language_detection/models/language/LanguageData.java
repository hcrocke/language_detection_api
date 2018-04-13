package language_detection.models.language;

public class LanguageData {

    LanguageDetection[] detections;

    public LanguageData() {
    }

    public LanguageData(LanguageDetection[] detections) {
        this.detections = detections;
    }

    public LanguageDetection[] getDetections() {
        return detections;
    }

    public void setDetections(LanguageDetection[] detections) {
        this.detections = detections;
    }

}

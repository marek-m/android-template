package project.template.com.template.template_recyclerview.model;

/**
 * Created by Marek on 2015-09-16.
 */
public class ViewModel {
    private String text;
    private boolean checked;

    public ViewModel(String text, boolean checked) {

        this.text = text;
        this.checked = checked;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}

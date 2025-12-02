package edu.farmingdale.taskmanager.viewmodels;

import edu.farmingdale.taskmanager.Models.MapMark;
import javafx.beans.property.*;

public class MapMarkViewModel {
    private final MapMark mapMark;
    private final BooleanProperty completed = new SimpleBooleanProperty();
    private final IntegerProperty completionIndex = new SimpleIntegerProperty();
    private final DoubleProperty x = new SimpleDoubleProperty();
    private final DoubleProperty y = new SimpleDoubleProperty();

    public MapMarkViewModel(MapMark mapMark) {
        this.mapMark = mapMark;
        this.completed.set(mapMark.isCompleted());
        this.completionIndex.set(mapMark.getCompletionIndex());
        this.x.set(mapMark.getX());
        this.y.set(mapMark.getY());

        // Listen to property changes to update the underlying model
        completed.addListener((obs, oldV, newV) -> mapMark.setCompleted(newV));
    }

    public BooleanProperty completedProperty() { return completed; }
    public DoubleProperty xProperty(){return x;}
    public DoubleProperty yProperty(){return y;}
    public IntegerProperty completionIndexProperty(){return completionIndex;}

    public int getCompletionIndex() { return completionIndex.get(); }
    public void setCompletionIndex(int idx) { completionIndex.set(idx); }

}

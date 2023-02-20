package org.vaadin.addons.sample;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.shared.Registration;
import elemental.json.JsonObject;

@Tag("jhoffmann-leaflet")
@JsModule("leaflet-kml.ts")
@NpmPackage(value = "leaflet", version = "1.9.3")
@NpmPackage(value = "leaflet-kml", version = "1.0.1")
@CssImport("leaflet/dist/leaflet.css")
public class LeafletKml extends Component implements HasSize {
    @Id("map")
    private JsonObject mapElement;

    public LeafletKml() {
        // Set the default size of the map
        setWidth(600);
        setHeight(400);
    }

    public void setWidth(int width) {
        getElement().setProperty("width", width);
    }

    public String getWidth() {
        return String.valueOf(getElement().getProperty("width", 600));
    }

    public void setHeight(int height) {
        getElement().setProperty("height", height);
    }

    public String getHeight() {
        return String.valueOf(getElement().getProperty("height", 400));
    }

    public void setKmlUrl(String kmlUrl) {
        getElement().setProperty("kmlUrl", kmlUrl);
    }

    public String getKmlUrl() {
        return getElement().getProperty("kmlUrl", "");
    }

    public Registration addMapClickListener(ComponentEventListener<MapClickEvent> listener) {
        return addListener(MapClickEvent.class, listener);
    }

    public static class MapClickEvent extends ComponentEvent<LeafletKml> {
        private double latitude;
        private double longitude;

        public MapClickEvent(LeafletKml source, double latitude, double longitude) {
            super(source, false);
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }
    }
}

